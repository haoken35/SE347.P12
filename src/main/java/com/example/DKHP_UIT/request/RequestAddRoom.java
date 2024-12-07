package com.example.DKHP_UIT.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddRoom {
    private String building;
    private Integer floor;
    private Integer number;
}
