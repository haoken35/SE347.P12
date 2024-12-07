package com.example.DKHP_UIT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DKHP_UIT.entities.GiangVien;
import com.example.DKHP_UIT.request.RequestAddGiangVien;
import com.example.DKHP_UIT.service.GiangVienService;

@RestController
@RequestMapping("/giangvien")
public class GiangVienController {
    @Autowired
    private GiangVienService giangVienService;

    @PostMapping("/addGiangVien")
    public ResponseEntity addGiangVien(@RequestBody List<RequestAddGiangVien> listGiangVien) {
        return this.giangVienService.addGiangVien(listGiangVien);
    }
}
