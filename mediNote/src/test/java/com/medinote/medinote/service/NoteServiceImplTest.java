package com.medinote.medinote.service;

import com.medinote.medinote.Exceptions.NoteNotFoundException;
import com.medinote.medinote.model.Note;
import com.medinote.medinote.repository.NoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {
    @InjectMocks
    NoteServiceImpl noteService;

    @Mock
    NoteRepository noteRepository;

    @Test
    void getAllNotes() {
        //Given
        List<Note> allNotes = List.of(
                new Note("1234123", 1, "Bros", LocalDate.of(1084, 9, 10)),
                new Note("9876543", 2, "Luigi", LocalDate.of(1084, 12, 12)));
        when(noteRepository.findAll()).thenReturn(allNotes);

        //When
        List<Note> noteResult = noteService.getAllNotes();

        //Then
        Assertions.assertEquals(noteResult.size(), 2);

    }

    @Test
    void findNoteById() throws NoteNotFoundException {
        //Given

        Note note1 = new Note("1234123", 1, "This is a message", LocalDate.of(1084, 9, 10));
        Note note2 = new Note("9876543", 2, "Ok", LocalDate.of(1084, 12, 12));
        when(noteRepository.findById("1234123")).thenReturn(Optional.of(note1));

        //When
        Note noteResult = noteService.findNoteById("1234123");

        //Then
        Assertions.assertEquals(noteResult.getMessage(), "This is a message");

    }

    @Test
    void findNoteByIdShouldReturnException() throws NoteNotFoundException {
        //Given // When //Then
        assertThrows(NoteNotFoundException.class, () -> noteService.findNoteById("00000"));
    }

    @Test
    void saveNote() {
        //Given

        Note note1 = new Note("1234123", 1, "This is a message", LocalDate.of(1084, 9, 10));
        Note note2 = new Note("9876543", 2, "Ok", LocalDate.of(1084, 12, 12));
        when(noteRepository.insert(note1)).thenReturn(note1);

        //When
        Note noteResult = noteService.saveNote(note1);

        //Then
        Assertions.assertEquals(noteResult.getPatientId(), 1L);
        verify(noteRepository, Mockito.times(1)).insert(note1);

    }

    @Test
    void updateNote() throws NoteNotFoundException {
        //Given
        Note note = new Note();
        note.setId("1234123");
        note.setPatientId(1L);
        note.setMessage("This is a message");
        note.setDate(LocalDate.of(1084, 9, 10));
        when(noteRepository.findById(note.getId())).thenReturn(Optional.of(note));

        //When
        Note noteUpdate = noteService.updateNote(note);

        //Then
        verify(noteRepository).findById(note.getId());
        verify(noteRepository, Mockito.times(1)).save(note);
        assertEquals(noteUpdate, note);

    }

    @Test
    void updateNoteShouldReturnException() throws NoteNotFoundException {
        //Given
        Note note = new Note("1234123", 1, "This is a message", LocalDate.of(1084, 9, 10));

        // When //Then
        assertThrows(NoteNotFoundException.class, () -> noteService.updateNote(note));

    }

    @Test
    void deleteNote() throws NoteNotFoundException {
        //Given
        Note note = new Note("1234123", 1, "This is a message", LocalDate.of(1084, 9, 10));
        when(noteRepository.findById(note.getId())).thenReturn(Optional.of(note));

        //When
        Note noteToDelete = noteService.deleteNote(note.getId());

        //Then
        verify(noteRepository).findById(note.getId());
        verify(noteRepository, Mockito.times(1)).delete(note);
        assertEquals(noteToDelete, note);
    }

    @Test
    void findNoteByPatientId() {
        //Given
        List<Note> allNotes = List.of(
                new Note("1234123", 1, "Bros", LocalDate.of(1084, 9, 10)),
                new Note("9876543", 2, "Luigi", LocalDate.of(1084, 12, 12)));
        when(noteRepository.findByPatientId(allNotes.get(0).getPatientId())).thenReturn(allNotes);

        //When
        List<Note> noteResult = noteService.findNoteByPatientId(1L);

        //Then
        Assertions.assertEquals(noteResult.get(0).getDate(), LocalDate.of(1084, 9, 10));

    }
}