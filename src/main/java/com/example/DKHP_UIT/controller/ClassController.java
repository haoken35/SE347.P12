package com.example.DKHP_UIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DKHP_UIT.request.RequestAddClassWithPractice;
import com.example.DKHP_UIT.request.RequestCreateClassNonTH;
import com.example.DKHP_UIT.request.RequestEditClass;
import com.example.DKHP_UIT.service.ClassService;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @PostMapping("/addClassNonTH")
    public ResponseEntity addClass(@RequestBody RequestCreateClassNonTH requestCreateClassNonTH) {
        System.out.println(requestCreateClassNonTH);
        return this.classService.addClass(requestCreateClassNonTH);
    }
}
