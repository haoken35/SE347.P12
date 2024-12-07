package com.example.DKHP_UIT.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CTDTRequestAddSubject {
    private String maKhoa;
    private String maMonHoc;
    private int hocKy;
}
