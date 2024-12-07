package com.example.DKHP_UIT.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.entities.Role;
import com.example.DKHP_UIT.entities.Student;
import com.example.DKHP_UIT.repository.RoleRepository;
import com.example.DKHP_UIT.request.StudentRequestAdd;
import com.example.DKHP_UIT.request.StudentRequestEdit;

@Component
public class StudentMapper {
    @Autowired
    private RoleRepository roleRepository;

    public Student convertRequestAdd(StudentRequestAdd studentRequestAdd) {
        Role role = (Role) this.roleRepository.findById(1).get();
        System.out.println(role);

        return Student.builder()
                .mssv(studentRequestAdd.getMssv())
                .tenDayDu(studentRequestAdd.getTenDayDu())
                .tenKhoa(studentRequestAdd.getTenKhoa())
                .tenNganh(studentRequestAdd.getTenNganh())
                .diaChiChiTiet(studentRequestAdd.getDiaChiChiTiet())
                .tinh_thanhPho(studentRequestAdd.getTinh_thanhPho())
                .quan_huyen(studentRequestAdd.getQuan_huyen())
                .xa_phuong(studentRequestAdd.getXa_phuong())
                .gioiTinh(studentRequestAdd.getGioiTinh())
                .noiSinh(studentRequestAdd.getNoiSinh())
                .diaChiChiTiet1(studentRequestAdd.getDiaChiChiTiet1())
                .tinh_thanhPho1(studentRequestAdd.getTinh_thanhPho1())
                .quan_huyen1(studentRequestAdd.getQuan_huyen1())
                .xa_phuong1(studentRequestAdd.getXa_phuong1())
                .ngaySinh(studentRequestAdd.getNgaySinh())
                .cmnd(studentRequestAdd.getCmnd())
                .emailCaNhan(studentRequestAdd.getEmailCaNhan())
                .password(studentRequestAdd.getPassword())
                .role(role)
                .build();
    }

    public void convertRequestEdit(StudentRequestEdit studentRequestEdit, Student student) {
        student.setTenDayDu(studentRequestEdit.getTenDayDu());
        student.setTenKhoa(studentRequestEdit.getTenKhoa());
        student.setTenNganh(studentRequestEdit.getTenNganh());
        student.setDiaChiChiTiet(studentRequestEdit.getDiaChiChiTiet());
        student.setTinh_thanhPho(studentRequestEdit.getTinh_thanhPho());
        student.setQuan_huyen(studentRequestEdit.getQuan_huyen());
        student.setXa_phuong(studentRequestEdit.getXa_phuong());
        student.setGioiTinh(studentRequestEdit.getGioiTinh());
        student.setNoiSinh(studentRequestEdit.getNoiSinh());
        student.setDiaChiChiTiet1(studentRequestEdit.getDiaChiChiTiet1());
        student.setTinh_thanhPho1(studentRequestEdit.getTinh_thanhPho1());
        student.setQuan_huyen1(studentRequestEdit.getQuan_huyen1());
        student.setXa_phuong1(studentRequestEdit.getXa_phuong1());
        student.setNgaySinh(studentRequestEdit.getNgaySinh());
        student.setCmnd(studentRequestEdit.getCmnd());
        student.setEmailCaNhan(studentRequestEdit.getEmailCaNhan());
    }
}
