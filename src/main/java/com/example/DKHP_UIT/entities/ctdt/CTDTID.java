package com.example.DKHP_UIT.entities.ctdt;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CTDTID implements Serializable {
    private Integer hocKy;
    private String maKhoa;
}
