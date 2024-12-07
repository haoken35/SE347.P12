package com.example.DKHP_UIT.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalHandler {
    @ExceptionHandler(value = ExceptionStudent.class)
    public ResponseEntity handleExceptionUser(ExceptionStudent exceptionStudent) {
        return ResponseEntity.status(exceptionStudent.getExceptionCode().getStatus())
                .body(ExceptionCode.jsonOfExceptionCode(exceptionStudent.getExceptionCode()));
    }

    @ExceptionHandler(value = ExceptionStaff.class)
    public ResponseEntity handleExceptionStaff(ExceptionStaff exceptionStaff) {
        return ResponseEntity.status(exceptionStaff.getExceptionCode().getStatus())
                .body(ExceptionCode.jsonOfExceptionCode(exceptionStaff.getExceptionCode()));
    }

    @ExceptionHandler(value = ExceptionSubject.class)
    public ResponseEntity handleExceptionStaff(ExceptionSubject exceptionStaff) {
        return ResponseEntity.status(exceptionStaff.getExceptionCode().getStatus())
                .body(ExceptionCode.jsonOfExceptionCode(exceptionStaff.getExceptionCode()));
    }

    @ExceptionHandler(value = ExceptionUser.class)
    public ResponseEntity handleExceptionUser(ExceptionUser exceptionStaff) {
        return ResponseEntity.status(exceptionStaff.getExceptionCode().getStatus())
                .body(ExceptionCode.jsonOfExceptionCode(exceptionStaff.getExceptionCode()));
    }
}