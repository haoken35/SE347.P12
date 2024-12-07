package com.example.DKHP_UIT.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddClassWithPractice {
    // theory class
    private RequestCreateClassNonTH requestCreateClassNonTH;
    // practice class
    private int flagTH;
    // siso is created based on flagTH and siso in theory class
    private Date start;
    private Date end;
    private int sectionOfDay; // 1 is morning, 2 is afternoon
    private int thu;
    private String giangVienId;
    private String roomId;
}
