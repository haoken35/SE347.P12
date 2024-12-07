package com.example.DKHP_UIT.mapper;

import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.entities.Staff;
import com.example.DKHP_UIT.request.RequestCreateStaff;

@Component
public class StaffMapper {
    public Staff convertRequest(RequestCreateStaff requestCreateStaff) {
        return Staff.builder()
                .fullName(requestCreateStaff.getFullName())
                .email(requestCreateStaff.getEmail())
                .flagAdmin(requestCreateStaff.getFlagAdmin())
                .build();
    }
}
