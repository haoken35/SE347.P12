package com.example.DKHP_UIT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.DKHP_UIT.entities.GiangVien;
import com.example.DKHP_UIT.repository.GiangVienRepository;
import com.example.DKHP_UIT.request.RequestAddGiangVien;

@Service
public class GiangVienService {
    @Autowired
    private GiangVienRepository giangVienRepository;

    public ResponseEntity addGiangVien(List<RequestAddGiangVien> listGiangVien) {
        System.out.println(listGiangVien);
        for (int i = 0; i < listGiangVien.size(); i++) {
            // build GiangVien
            GiangVien giangVien = GiangVien.builder()
                    .name(listGiangVien.get(i).getName())
                    .build();
            this.giangVienRepository.save(giangVien);
        }

        return ResponseEntity.ok().body("add giangvien successfully!");
    }
}
