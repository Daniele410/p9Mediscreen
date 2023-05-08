package com.medinote.medinote.curl;

import com.medinote.medinote.model.Note;
import com.medinote.medinote.model.dto.NoteDto;
import com.medinote.medinote.service.INoteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;


/**
 * Controller class for testing note with curl commands.
 */
@RestController
@RequestMapping
public class NoteCurlController {

    /**
     * SLF4J Logger instance.
     */
    private final Logger log = LoggerFactory.getLogger(NoteCurlController.class);

    /**
     * Instance of INoteService
     */
    private final INoteService noteService;

    /**
     * Constructor for NoteCurlController that injects the noteService dependency.
     * @param noteService An instance of INoteService.
     */
    public NoteCurlController(INoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * POST method to create a new note.
     * @param noteDto A Data Transfer Object containing the information needed to create the note.
     * @return A ResponseEntity containing the created note and HTTP status code.
     */
    @PostMapping("/patHistory/add")
    public ResponseEntity<Note> addNoteCurl(@Valid NoteDto noteDto) {
        log.info("save note curl:{} ", noteDto.getPatId());
        Note note = new Note(Long.valueOf(noteDto.getPatId()), noteDto.getE());
        return new ResponseEntity<>(noteService.saveNote(note), CREATED);
    }

}
