package com.example.DKHP_UIT.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestLogin {
    private String mssv;
    private String password;
}
