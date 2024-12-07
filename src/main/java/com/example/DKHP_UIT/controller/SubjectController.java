package com.example.DKHP_UIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.example.DKHP_UIT.entities.Subject;
import com.example.DKHP_UIT.repository.SubjectRepository;
import com.example.DKHP_UIT.request.RequestDeleteSubjectFromAllSubject;
import com.example.DKHP_UIT.request.SubjectRequest;
import com.example.DKHP_UIT.service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping("/createSubject")
    public ResponseEntity createSubject(@RequestBody List<SubjectRequest> list) {

        return subjectService.createListSubject(list);
    }

    @PostMapping("/create1Subject")
    public ResponseEntity create1Subject(@RequestBody SubjectRequest subjectRequest) {
        return subjectService.create1Subject(subjectRequest);
    }

    @PostMapping("/delete1Subject")
    public ResponseEntity deleteSubject(@RequestParam(name = "maMonHoc") String maMonHoc,
            @RequestParam(name = "maKhoa") String maKhoa) {
        return subjectService.deleteSubject(maMonHoc, maKhoa);
    }

    @PostMapping("/deleteListSubject")
    public ResponseEntity deleteListSubject(
            @RequestBody List<RequestDeleteSubjectFromAllSubject> listRequestDeleteSubjectFromAllSubjects) {
        return subjectService.deleteListSubject(listRequestDeleteSubjectFromAllSubjects);
    }

    @PostMapping("/editSubject")
    public ResponseEntity editSubject(@RequestBody SubjectRequest subjectRequest,
            @RequestParam(name = "id") String id) {
        return subjectService.editSubject(subjectRequest, id);
    }

    @GetMapping("/getSubject")
    public ResponseEntity getSubject(@RequestParam(name = "maKhoa") String maKhoa) {
        return subjectService.getSubject(maKhoa);
    }

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/getSubject1")
    public ResponseEntity getSubject() {
        List<Integer> list = new ArrayList<>();
        List<Subject> list1 = this.subjectRepository.findAll();
        for (int i = 0; i < list1.size(); i++) {
            list.add(list1.get(i).getDsMaMonHocTruoc().size());
        }
        return ResponseEntity.ok().body(list);
    }
}
