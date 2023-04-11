package com.medinote.medinote.controller;

import com.medinote.medinote.Exceptions.NoteNotFoundException;
import com.medinote.medinote.model.Note;
import com.medinote.medinote.service.INoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * NoteController
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

    public NoteController(INoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * method to get all notes
     * @return all notes
     */
    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes() {
        log.info("get all notes");
        return new ResponseEntity<>(noteService.getAllNotes(), OK);
    }

    /**
     * @param id
     * @return note
     * @throws NoteNotFoundException
     */
    @GetMapping("/note")
    public ResponseEntity<Note> getNoteById( @RequestParam String id) throws NoteNotFoundException {
        log.info("get note by id :{} ", id);
        return new ResponseEntity<>(noteService.findNoteById((id)), OK);
    }

    @PostMapping("/note")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        log.info("save note :{}", note.getId());

        return new ResponseEntity<>(noteService.saveNote(note), CREATED);
    }


    /**
     * put method to upload note
     * @param note
     * @return note update
     */
    @PutMapping("/note")
    public ResponseEntity<Note> updateNote(@RequestBody Note note) throws NoteNotFoundException {
        log.info("update note with id :{} ", note.getId());
        return new ResponseEntity<>(noteService.updateNote(note), OK );
    }

    /**
     * delete method to delete patient by id
     * @param id
     * @return OK
     * @throws NoteNotFoundException
     */
    @DeleteMapping (value = "/noteDelete")
    public ResponseEntity<Note> deleteNote(@RequestParam String id) throws NoteNotFoundException {
        log.info("remove note with id:{}", id);
        return new ResponseEntity<>(noteService.deleteNote(id), OK);
    }

    @GetMapping("/noteByPatientId")
    public ResponseEntity<List<Note>> getNoteByPatientId(@RequestParam Long id)  {
        log.info("get note by id :{} ", id);

        return new ResponseEntity<>(noteService.findNoteByPatientId(id), OK);
    }



}
