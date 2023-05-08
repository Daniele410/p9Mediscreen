package com.medinote.medinote.service;

import com.medinote.medinote.Exceptions.NoteNotFoundException;
import com.medinote.medinote.model.Note;
import com.medinote.medinote.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the INoteService interface
 * that provides methods to interact with the Note repository.
 */
@Service
public class NoteServiceImpl implements INoteService {

    /**
     * SLF4J Logger instance.
     */
    private final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

    /**
     * Instance of NoteRepository
     */
    private final NoteRepository noteRepository;

    /**
     * Constructor for NoteServiceImpl that injects the NoteRepository dependency.
     * @param noteRepository
     */
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Retrieves all notes stored in the NoteRepository.
     * @return A list of Note objects.
     */
    @Override
    public List<Note> getAllNotes(){
        log.info("get all notes");
        return noteRepository.findAll();
    }

    /**
     * Retrieves a Note object with a given id.
     * @param id The id of the note to retrieve.
     * @return A Note object.
     * @throws NoteNotFoundException if no note with the given id is found.
     */
    @Override
    public Note findNoteById(String id) throws NoteNotFoundException {
        log.info("get note by id{},id");
        return noteRepository.findById(id).orElseThrow(()->
                new NoteNotFoundException("Id note: {} "+id + "not found"));
    }


    /**
     * Saves a new note to the NoteRepository.
     * @param note The Note object to save.
     * @return The saved Note object.
     */
    @Override
    public Note saveNote(Note note) {
        log.info("save note: {}" + note.getPatientId());
        return noteRepository.insert(note);
    }

    /**
     * Updates an existing note in the NoteRepository.
     * @param note The updated Note object.
     * @return The updated Note object.
     * @throws NoteNotFoundException if no note with the given id is found.
     */
    @Override
    public Note updateNote(Note note) throws NoteNotFoundException {
        log.info("update note: {} {}" + note.getId());
        noteRepository.findById(note.getId()).orElseThrow(()->
                new NoteNotFoundException("id note: {} " + note.getId() + " not found!"));
        noteRepository.save(note);
        return note;
    }

    /**
     * Delete a note with a given id from the NoteRepository.
     * @param id The id of the note to delete.
     * @return The deleted Note object.
     * @throws NoteNotFoundException if no note with the given id is found.
     */
    @Override
    public Note deleteNote(String id) throws NoteNotFoundException {
        log.info("note with: {} " + id , " delete");
        Note noteToDelete = findNoteById(id);
        noteRepository.delete(noteToDelete);
        return noteToDelete;
    }

    /**
     * Retrieves all notes belonging to a patient with a given id.
     * @param id The id of the patient.
     * @return A list of Note objects.
     */
    @Override
    public List<Note> findNoteByPatientId(Long id) {
        List<Note> note = noteRepository.findByPatientId(id);
        return note;
    }




}
