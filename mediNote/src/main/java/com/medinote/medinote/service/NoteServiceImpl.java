package com.medinote.medinote.service;

import com.medinote.medinote.Exceptions.NoteNotFoundException;
import com.medinote.medinote.model.Note;
import com.medinote.medinote.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements INoteService {

    private final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getAllNotes(){
        log.info("get all notes");
        return noteRepository.findAll();
    }
    @Override
    public Note findNoteById(String id) throws NoteNotFoundException {
        log.info("get note by id{},id");
        return noteRepository.findById(id).orElseThrow(()->
                new NoteNotFoundException("Id note: {} "+id + "not found"));
    }


    @Override
    public Note saveNote(Note note) {
        log.info("save note: {} {}" + note.getId());
        return noteRepository.insert(note);
    }

    @Override
    public Note updateNote(Note note) throws NoteNotFoundException {
        log.info("update note: {} {}" + note.getId());
        noteRepository.findById(note.getId()).orElseThrow(()->
                new NoteNotFoundException("id note: {} " + note.getId() + " not found!"));
        return noteRepository.save(note);
    }

    @Override
    public Note deleteNote(String id) throws NoteNotFoundException {
        log.info("note with: {} " + id , " delete");
        Note noteToDelete = findNoteById(id);
        noteRepository.delete(noteToDelete);
        return noteToDelete;
    }

    @Override
    public List<Note> findNoteByPatientId(Long id){
        List<Note> note = noteRepository.findByPatientId(id);
        return note;
    }



}
