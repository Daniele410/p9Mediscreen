package com.clientui.mediclientui.proxies;

import com.clientui.mediclientui.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "mediNote", url = "localhost:9002")
public interface NoteProxy {

    @GetMapping("/notes")
    public ResponseEntity<List<NoteBean>> getAllNotes();

    @GetMapping("/note")
    public ResponseEntity<NoteBean> getNoteById( @RequestParam String id);

    @PostMapping("/note")
    public ResponseEntity<NoteBean> addNote(@RequestBody NoteBean NewNote);

    @PutMapping("/note")
    public ResponseEntity<NoteBean> updateNote(@RequestBody NoteBean note);

    @DeleteMapping (value = "/noteDelete")
    public ResponseEntity<NoteBean> deleteNote(@RequestParam String id);

    @GetMapping("/noteByPatientId")
    public ResponseEntity<List<NoteBean>> getNoteByPatientId(@RequestParam Long id);

}
