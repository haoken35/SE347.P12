package com.example.DKHP_UIT.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateStaff {
    private String fullName;
    private String email;
    // 1 staff can be an Admin, if the flagAdmin is 1
    // in the System, we have 1 parent Admin who have permission to allow 1 staff to
    // admin or change 1 admin to staff.
    private Integer flagAdmin;
}
