package com.example.DKHP_UIT.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    LoginSuccessfully(1000, "Login successfully!", HttpStatus.ACCEPTED),
    CreateAccountSuccessfully(1000, "Create account successfully!", HttpStatus.ACCEPTED),
    CreateSubjectSuccessfully(1000, "Create subject successfully!", HttpStatus.ACCEPTED),
    CreateStudentSuccessfully(1000, "Create student successfully!", HttpStatus.ACCEPTED),
    EditStudentSuccessfully(1000, "Edit student successfully!", HttpStatus.ACCEPTED),
    CloseDKHP(1000, "Close dkhp successfully!", HttpStatus.ACCEPTED),
    OpenDKHP(1000, "Open dkhp successfully!", HttpStatus.ACCEPTED),
    CreateAllAccount(1000, "Create all account successfully!", HttpStatus.ACCEPTED),
    DeleteStudent(1000, "Delete student successfully!", HttpStatus.ACCEPTED),
    DeleteSubject(1000, "Delete subject successfully!", HttpStatus.ACCEPTED),
    EditSubject(1000, "Edit subject successfully!", HttpStatus.ACCEPTED),
    AddOpenSubject(1000, "Add open subject successfully!", HttpStatus.ACCEPTED),
    SetSemesterAndYear(1000, "Set semester and year successfully!", HttpStatus.ACCEPTED),
    AddRoom(1000, "Add room successfully!", HttpStatus.ACCEPTED),
    AddClassWithOutTH(1000, "Add successfully!", HttpStatus.ACCEPTED),
    AddClassWithInTH(1000, "Add successfully!", HttpStatus.ACCEPTED),
    DeleteClass(1000, "Delete class successfully!", HttpStatus.ACCEPTED),
    EditClass(1000, "Edit class successfully!", HttpStatus.ACCEPTED);

    private int code;
    private String message;
    private HttpStatusCode status;

    public static Map<String, Object> jsonOfResponseCode(ResponseCode exceptionCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", exceptionCode.getCode());
        map.put("message", exceptionCode.getMessage());
        return map;
    }
}
