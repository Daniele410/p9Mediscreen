package com.clientui.mediclientui.proxies;

import com.clientui.mediclientui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * This interface represents a FeignClient for the mediNote service
 * It provides methods to retrieve, add, update, and delete notes
 */
@FeignClient(name = "note", url = "${feign.mediNote.url}")
public interface NoteProxy {


    /**
     * Retrieves all notes from the Note service.
     *@return A List of NoteBean objects representing all notes.
     */
    @GetMapping("/notes")
    public List<NoteBean> getAllNotes();

    /**
     * Retrieves a note with a specified id from the mediNote service.
     * @param id The id of the note to retrieve.
     * @return A NoteBean object representing the retrieved note.
     */
    @GetMapping("/note")
    public NoteBean getNoteById( @RequestParam String id);

    /**
     * Adds a new note to the mediNote service.
     * @param NewNote A NoteBean object representing the new note to add.
     * @return A NoteBean object representing the added note.
     */
    @PostMapping("/note")
    public NoteBean addNote(@RequestBody NoteBean NewNote);

    /**
     * Updates an existing note in the mediNote service.
     * @param note A NoteBean object representing the note to update.
     * @return A NoteBean object representing the updated note.
     */
    @PutMapping("/note")
    public NoteBean updateNote(@RequestBody NoteBean note);

    /**
     * Deletes a note with a specified id from the mediNote service.
     * @param id The id of the note to delete.
     * @return A NoteBean object representing the deleted note.
     */
    @DeleteMapping (value = "/noteDelete")
    public NoteBean deleteNote(@RequestParam String id);

    /**
     * @param id patientId
     * @return List of notes by patientId
     */
    @GetMapping("/noteByPatientId")
    public List<NoteBean> getNoteByPatientId(@RequestParam Long id);

}
