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
public class ResponseDKHP {
    private int code;
    private String message;
    private List<String> listTrue;
    private List<String> listWrong;
    private List<String> listProblem;
}
