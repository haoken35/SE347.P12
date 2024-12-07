package com.example.DKHP_UIT.support_service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.entities.Subject;
import com.example.DKHP_UIT.entities.OpenSubject.OpenSubject;
import com.example.DKHP_UIT.entities.OpenSubject.OpenSubjectId;
import com.example.DKHP_UIT.repository.CTDTRepository;
import com.example.DKHP_UIT.repository.ClassRepository;
import com.example.DKHP_UIT.repository.OpenSubjectRepository;

@Component
public class SupportSubjectService {
    @Value("${app.currentYear}")
    private int currentYear;

    @Value("${app.currentSemester}")
    private int currentSemester;

    @Autowired
    private OpenSubjectRepository openSubjectRepository;

    @Autowired
    private CTDTRepository ctdtRepository;

    @Autowired
    private ClassRepository classRepository;

    public boolean checkOpenSubject(Subject subject) { // true is that open subject have this subject.
        Optional<OpenSubject> optional = this.openSubjectRepository.findById(OpenSubjectId.builder()
                .subject(subject)
                .nam(currentYear)
                .hocKy(currentSemester)
                .build());
        if (optional.isPresent()) {
            return true;
        }
        return false;
    }

    public boolean check1SubjectInCTDT(String subjectId) { // true is that ctdt have this subject
        int count = this.ctdtRepository.check1SubjectInCTDT(subjectId);
        if (count == 0) {
            return false;
        }
        return true;
    }

    public boolean checkSubjectClass(String subjectId) { // true is that the classes contain at least 1 subject class.
        int count = this.classRepository.getClassFollowingBySubjectId(subjectId);
        if (count == 0) {
            return false;
        }
        return true;
    }

}
