package com.example.DKHP_UIT.support_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.entities.Subject;
import com.example.DKHP_UIT.entities.ctdt.CTDT;
import com.example.DKHP_UIT.repository.CTDTRepository;
import com.example.DKHP_UIT.repository.SubjectRepository;
import java.util.List;
import java.util.Set;

@Component
public class SupportCTDTService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CTDTRepository ctdtRepository;

    public boolean checkSubjectInCTDT(String subjectId, String maKhoa) {
        List<CTDT> list = this.ctdtRepository.getListCTDTByMaKhoa(maKhoa);
        // System.out.println(this.ctdtRepository.getListSubjects(maKhoa, 1));

        Subject subject = this.subjectRepository.findById(subjectId).get();

        for (int i = 0; i < list.size(); i++) {
            Set<Subject> listSubjects = this.ctdtRepository.getListSubjects(maKhoa, list.get(i).getId().getHocKy());
            if (listSubjects.contains(subject)) {
                return false;
            }
        }

        return true;
    }

    public Subject getSubjectFromMaMonHoc(String maMonHoc) {
        Subject subject = this.subjectRepository.checkMaMonHoc(maMonHoc);
        return subject;
    }

}
