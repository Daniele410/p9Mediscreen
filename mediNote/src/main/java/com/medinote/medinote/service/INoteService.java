package com.medinote.medinote.service;

import com.medinote.medinote.Exceptions.NoteNotFoundException;
import com.medinote.medinote.model.Note;

import java.util.List;

public interface INoteService {
    List<Note> getAllNotes();

    Note findNoteById(String id) throws NoteNotFoundException;

    Note saveNote(Note note);

    Note updateNote(Note note) throws NoteNotFoundException;

    Note deleteNote(String id) throws NoteNotFoundException;
}
