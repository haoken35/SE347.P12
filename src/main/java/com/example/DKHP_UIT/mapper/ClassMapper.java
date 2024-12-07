package com.example.DKHP_UIT.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.entities.Class;
import com.example.DKHP_UIT.entities.GiangVien;
import com.example.DKHP_UIT.entities.Room;
import com.example.DKHP_UIT.entities.Subject;
import com.example.DKHP_UIT.repository.ClassRepository;
import com.example.DKHP_UIT.repository.GiangVienRepository;
import com.example.DKHP_UIT.repository.RoomRepository;
import com.example.DKHP_UIT.repository.SubjectRepository;
import com.example.DKHP_UIT.request.RequestAddClassWithPractice;
import com.example.DKHP_UIT.request.RequestCreateClassNonTH;
import com.example.DKHP_UIT.request.RequestEditClass;

@Component
public class ClassMapper {
        @Autowired
        private ClassRepository classRepository;

        @Autowired
        private SubjectRepository subjectRepository;

        @Autowired
        private GiangVienRepository giangVienRepository;

        @Autowired
        private RoomRepository roomRepository;

        public Class convertRequestNonTH(RequestCreateClassNonTH requestCreateClassNonTH) {
                // get subject
                Subject subject = this.subjectRepository.findById(requestCreateClassNonTH.getSubjectId()).get();
                // get giang vien
                GiangVien giangVien = this.giangVienRepository.findById(requestCreateClassNonTH.getGiangVienId()).get();
                // create name for class
                int numberOfClassLikedSubjectName = this.classRepository
                                .getNumberOfClassLikeSubjectName(subject.getMaMonHoc() + "__");
                System.out.println(subject.getTenMonHoc());
                String className = subject.getMaMonHoc() + "." + (numberOfClassLikedSubjectName + 1);
                // get room
                Room room = this.roomRepository.findById(requestCreateClassNonTH.getRoomId()).get();

                return Class.builder()
                                .className(className)
                                .siso(requestCreateClassNonTH.getSiSo())
                                .startDate(requestCreateClassNonTH.getStart())
                                .endDate(requestCreateClassNonTH.getEnd())
                                .tietBatDau(requestCreateClassNonTH.getTietBatDau())
                                .thu(requestCreateClassNonTH.getThu())
                                .flagTH(0)
                                .note("")
                                .giangVien(giangVien)
                                .subject(subject)
                                .room(room)
                                .tietKetThuc(requestCreateClassNonTH.getTietBatDau() + subject.getSoTinChiLT())
                                .currentSiSo(0)
                                .build();
        }

        public Class[] convertRequestWithInPracticeHT1(RequestAddClassWithPractice requestAddClassWithPractice,
                        Class classTheory) {
                Class[] classes = new Class[2];
                // at here we don't set theoryId for this class, because we haven't save the
                // theory class
                // get subject
                Subject subject = this.subjectRepository
                                .findById(requestAddClassWithPractice.getRequestCreateClassNonTH().getSubjectId())
                                .get();
                // get giang vien
                GiangVien giangVien = this.giangVienRepository.findById(requestAddClassWithPractice.getGiangVienId())
                                .get();
                // create name for class
                String name1 = classTheory.getClassName() + ".1";
                String name2 = classTheory.getClassName() + ".2";
                // get room
                Room room = this.roomRepository.findById(requestAddClassWithPractice.getRoomId()).get();
                // create tietBatDau and tietKetThuc
                int tietBatDau = 1;
                int tietKetThuc = 5;
                if (requestAddClassWithPractice.getSectionOfDay() == 2) {
                        tietBatDau = 6;
                        tietKetThuc = 10;
                }

                classes[0] = Class.builder()
                                .subject(subject)
                                .className(name1)
                                .siso(classTheory.getSiso() / 2)
                                .startDate(requestAddClassWithPractice.getStart())
                                .endDate(requestAddClassWithPractice.getEnd())
                                .sectionOfDay(requestAddClassWithPractice.getSectionOfDay())
                                .thu(requestAddClassWithPractice.getThu())
                                .flagTH(1)
                                .note("")
                                .giangVien(giangVien)
                                .subject(subject)
                                .room(room)
                                .tietBatDau(tietBatDau)
                                .tietKetThuc(tietKetThuc)
                                .currentSiSo(0)
                                .build();

                classes[1] = Class.builder()
                                .subject(subject)
                                .className(name2)
                                .siso(classTheory.getSiso() / 2)
                                .startDate(requestAddClassWithPractice.getStart())
                                .endDate(requestAddClassWithPractice.getEnd())
                                .sectionOfDay(requestAddClassWithPractice.getSectionOfDay())
                                .thu(requestAddClassWithPractice.getThu())
                                .flagTH(1)
                                .note("")
                                .giangVien(giangVien)
                                .subject(subject)
                                .room(room)
                                .tietBatDau(tietBatDau)
                                .tietKetThuc(tietKetThuc)
                                .currentSiSo(0)
                                .build();
                return classes;
        }

