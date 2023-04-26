package com.mediassessment.mediassessment.proxies;


import com.mediassessment.mediassessment.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "mediNote", url = "localhost:8082")
public interface NoteProxy {


    /**
     * @param id patientId
     * @return List of notes by patientId
     */
    @GetMapping("/noteByPatientId")
    public List<NoteBean> getNoteByPatientId(@RequestParam Long id);

}
