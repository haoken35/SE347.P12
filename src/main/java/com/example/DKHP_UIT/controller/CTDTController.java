package com.example.DKHP_UIT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DKHP_UIT.request.CTDTRequestAddSubject;
import com.example.DKHP_UIT.service.CTDTService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/CTDT")
public class CTDTController {
    @Autowired
    private CTDTService ctdtService;

    @PostMapping("/addSubject")
    public ResponseEntity addSubject(@RequestBody List<CTDTRequestAddSubject> listCtdtRequestAddSubjects) {
        System.out.println(listCtdtRequestAddSubjects.size());
        return this.ctdtService.addSubject(listCtdtRequestAddSubjects);
    }

    @PostMapping("/createCTDT")
    public ResponseEntity createCTDT(@RequestParam(name = "maKhoa") String maKhoa,
            @RequestParam(name = "tenKhoa") String tenKhoa) {
        return ctdtService.createCTDT(maKhoa, tenKhoa);
    }

    @PostMapping("/deleteSubject")
    public ResponseEntity deleteSubject(@RequestBody String[] subjectIds) {
        return ctdtService.deleteSubject(subjectIds);
    }

    @GetMapping("/getCTDT")
    public ResponseEntity getCTDT(@RequestParam(name = "hocKy") int hocKy, HttpServletRequest httpServletRequest) {
        return ctdtService.getCTDT(hocKy, httpServletRequest);
    }
}
