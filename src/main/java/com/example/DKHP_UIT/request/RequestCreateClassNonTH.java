package com.example.DKHP_UIT.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCreateClassNonTH {
    private String subjectId;
    private int siSo;
    private Date start;
    private Date end;
    private int tietBatDau;
    private int thu;
    private String giangVienId;
    private String roomId;
    private int flagTH;
}
