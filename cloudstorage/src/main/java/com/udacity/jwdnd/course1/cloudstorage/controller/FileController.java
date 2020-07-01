package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileNameAlreadyExistsException;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.exception.ResourceNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
@PropertySource(value = "classpath:message.properties")
public class FileController {

    private final Logger logger = LoggerFactory.getLogger(CredentialService.class);

    @Autowired
    private FileService fileService;

    @Value("${resource.error}")
    String resourceError;

    @GetMapping("/delete")
    public String deleteFile(@RequestParam("fileid") Long fileId, Model model) throws FileNotAvailableException, ResourceNotAvailableException {
        fileService.deleteFile(fileId);
        model.addAttribute("success", true);
        return "result";
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity downloadFile(@RequestParam("fileid") Long fileId, Model model) throws FileNotAvailableException, ResourceNotAvailableException {
        File file = fileService.getFile(fileId);
        try {
           ByteArrayResource resource = new ByteArrayResource(file.getFileData());
           return ResponseEntity.ok()
                   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName())
                   .contentType(MediaType.valueOf(file.getContentType()))
                   .contentLength(Long.parseLong(file.getFileSize()))
                   .body(resource);
       } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new ResourceNotAvailableException(resourceError);
       }
    }

    @PostMapping
    public String UploadFile(MultipartFile fileUpload, Model model) throws FileNameAlreadyExistsException, FileNotAvailableException, ResourceNotAvailableException, MaxUploadSizeExceededException {
            fileService.createFile(fileUpload);
            model.addAttribute("success",true);
            return "result";
    }
}
