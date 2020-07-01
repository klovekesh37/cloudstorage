package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.exception.IdGenerationException;
import com.udacity.jwdnd.course1.cloudstorage.exception.NoteNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.exception.ResourceNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/delete")
    public String deleteNote(@RequestParam("id") Long noteId, Model model) throws NoteNotAvailableException, ResourceNotAvailableException {
                noteService.deleteNote(noteId);
                model.addAttribute("success", true);
                return "result";
    }

    @PostMapping
    public String updateOrCreateNote(Note note, Model model) throws IdGenerationException, ResourceNotAvailableException {
            noteService.updateOrCreateNote(note);
            model.addAttribute("success",true);
            return "result";
    }
}
