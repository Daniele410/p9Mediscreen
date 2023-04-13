package com.medinote.medinote.controller;

import com.medinote.medinote.Exceptions.NoteNotFoundException;
import com.medinote.medinote.model.Note;
import com.medinote.medinote.service.NoteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteControllerTest {
    @Mock
    NoteServiceImpl noteService;
    @InjectMocks
    NoteController noteController;

    @Test
    void getAllNotes() {
        //Given
        List<Note> allNotes = List.of(
                new Note("1234123", 1, "Bros", LocalDate.of(1084, 9, 10)),
                new Note("9876543", 2, "Luigi", LocalDate.of(1084, 12, 12)));

        when(noteService.getAllNotes()).thenReturn(allNotes);

        //When
        ResponseEntity<List<Note>> response = noteController.getAllNotes();

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allNotes, response.getBody());
    }

    @Test
    void getNoteById() throws NoteNotFoundException {
        //Given
        List<Note> allNotes = List.of(
                new Note("1234123", 1, "Bros", LocalDate.of(1084, 9, 10)),
                new Note("9876543", 2, "Luigi", LocalDate.of(1084, 12, 12)));

        when(noteService.findNoteById("1234123")).thenReturn(allNotes.get(0));

        //When
        ResponseEntity<Note> response = noteController.getNoteById("1234123");

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allNotes.get(0), response.getBody());
    }

    @Test
    void addNote() {
        //Given
        List<Note> allNotes = List.of(
                new Note("1234123", 1, "Bros", LocalDate.of(1084, 9, 10)),
                new Note("9876543", 2, "Luigi", LocalDate.of(1084, 12, 12)));

        when(noteService.saveNote(allNotes.get(0))).thenReturn(allNotes.get(0));

        //When
        ResponseEntity<Note> response = noteController.addNote(allNotes.get(0));

        //Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(allNotes.get(0), response.getBody());
    }

    @Test
    void updateNote() throws NoteNotFoundException {
        //Given
        List<Note> allNotes = List.of(
                new Note("1234123", 1, "Bros", LocalDate.of(1084, 9, 10)),
                new Note("9876543", 2, "Luigi", LocalDate.of(1084, 12, 12)));

        when(noteService.updateNote(allNotes.get(0))).thenReturn(allNotes.get(0));

        //When
        ResponseEntity<Note> response = noteController.updateNote(allNotes.get(0));

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allNotes.get(0), response.getBody());
    }

    @Test
    void deleteNote() throws NoteNotFoundException {
        //Given
        List<Note> allNotes = List.of(
                new Note("1234123", 1, "Bros", LocalDate.of(1084, 9, 10)),
                new Note("9876543", 2, "Luigi", LocalDate.of(1084, 12, 12)));

        when(noteService.deleteNote("1234123")).thenReturn(allNotes.get(0));

        //When
        ResponseEntity<Note> response = noteController.deleteNote(allNotes.get(0).getId());

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allNotes.get(0), response.getBody());
    }

    @Test
    void getNoteByPatientId() {
        //Given
        List<Note> allNotes = List.of(
                new Note("1234123", 1, "Bros", LocalDate.of(1084, 9, 10)));

        when(noteService.findNoteByPatientId(allNotes.get(0).getPatientId())).thenReturn(allNotes);

        //When
        ResponseEntity<List<Note>> response = noteController.getNoteByPatientId(1L);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allNotes.size(), response.getBody().size());
    }
}