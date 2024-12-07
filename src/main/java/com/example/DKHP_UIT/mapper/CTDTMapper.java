package com.example.DKHP_UIT.mapper;

import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.response.ResponseCTDT;
import java.util.List;
import java.util.ArrayList;

@Component
public class CTDTMapper {
    public ResponseCTDT createResponseCTDT(List<List<Object>> list, int hocKy, String maKhoa) {
        List<String> listMaMonHoc = new ArrayList<>();
        List<String> listId = new ArrayList<>();
        List<Integer> listSoTinChiLT = new ArrayList<>();
        List<Integer> listSoTinChiTH = new ArrayList<>();
        List<String> listName = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listMaMonHoc.add((String) list.get(i).get(1));
            listId.add((String) list.get(i).get(2));
            listSoTinChiLT.add((Integer) list.get(i).get(3));
            listSoTinChiTH.add((Integer) list.get(i).get(4));
            listName.add((String) list.get(i).get(5));
        }

        return ResponseCTDT.builder()
                .hocKy(hocKy)
                .maKhoa(maKhoa)
                .listMaMonHoc(listMaMonHoc)
                .listId(listId)
                .listTinChiLT(listSoTinChiLT)
                .listTinChiTH(listSoTinChiTH)
                .listName(listName)
                .build();
    }
}
