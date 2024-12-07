package com.example.DKHP_UIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DKHP_UIT.request.RequestOpenSubject;
import com.example.DKHP_UIT.service.OpenSubjectService;

@RestController
@RequestMapping("/openSubject")
public class OpenSubjectController {
    @Autowired
    private OpenSubjectService openSubjectService;

    @PostMapping("/addListOpenSubject")
    public ResponseEntity addListSubject(@RequestBody RequestOpenSubject requestAddOpenSubject) {
        return this.openSubjectService.addOpenSubject(requestAddOpenSubject);
    }

    @PostMapping("/deleteOpenSubject")
    public ResponseEntity deleteOpenSubject(@RequestBody RequestOpenSubject requestOpenSubject) {
        return this.openSubjectService.deleteOpenSubject(requestOpenSubject);
    }
}