        public Class convertRequestPracticeHT2(RequestAddClassWithPractice requestAddClassWithPractice,
                        Class theoryClass) {
                // get subject
                Subject subject = this.subjectRepository
                                .findById(requestAddClassWithPractice.getRequestCreateClassNonTH().getSubjectId())
                                .get();
                // get giang vien
                GiangVien giangVien = this.giangVienRepository.findById(requestAddClassWithPractice.getGiangVienId())
                                .get();
                // create name for class
                String name = theoryClass.getClassName() + ".1";

                return Class.builder()
                                .className(name)
                                .siso(requestAddClassWithPractice.getRequestCreateClassNonTH().getSiSo())
                                .startDate(requestAddClassWithPractice.getStart())
                                .endDate(requestAddClassWithPractice.getEnd())
                                .flagTH(2)
                                .note("")
                                .giangVien(giangVien)
                                .subject(subject)
                                .currentSiSo(0)
                                .build();
        }

        public Class convertRequestEditClass(RequestEditClass requestEditClass, Class originalClass) {
                // get subject
                // get subject
                Subject subject = this.subjectRepository
                                .findById(requestEditClass.getSubjectId()).get();
                // get giang vien
                GiangVien giangVien = this.giangVienRepository.findById(requestEditClass.getGiangVienId())
                                .get();
                // get room
                Room room = this.roomRepository.findById(requestEditClass.getRoomId()).get();

                originalClass.setEndDate(requestEditClass.getEndDate());
                originalClass.setStartDate(requestEditClass.getStartDate());
                originalClass.setThu(requestEditClass.getThu());
                originalClass.setTietBatDau(requestEditClass.getTietBatDau());
                originalClass.setTietKetThuc(requestEditClass.getTietBatDau() + subject.getSoTinChiLT() - 1);
                originalClass.setSiso(requestEditClass.getSiso());
                originalClass.setGiangVien(giangVien);
                originalClass.setRoom(room);
                originalClass.setCurrentSiSo(0);
                return originalClass;
        }

        public Class convertRequestEditClass(RequestEditClass requestEditClass) {
                int tietBatDau = 1;
                int tietKetThuc = 5;
                if (requestEditClass.getSectionOfDay() == 2) {
                        tietBatDau = 6;
                        tietKetThuc = 10;
                }
                return Class.builder()
                                .idLT(requestEditClass.getIdLT())
                                .endDate(requestEditClass.getEndDate())
                                .startDate(requestEditClass.getStartDate())
                                .flagTH(1)
                                .thu(requestEditClass.getThu())
                                .tietBatDau(tietBatDau)
                                .tietKetThuc(tietKetThuc)
                                .giangVien(this.giangVienRepository.findById(requestEditClass.getGiangVienId()).get())
                                .room(this.roomRepository.findById(requestEditClass.getRoomId()).get())
                                .subject(this.subjectRepository.findById(requestEditClass.getSubjectId()).get())
                                .sectionOfDay(requestEditClass.getSectionOfDay())
                                .currentSiSo(0)
                                .build();
        }
}
