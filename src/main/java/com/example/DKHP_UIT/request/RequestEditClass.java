package com.example.DKHP_UIT.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestEditClass {
    private String classId;
    private Integer siso; // min >= 30
    private Date startDate;
    private Date endDate;
    private Integer tietBatDau;
    private Integer thu;
    private Integer flagTH; // 0 1 2
    private String note;
    private String idLT;
    private int sectionOfDay;
    private String roomId;
    private String giangVienId;
    private String subjectId;
}
