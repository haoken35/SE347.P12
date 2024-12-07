package com.example.DKHP_UIT.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SubjectRequest {
    private String maMonHoc;
    private String tenMonHoc;
    private List<String> maMonHocTruoc;
    private String loaiMonHoc;
    private int soTinChiLT;
    private int soTinChiTH;
    private String maKhoa;
}
