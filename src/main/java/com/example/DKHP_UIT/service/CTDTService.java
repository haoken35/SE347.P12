package com.example.DKHP_UIT.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.DKHP_UIT.entities.Student;
import com.example.DKHP_UIT.entities.Subject;
import com.example.DKHP_UIT.entities.ctdt.CTDT;
import com.example.DKHP_UIT.entities.ctdt.CTDTID;
import com.example.DKHP_UIT.mapper.CTDTMapper;
import com.example.DKHP_UIT.repository.CTDTRepository;
import com.example.DKHP_UIT.repository.StudentRepository;
import com.example.DKHP_UIT.repository.SubjectRepository;
import com.example.DKHP_UIT.request.CTDTRequestAddSubject;
import com.example.DKHP_UIT.request.RequestDeleteSubjectFromAllSubject;
import com.example.DKHP_UIT.response.ResponseAddSubjectToCTDT;
import com.example.DKHP_UIT.response.ResponseCode;
import com.example.DKHP_UIT.support_service.SupportCTDTService;
import com.example.DKHP_UIT.utils.UtilsHandleCookie;
import com.example.DKHP_UIT.utils.UtilsHandleJwtToken;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CTDTService {
    @Autowired
    private CTDTRepository ctdtRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SupportCTDTService supportCTDTService;

    @Autowired
    private UtilsHandleCookie utilsHandleCookie;

    @Autowired
    private UtilsHandleJwtToken utilsHandleJwtToken;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CTDTMapper ctdtMapper;

    public ResponseEntity addSubject(List<CTDTRequestAddSubject> ctdtRequestAddSubject) {
        List<String> listMaMonHocTrue = new ArrayList<>();
        List<String> listMaMonHocWrong = new ArrayList<>();
        for (int i = 0; i < ctdtRequestAddSubject.size(); i++) {
            CTDTID ctdtid = CTDTID.builder()
                    .hocKy(ctdtRequestAddSubject.get(i).getHocKy())
                    .maKhoa(ctdtRequestAddSubject.get(i).getMaKhoa())
                    .build();

            CTDT ctdt = this.ctdtRepository.findById(ctdtid).get();

            Subject subject = this.supportCTDTService
                    .getSubjectFromMaMonHoc(ctdtRequestAddSubject.get(i).getMaMonHoc());
            System.out.println(ctdtRequestAddSubject.get(i).getMaMonHoc());
            // kiểm tra thử xem có tồn tại môn học trong chương trình học chưa.
            try {
                if (this.supportCTDTService.checkSubjectInCTDT(subject.getId(), ctdtid.getMaKhoa()) == false) {
                    listMaMonHocWrong.add(subject.getMaMonHoc());
                    continue;
                }
            } catch (Exception e) {
                System.out.println(ctdtRequestAddSubject.get(i).getMaMonHoc());
            }
            listMaMonHocTrue.add(subject.getMaMonHoc());

            ctdt.getSubjects().add(subject);

            this.ctdtRepository.save(ctdt);
        }
        return ResponseEntity.ok()
                .body(ResponseAddSubjectToCTDT.createResponseAddSubjectToCTDT(listMaMonHocTrue, listMaMonHocWrong));
    }

    public ResponseEntity createCTDT(String maKhoa, String tenKhoa) {
        for (int i = 1; i <= 8; i++) {
            CTDT ctdt = CTDT.builder()
                    .id(CTDTID.builder()
                            .hocKy(i)
                            .maKhoa(maKhoa)
                            .build())
                    .tenKhoa(tenKhoa)
                    .build();
            this.ctdtRepository.save(ctdt);
        }
        return ResponseEntity.ok().body("create 8 " + maKhoa + " " + tenKhoa);
    }

    // public ResponseEntity deleteSubject(CTDTRequestDeleteSubject
    // ctdtRequestDeleteSubject) {
    // List<CTDT> listCtdts =
    // this.ctdtRepository.getListCTDTByMaKhoa(ctdtRequestDeleteSubject.getMaKhoa());

    // Set<Set<Subject>> listSubjectForAllHocKy = new HashSet<>();

    // for (int i = 0; i < listCtdts.size(); i++){
    // listSubjectForAllHocKy.add(this.ctdtRepository.getListSubjects(ctdtRequestDeleteSubject.getMaKhoa(),
    // i + 1));
    // }

    // return ResponseEntity.ok().body()
    // }

    public ResponseEntity getCTDT(int hocKy, HttpServletRequest httpServletRequest) {
        // get student from cookie
        String token = this.utilsHandleCookie.getCookie(httpServletRequest, "jwtToken");
        String studentId = this.utilsHandleJwtToken.verifyToken(token);
        Student student = this.studentRepository.findById(studentId).get();
        // get maKhoa from student
        String tenKhoa = student.getTenKhoa();
        String maKhoa = "";
        if (tenKhoa.equals("Công Nghệ Phần Mềm")) {
            maKhoa = "SE";
        } else if (tenKhoa.equals("Hệ thống thông tin")) {
            maKhoa = "IS";
        } else if (tenKhoa.equals("Khoa Học Máy Tính")) {
            maKhoa = "CS";
        } else {
            maKhoa = "CE";
        }
        // get CTDT from (maKhoa and hocKy)
        List<List<Object>> list = this.ctdtRepository.getCTDT(maKhoa, hocKy);
        // get result
        return ResponseEntity.ok().body(ctdtMapper.createResponseCTDT(list, hocKy, maKhoa));
    }

    public ResponseEntity deleteSubject(String[] subjectIds) {
        for (int i = 0; i < subjectIds.length; i++) {
            this.ctdtRepository.deleteSubjectInRepository(subjectIds[i]);
        }
        return ResponseEntity.ok().body(ResponseCode.DeleteSubject);
    }
}
