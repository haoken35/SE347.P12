package com.example.DKHP_UIT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.DKHP_UIT.repository.OpenSubjectRepository;
import com.example.DKHP_UIT.request.RequestOpenSubject;
import com.example.DKHP_UIT.response.ResponseAddOpenSubject;
import com.example.DKHP_UIT.response.ResponseCode;
import com.example.DKHP_UIT.response.ResponseDeleteSubjectFromAllSubject;
import com.example.DKHP_UIT.support_service.SupportOpenSubjectService;
import com.example.DKHP_UIT.support_service.SupportSubjectService;

import java.util.List;
import java.util.ArrayList;

@Service
public class OpenSubjectService {

    @Autowired
    private SupportOpenSubjectService supportOpenSubjectService;

    @Autowired
    private SupportSubjectService supportSubjectService;

    public ResponseEntity addOpenSubject(RequestOpenSubject requestAddOpenSubject) {
        // get List opensubject with the condition (year and semester)
        List<String> listSubjectId = this.supportOpenSubjectService.getListSubjectFollowingYearAndSemester();

        List<String> listTrue = new ArrayList<>();
        List<String> listWrong = new ArrayList<>();
        for (int i = 0; i < requestAddOpenSubject.getListSubjectId().size(); i++) {
            // check each subject in listsubject
            boolean check = this.supportOpenSubjectService.checkIfSubjectIsExistInListOpenSubject(
                    requestAddOpenSubject.getListSubjectId().get(i), listSubjectId);
            if (check == true) {
                this.supportOpenSubjectService.saveOpenSubject(requestAddOpenSubject.getListSubjectId().get(i),
                        this.supportOpenSubjectService.getCurrentSemester(),
                        this.supportOpenSubjectService.getCurrentYear());
                listTrue.add(requestAddOpenSubject.getListSubjectId().get(i));
            } else {
                listWrong.add(requestAddOpenSubject.getListSubjectId().get(i));
            }
        }

        // create response
        ResponseAddOpenSubject responseAddOpenSubject = ResponseAddOpenSubject.builder()
                .hocKy(this.supportOpenSubjectService.getCurrentSemester())
                .nam(this.supportOpenSubjectService.getCurrentYear())
                .listTrue(listTrue)
                .listWrong(listWrong)
                .responseCode(ResponseCode.jsonOfResponseCode(ResponseCode.AddOpenSubject))
                .build();

        // return
        return ResponseEntity.ok().body(responseAddOpenSubject);
    }

    public ResponseEntity deleteOpenSubject(RequestOpenSubject requestOpenSubject) {
        List<String> listTrue = new ArrayList<>();
        List<String> listWrong = new ArrayList<>();
        List<String> listProblem = new ArrayList<>();
        for (int i = 0; i < requestOpenSubject.getListSubjectId().size(); i++) {
            // check class
            if (this.supportSubjectService.checkSubjectClass(requestOpenSubject.getListSubjectId().get(i)) == true) {
                listProblem.add("class");
                listWrong.add(requestOpenSubject.getListSubjectId().get(i));
                continue;
            }
            listTrue.add(requestOpenSubject.getListSubjectId().get(i));
            this.supportOpenSubjectService.delete1Subject(requestOpenSubject.getListSubjectId().get(i));
        }

        ResponseDeleteSubjectFromAllSubject response = ResponseDeleteSubjectFromAllSubject.builder()
                .code(1000)
                .listTrue(listTrue)
                .listWrong(listWrong)
                .listProblem(listProblem)
                .message("Delete successfully!")
                .build();
        return ResponseEntity.ok().body(response);
    }
}
