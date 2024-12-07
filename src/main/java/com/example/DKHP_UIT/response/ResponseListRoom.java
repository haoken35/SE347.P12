package com.example.DKHP_UIT.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseListRoom {
    private String building;
    private List<List<Integer>> listRoomsInFloors;
}
