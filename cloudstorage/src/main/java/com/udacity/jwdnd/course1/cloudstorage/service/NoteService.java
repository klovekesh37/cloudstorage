package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.exception.IdGenerationException;
import com.udacity.jwdnd.course1.cloudstorage.exception.NoteNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.exception.ResourceNotAvailableException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource(value = "classpath:message.properties")
public class NoteService {

    private final Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private UserService userService;

    @Value("${resource.error}")
    String resourceError;
    @Value("${note.id.error}")
    String noteIdError;
    @Value("${note.delete.error}")
    String deleteError;
    @Value("${note.update.error}")
    String updateError;
    @Value("${note.available.error}")
    String notAvailableError;


    public Boolean isPresent(Long noteId) throws ResourceNotAvailableException {
        Note note ;
        try {
            note = noteMapper.findNoteByNoteIdAndUserId(noteId, userService.getUserId());
        }catch(DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if (note != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public void deleteNote(Long noteId) throws NoteNotAvailableException, ResourceNotAvailableException {
        boolean isNoteAvailable = isPresent(noteId);
        if(!isNoteAvailable)
            throw new NoteNotAvailableException(notAvailableError);
        Integer noOfRowsDeleted;
        try {
            noOfRowsDeleted = noteMapper.deleteNoteById(noteId);
        }catch(DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if(noOfRowsDeleted != 1)
            throw new ResourceNotAvailableException(deleteError);
    }

    public void createNote(Note note) throws ResourceNotAvailableException, IdGenerationException {
        note.setUserId(userService.getUserId());
        Long noteId;
        try {
            noteId = noteMapper.createNote(note);
        }catch(DataAccessException dataAccessException) {
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if(noteId <= 0)
            throw new IdGenerationException(noteIdError);
    }

    public void updateNote(Note note) throws ResourceNotAvailableException {
        note.setUserId(userService.getUserId());
        Integer noOfRowsUpdated;
        try {
            noOfRowsUpdated = noteMapper.updateNote(note);
        }catch(DataAccessException dataAccessException) {
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if(noOfRowsUpdated != 1)
            throw new ResourceNotAvailableException(updateError);
    }

    public List<Note> getAllNotes() throws ResourceNotAvailableException {
        List<Note> notes ;
        try{
            notes = noteMapper.findAll(userService.getUserId());
        }catch(DataAccessException dataAccessException) {
            logger.error(dataAccessException.getMessage());
            throw new ResourceNotAvailableException(resourceError);
        }
        if(notes == null || notes.isEmpty())
            return new ArrayList<>();
        return notes;
    }

    public void updateOrCreateNote(Note note) throws IdGenerationException, ResourceNotAvailableException {
        Long noteId = note.getNoteId();
        if (noteId == null)
            createNote(note);
        else
            updateNote(note);
    }
}