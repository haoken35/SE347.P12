package com.example.DKHP_UIT.mapper;

import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.entities.Subject;
import com.example.DKHP_UIT.request.SubjectRequest;

@Component
public class SubjectMapper {
    public static Subject convertRequest(SubjectRequest subjectRequest) {
        return Subject.builder()
                .maMonHoc(subjectRequest.getMaMonHoc())
                .tenMonHoc(subjectRequest.getTenMonHoc())
                .dsMaMonHocTruoc(subjectRequest.getMaMonHocTruoc())
                .loaiMonHoc(subjectRequest.getLoaiMonHoc())
                .soTinChiLT(subjectRequest.getSoTinChiLT())
                .soTinChiTH(subjectRequest.getSoTinChiTH())
                .maKhoa(subjectRequest.getMaKhoa())
                .build();
    }
}
