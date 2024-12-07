package com.example.DKHP_UIT.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCTDT {
    private String maKhoa;
    private int hocKy;
    private List<String> listId;
    private List<String> listName;
    private List<String> listMaMonHoc;
    private List<Integer> listTinChiLT;
    private List<Integer> listTinChiTH;
}
