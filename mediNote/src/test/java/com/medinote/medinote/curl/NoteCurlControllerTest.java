package com.medinote.medinote.curl;

import com.medinote.medinote.model.Note;
import com.medinote.medinote.model.dto.NoteDto;
import com.medinote.medinote.service.INoteService;
import com.medinote.medinote.service.NoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class NoteCurlControllerTest {

    @InjectMocks
    NoteCurlController noteCurlController;

    @Mock
    NoteServiceImpl noteService;


    @Test
    void addNoteCurlShouldReturnModifiedModelAndView() {

        //Given
        NoteDto noteDto= new NoteDto();
        noteDto.setPatId("1");
        noteDto.setE("this message");
        Note note= new Note();
        note.setPatientId(Long.valueOf(noteDto.getPatId()));
        note.setMessage(noteDto.getE());
        when(noteService.saveNote(any())).thenReturn(note);

        //When
        ResponseEntity<Note> response = noteCurlController.addNoteCurl(noteDto);

        //Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(note, response.getBody());

    }
}