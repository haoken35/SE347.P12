package com.example.DKHP_UIT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.DKHP_UIT.entities.Class;
import com.example.DKHP_UIT.mapper.ClassMapper;
import com.example.DKHP_UIT.repository.ClassRepository;
import com.example.DKHP_UIT.request.RequestAddClassWithPractice;
import com.example.DKHP_UIT.request.RequestCreateClassNonTH;
import com.example.DKHP_UIT.request.RequestEditClass;
import com.example.DKHP_UIT.response.ResponseCode;
import com.example.DKHP_UIT.support_service.SupportClassService;

@Service
public class ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SupportClassService supportClassService;

    public ResponseEntity addClass(RequestCreateClassNonTH requestCreateClassNonTH) {
        Class class1 = this.classMapper.convertRequestNonTH(requestCreateClassNonTH);

        if (requestCreateClassNonTH.getFlagTH() == 3) {
            class1.setFlagTH(3);
            this.classRepository.save(class1);
            return ResponseEntity.ok().body(ResponseCode.jsonOfResponseCode(ResponseCode.AddClassWithOutTH));
        }
        // teacher schedule maybe conflict.
        // The starting period may cause the class to extend beyond the lunch break.
        this.supportClassService.check(requestCreateClassNonTH.getGiangVienId(),
                requestCreateClassNonTH.getThu(), requestCreateClassNonTH.getTietBatDau(),
                requestCreateClassNonTH.getSubjectId(), requestCreateClassNonTH.getRoomId());
        this.classRepository.save(class1);
        return ResponseEntity.ok().body(ResponseCode.jsonOfResponseCode(ResponseCode.AddClassWithOutTH));
    }

    public ResponseEntity addClassWithInPractice(RequestAddClassWithPractice requestAddClassWithPractice) {
        // create theory class
        Class theoryClass = this.classMapper
                .convertRequestNonTH(requestAddClassWithPractice.getRequestCreateClassNonTH());
        // check theory class.
        this.supportClassService.check(
                requestAddClassWithPractice.getRequestCreateClassNonTH().getGiangVienId(),
                requestAddClassWithPractice.getRequestCreateClassNonTH().getThu(),
                requestAddClassWithPractice.getRequestCreateClassNonTH().getTietBatDau(),
                requestAddClassWithPractice.getRequestCreateClassNonTH().getSubjectId(),
                requestAddClassWithPractice.getRequestCreateClassNonTH().getRoomId());
        // create practice class
        Class classes[] = null;
        Class ht2Class = null;
        if (requestAddClassWithPractice.getFlagTH() == 1) {
            classes = this.classMapper.convertRequestWithInPracticeHT1(requestAddClassWithPractice,
                    theoryClass);
            this.supportClassService.checkForPractictHT1(classes[0].getGiangVien().getId(), classes[0].getThu(),
                    classes[0].getSectionOfDay(), classes[0].getSubject().getId(), classes[0].getRoom().getId());
            // check theory and practice
            this.supportClassService.checkTheoryAndPractice(theoryClass, classes[0]);
        } else {
            ht2Class = this.classMapper.convertRequestPracticeHT2(requestAddClassWithPractice, theoryClass);
        }

        // overcome the condition
        // save theory class
        theoryClass = this.classRepository.save(theoryClass);
        // save practice class
        if (classes != null) {
            classes[0].setIdLT(theoryClass.getId());
            classes[1].setIdLT(theoryClass.getId());
            this.classRepository.save(classes[0]);
            this.classRepository.save(classes[1]);
        } else {
            ht2Class.setIdLT(theoryClass.getId());
            this.classRepository.save(ht2Class);
        }
        return ResponseEntity.ok().body(ResponseCode.jsonOfResponseCode(ResponseCode.AddClassWithInTH));
    }

    public ResponseEntity deleteClass(String classId) {
        // get class
        Class class1 = this.classRepository.findById(classId).get();
        // create general class name
        String className = class1.getClassName();
        if (class1.getFlagTH() != 0) {
            className = class1.getClassName().substring(0, class1.getClassName().length() - 2);
        }
        // get relative class.
        List<Class> relativeClass = this.classRepository.getRelativeClass(className);
        // delete relative class
        for (int i = 0; i < relativeClass.size(); i++) {
            this.classRepository.delete(relativeClass.get(i));
        }

        return ResponseEntity.ok().body(ResponseCode.jsonOfResponseCode(ResponseCode.DeleteClass));
    }

    public ResponseEntity getRelativeClass(String classId) {
        // get class
        Class class1 = this.classRepository.findById(classId).get();
        // create general class name
        String className = class1.getClassName();
        if (class1.getFlagTH() != 0) {
            className = class1.getClassName().substring(0, class1.getClassName().length() - 2);
        }
        // get relative class.
        List<Class> relativeClass = this.classRepository.getRelativeClass(className);
        return ResponseEntity.ok().body(relativeClass);
    }

    public ResponseEntity editClass(RequestEditClass requestEditClass) {
        Class class1 = this.classRepository.findById(requestEditClass.getClassId()).get();
        // if this class is theory class
        if (class1.getFlagTH() == 0) {
            this.supportClassService.checkDataForEditingTheoryClass(requestEditClass, class1);
        }
        // if this class is practice class HT1
        else if (class1.getFlagTH() == 1) {
            this.supportClassService.checkDataForEditingPracticeClassHT1(requestEditClass, class1);
        }
        // if this class is practice class HT2
        else {
            this.supportClassService.checkDataForEditingPracticeClassHT2(requestEditClass, class1);
        }

        return ResponseEntity.ok().body(ResponseCode.jsonOfResponseCode(ResponseCode.EditClass));
    }

}
