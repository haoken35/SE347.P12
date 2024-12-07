package com.example.DKHP_UIT.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionSubject extends RuntimeException {

    private ExceptionCode exceptionCode;
}