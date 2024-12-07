package com.example.DKHP_UIT.response;

import java.util.List;

import com.example.DKHP_UIT.request.CTDTRequestAddSubject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAddSubjectToCTDT {
    private int code;
    private String message;
    private List<String> listMaMonHocTrue;
    private List<String> listMaMonHocWrong;

    public static ResponseAddSubjectToCTDT createResponseAddSubjectToCTDT(List<String> listTrue,
            List<String> listWrong) {
        return ResponseAddSubjectToCTDT.builder()
                .code(1000)
                .message("add subject to ctdt successfully!")
                .listMaMonHocTrue(listTrue)
                .listMaMonHocWrong(listWrong)
                .build();
    }
}
