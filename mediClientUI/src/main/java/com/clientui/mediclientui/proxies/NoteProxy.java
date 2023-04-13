package com.clientui.mediclientui.proxies;

import com.clientui.mediclientui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mediNote", url = "localhost:9002")
public interface NoteProxy {

    @GetMapping("/notes")
    public List<NoteBean> getAllNotes();

    @GetMapping("/note")
    public NoteBean getNoteById( @RequestParam String id);

    @PostMapping("/note")
    public NoteBean addNote(@RequestBody NoteBean NewNote);

    @PutMapping("/note")
    public NoteBean updateNote(@RequestBody NoteBean note);

    @DeleteMapping (value = "/noteDelete")
    public NoteBean deleteNote(@RequestParam String id);

    @GetMapping("/noteByPatientId")
    public List<NoteBean> getNoteByPatientId(@RequestParam Long id);

}