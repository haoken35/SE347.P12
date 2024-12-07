package com.example.DKHP_UIT.support_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.entities.Subject;
import com.example.DKHP_UIT.entities.OpenSubject.OpenSubject;
import com.example.DKHP_UIT.entities.OpenSubject.OpenSubjectId;
import com.example.DKHP_UIT.repository.OpenSubjectRepository;
import com.example.DKHP_UIT.repository.SubjectRepository;

import lombok.Data;

import java.util.List;

@Component
@Data
public class SupportOpenSubjectService {
    @Value("${app.currentYear}")
    private int currentYear;

    @Value("${app.currentSemester}")
    private int currentSemester;

    @Autowired
    private OpenSubjectRepository openSubjectRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public boolean checkIfSubjectIsExistInListOpenSubject(String subjectId, List<String> listSubject) {
        for (int i = 0; i < listSubject.size(); i++) {
            if (subjectId.equals(listSubject.get(i))) {
                return false;
            }
        }
        return true;
    }

    public List<String> getListSubjectFollowingYearAndSemester() {
        List<String> listSubject = this.openSubjectRepository.getListOpenSubjectWithYearAndSemester(
                currentSemester,
                currentYear);
        return listSubject;
    }

    public void saveOpenSubject(String subjectId, int semester, int year) {
        Subject subject = this.subjectRepository.findById(subjectId).get();
        OpenSubject openSubject = OpenSubject.builder()
                .id(OpenSubjectId.builder()
                        .subject(subject)
                        .hocKy(semester)
                        .nam(year)
                        .build())
                .build();
        this.openSubjectRepository.save(openSubject);
    }

    public void delete1Subject(String subjectId) {
        // get subject
        Subject subject = this.subjectRepository.findById(subjectId).get();
        // get openSubjectId
        OpenSubjectId openSubjectId = OpenSubjectId.builder()
                .hocKy(currentSemester)
                .nam(currentYear)
                .subject(subject)
                .build();
        // get opensubject
        OpenSubject openSubject = this.openSubjectRepository.findById(openSubjectId).get();
        // delete opensubject
        this.openSubjectRepository.delete(openSubject);
    }
}
