package com.example.DKHP_UIT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.DKHP_UIT.entities.Staff;
import com.example.DKHP_UIT.exception.ExceptionCode;
import com.example.DKHP_UIT.exception.ExceptionUser;
import com.example.DKHP_UIT.mapper.StaffMapper;
import com.example.DKHP_UIT.repository.StaffRepository;
import com.example.DKHP_UIT.request.RequestCreateStaff;
import com.example.DKHP_UIT.response.ResponseCode;
import com.example.DKHP_UIT.support_service.SupportAdminService;
import com.example.DKHP_UIT.utils.UtilsHandleEmail;
import com.example.DKHP_UIT.utils.UtilsHandlePassword;

@Service
public class AdminService {
    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private SupportAdminService supportAdminService;

    @Autowired
    private UtilsHandlePassword utilsHandlePassword;

    @Autowired
    private UtilsHandleEmail utilsHandleEmail;

    @Autowired
    private StaffRepository staffRepository;

    public ResponseEntity createStaff(RequestCreateStaff requestCreateStaff) {
        // check staffId
        Staff st = this.staffRepository.getStaffByEmail(requestCreateStaff.getEmail());
        if (st != null) {
            throw new ExceptionUser(ExceptionCode.StaffExist);
        }
        // map
        Staff staff = this.staffMapper.convertRequest(requestCreateStaff);
        // create password
        String randomPassword = this.supportAdminService.createPassword();
        // encrypt password
        String encryptedPassword = this.utilsHandlePassword.encryptPassword(randomPassword);
        // save
        staff.setAccount(staff.getEmail());
        staff.setPassword(encryptedPassword);
        this.staffRepository.save(staff);
        // send notification via email.
        utilsHandleEmail.sendCreateAccount(staff.getEmail(), "TÀI KHOẢN VÀ MẬT KHẨU CHO NHÂN VIÊN - UIT",
                staff.getEmail(), randomPassword);

        return ResponseEntity.ok().body(ResponseCode.jsonOfResponseCode(ResponseCode.CreateAccountSuccessfully));
    }
}
