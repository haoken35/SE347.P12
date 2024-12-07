package com.example.DKHP_UIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DKHP_UIT.request.RequestCreateStaff;
import com.example.DKHP_UIT.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/createStaffAccount")
    public ResponseEntity createStaffAccount(@RequestBody RequestCreateStaff requestCreateStaff) {
        return this.adminService.createStaff(requestCreateStaff);
    }
}
