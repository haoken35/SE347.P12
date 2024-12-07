package com.example.DKHP_UIT.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAddOpenSubject {
    private int hocKy;
    private int nam;
    private List<String> listTrue;
    private List<String> listWrong;
    private Map<String, Object> responseCode;
}
