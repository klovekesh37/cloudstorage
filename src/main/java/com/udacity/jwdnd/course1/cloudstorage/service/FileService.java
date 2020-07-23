package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileNameAlreadyExistsException;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.exception.ResourceNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource(value = "classpath:message.properties")
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private UserService userService;

    @Value("${resource.error}")
    String resourceError;
    @Value("${file.id.error}")
    String fileIdError;
    @Value("${file.delete.error}")
    String deleteError;
    @Value("${file.exists.error}")
    String fileExistsError;
    @Value("${file.available.error}")
    String notAvailableError;
    @Value("${file.upload.error}")
    String notUploadedError;

    public boolean isNullOrEmpty(MultipartFile file) throws FileNotAvailableException {
        if(file==null || file.isEmpty())
            throw new FileNotAvailableException(notAvailableError);
        return false;
    }
    public boolean isPresent(Long fileId) throws ResourceNotAvailableException {
        File file;
        try {
            file = fileMapper.findFilesByUserIdAndFileId(userService.getUserId(), fileId);
        }catch(DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        return file != null;
    }

    public boolean isFilePresentByFileName(String fileName) throws ResourceNotAvailableException {
        File file;
        try {
            file = fileMapper.findFileByUserIdAndFileName(userService.getUserId(), fileName);
        }catch(DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        return file != null;
    }

    public File getFile(Long fileId) throws FileNotAvailableException, ResourceNotAvailableException {
        boolean isFileAvailable = isPresent(fileId);
        if(!isFileAvailable)
            throw new FileNotAvailableException(notAvailableError);
        try {
            return fileMapper.findFileById(fileId);
        }catch(DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
    }

    public void deleteFile(Long fileId) throws FileNotAvailableException, ResourceNotAvailableException {
        boolean isFileAvailable = isPresent(fileId);
        if(!isFileAvailable)
            throw new FileNotAvailableException(notAvailableError);
        Integer noOfRowsDeleted;
        try {
            noOfRowsDeleted = fileMapper.deleteFileById(fileId);
        }catch(DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if(noOfRowsDeleted != 1)
            throw new ResourceNotAvailableException(deleteError);
    }

    public void createFile(MultipartFile file) throws ResourceNotAvailableException, FileNameAlreadyExistsException, FileNotAvailableException {
        boolean isFileAvailable = !isNullOrEmpty(file);
        boolean canCreateFile = !isFilePresentByFileName(file.getOriginalFilename());
        if(canCreateFile && isFileAvailable) {
            Long fileId;
            try {
                fileId = fileMapper.createFile(new File(null, file.getOriginalFilename(), file.getContentType(), String.valueOf(file.getSize()), userService.getUserId(), file.getBytes()));
            } catch (DataAccessException dataAccessException) {
                logger.error(dataAccessException.getMessage());
                throw new ResourceNotAvailableException(resourceError);
            } catch (IOException ioException) {
                logger.error(ioException.getMessage());
                throw new ResourceNotAvailableException(notUploadedError);
            }
            if (fileId == 0)
                throw new ResourceNotAvailableException(fileIdError);
        }else
            throw new FileNameAlreadyExistsException(fileExistsError);
    }

    public List<File> getAllFiles() throws ResourceNotAvailableException {
        List<File> files ;
        try{
            files = fileMapper.findAll(userService.getUserId());
        }catch(DataAccessException dataAccessException) {
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if(files==null || files.isEmpty())
            return new ArrayList<>();
        else
            return files;
    }

}
