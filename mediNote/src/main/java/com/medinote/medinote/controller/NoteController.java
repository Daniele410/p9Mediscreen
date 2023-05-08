package com.medinote.medinote.controller;

import com.medinote.medinote.Exceptions.NoteNotFoundException;
import com.medinote.medinote.model.Note;
import com.medinote.medinote.service.INoteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * NoteController is a REST API controller
 * that handles HTTP requests related to note information.
 */
@Controller
public class NoteController {
    /**
     * SLF4J Logger instance.
     */
    private final Logger log = LoggerFactory.getLogger(NoteController.class);

    /**
     * Instance of INoteService
     */
    private final INoteService noteService;

    /**
     * Constructor for NoteController that injects the noteService dependency.
     * @param noteService
     */
    public NoteController(INoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Retrieves all notes from the database.
     * @return ResponseEntity containing a list of all notes and a HTTP status code
     */
    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes() {
        log.info("get all notes");
        return new ResponseEntity<>(noteService.getAllNotes(), OK);
    }

    /**
     * Retrieves a specific note from the database.
     * @param id the ID of the note to retrieve
     * @return ResponseEntity containing the requested note and HTTP status code
     * @throws NoteNotFoundException if the note with the given ID is not found
     */
    @GetMapping("/note")
    public ResponseEntity<Note> getNoteById( @RequestParam String id) throws NoteNotFoundException {
        log.info("get note by id :{} ", id);
        return new ResponseEntity<>(noteService.findNoteById((id)), OK);
    }

    /**
     * Adds a new note to the database.
     * @param note the Note object to be added
     * @return ResponseEntity containing the newly added note and a HTTP status code
     */
    @PostMapping("/note")
    public ResponseEntity<Note> addNote(@RequestBody @Valid Note note) {
        log.info("save note :{} ", note.getId());

        return new ResponseEntity<>(noteService.saveNote(note), CREATED);
    }


    /**
     * Updates an existing note in the database.
     * @param note the updated Note object
     * @return ResponseEntity containing the updated note and HTTP status code
     * @throws NoteNotFoundException if the note with the given ID is not found
     */
    @PutMapping("/note")
    public ResponseEntity<Note> updateNote(@RequestBody Note note) throws NoteNotFoundException {
        log.info("update note with id :{} ", note.getId());
        return new ResponseEntity<>(noteService.updateNote(note), OK );
    }

    /**
     * Delete method to delete patient by id
     * @param id the ID of the note to delete
     * @return ResponseEntity with HTTP status code
     * @throws NoteNotFoundException if the note with the given ID is not found
     */
    @DeleteMapping (value = "/noteDelete")
    public ResponseEntity<Note> deleteNote(@RequestParam String id) throws NoteNotFoundException {
        log.info("remove note with id:{}", id);
        return new ResponseEntity<>(noteService.deleteNote(id), OK);
    }

    /**
     * Retrieves all notes associated with a specific patient id from the database.
     * @param id the ID of the patient
     * @return ResponseEntity containing a list of all notes associated with the patient and HTTP status code
     * @throws NoteNotFoundException if there are no notes associated with the given patient ID
     */
    @GetMapping("/noteByPatientId")
    public ResponseEntity<List<Note>> getNoteByPatientId(@RequestParam Long id) throws NoteNotFoundException {
        log.info("get note by id :{} ", id);
        return new ResponseEntity<>(noteService.findNoteByPatientId(id), OK);
    }



}
