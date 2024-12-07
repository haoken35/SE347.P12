package com.example.DKHP_UIT.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseList {
    private String mssv;
    private String tenDayDu;
    private String tenKhoa;
    private String tenNganh;
    private String gioiTinh;

    public static List<StudentResponseList> createListStudentResponseList(List<List<String>> list) {
        List<StudentResponseList> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(create1StudentResponseList(list.get(i)));
        }
        return list1;
    }

    public static StudentResponseList create1StudentResponseList(List<String> list) {
        return StudentResponseList.builder()
                .mssv(list.get(0))
                .tenDayDu(list.get(1))
                .tenKhoa(list.get(2))
                .tenNganh(list.get(3))
                .gioiTinh(list.get(4))
                .build();
    }
}
