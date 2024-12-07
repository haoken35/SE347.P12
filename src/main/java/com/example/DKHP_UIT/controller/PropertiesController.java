package com.example.DKHP_UIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DKHP_UIT.service.PropertiesService;

@RestController
@RequestMapping("/properties")
public class PropertiesController {
    @Autowired
    private PropertiesService propertiesService;

    @PostMapping("/changeYearAndSemester")
    public ResponseEntity changeYearAndSemester(@RequestParam(name = "year") int year,
            @RequestParam(name = "semester") int semester) {
        return this.propertiesService.updateProperty1(year, semester);
    }
}
