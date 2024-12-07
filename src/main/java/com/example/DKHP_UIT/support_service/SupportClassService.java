package com.example.DKHP_UIT.support_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DKHP_UIT.entities.Class;
import com.example.DKHP_UIT.entities.GiangVien;
import com.example.DKHP_UIT.entities.Subject;
import com.example.DKHP_UIT.exception.ExceptionCode;
import com.example.DKHP_UIT.exception.ExceptionUser;
import com.example.DKHP_UIT.mapper.ClassMapper;
import com.example.DKHP_UIT.repository.ClassRepository;
import com.example.DKHP_UIT.repository.GiangVienRepository;
import com.example.DKHP_UIT.repository.RoomRepository;
import com.example.DKHP_UIT.repository.SubjectRepository;
import com.example.DKHP_UIT.request.RequestEditClass;

import java.util.List;

@Component
public class SupportClassService {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    private RoomRepository roomRepository;

    public void check(String giangVienId, int thu, int tietBatDau, String subjectId, String roomId) {
        List<List<Integer>> list = this.classRepository.getList(giangVienId, thu);
        System.out.println(list);
        // create tiet ket thuc, tiet bat dau
        Subject subject = this.subjectRepository.findById(subjectId).get();
        int tietKetThuc = tietBatDau + subject.getSoTinChiLT() - 1;
        // check thu
        if (checkThu(thu, list) == false) {
            // check the time period extends beyond the lunch.
            if (tietBatDau <= 5 && tietKetThuc > 5) {
                throw new ExceptionUser(ExceptionCode.ThePeriodTimeFail);
            }
            // check room
            if (checkAllInRoom(tietBatDau, tietKetThuc, roomId, thu) == false) {
                throw new ExceptionUser(ExceptionCode.RoomIsChoosen);
            }
            return;
        }
        // check the teacher schedule
        if (checkAll(tietBatDau, tietKetThuc, list) == false) {
            throw new ExceptionUser(ExceptionCode.TeacherSchedule);
        }
        // check the time period extends beyond the lunch.
        if (tietBatDau <= 5 && tietKetThuc > 5) {
            throw new ExceptionUser(ExceptionCode.ThePeriodTimeFail);
        }
        // check room schedule
        if (checkAllInRoom(tietBatDau, tietKetThuc, roomId, thu) == false) {
            throw new ExceptionUser(ExceptionCode.RoomIsChoosen);
        }
    }

    private boolean checkThu(int thu, List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            if (thu == list.get(i).get(0)) {
                return true;
            }
        }
        return false;
    }

    private boolean check1(int tietBD1, int tietKT1, int tietBD2, int tietKT2) {
        if (tietKT2 < tietBD1) {
            return true;
        }
        if (tietBD2 > tietKT1) {
            return true;
        }
        return false;
    }

    private boolean checkAll(int tietBD, int tietKT, List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            if (check1(list.get(i).get(1), list.get(i).get(2), tietBD, tietKT) == false) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAllWhenEdit(int tietBD, int tietKT, int originalTietBD, int originalThu, String giangVienId,
            int thu) {
        List<List<Integer>> list = this.classRepository.getList(giangVienId, originalThu, originalTietBD, thu);
        System.out.println("List teacher schedule when edit: " + list);
        for (int i = 0; i < list.size(); i++) {
            return check1(list.get(i).get(1), list.get(i).get(2), tietBD, tietKT);
        }
        return true;
    }

    private boolean checkAllInRoom(int tietBD, int tietKT, String roomId, int thu) {
        List<List<Integer>> list = this.classRepository.shceduleOneDayOfRoom(roomId, thu);
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            return check1(list.get(i).get(0), list.get(i).get(1), tietBD, tietKT);
        }
        return true;
    }

    private boolean checkAllInRoomWhenEdit(int tietBD, int tietKT, String roomId, int thu, String classId) {
        List<List<Integer>> list = this.classRepository.shceduleOneDayOfRoom(roomId, thu, classId);
        System.out.println("List Room when Edit: " + list);
        for (int i = 0; i < list.size(); i++) {
            return check1(list.get(i).get(0), list.get(i).get(1), tietBD, tietKT);
        }
        return true;
    }

    public void checkForPractictHT1(String giangVienId, int thu, int sectionOfDay, String subjectId, String roomId) {
        int tietBatDau = 1;
        if (sectionOfDay == 2) {
            tietBatDau = 6;
        }

        List<List<Integer>> list = this.classRepository.getList(giangVienId, thu);
        // create tiet ket thuc, tiet bat dau
        Subject subject = this.subjectRepository.findById(subjectId).get();
        int tietKetThuc = tietBatDau + 4;
        // check thu
        if (checkThu(thu, list) == false) {
            // check the time period extends beyond the lunch.
            if (tietBatDau <= 5 && tietKetThuc > 5) {
                throw new ExceptionUser(ExceptionCode.ThePeriodTimeFail);
            }
            // check room
            if (checkAllInRoom(tietBatDau, tietKetThuc, roomId, thu) == false) {
                throw new ExceptionUser(ExceptionCode.RoomIsChoosen);
            }
            return;
        }
        // check the teacher schedule
        if (checkAll(tietBatDau, tietKetThuc, list) == false) {
            throw new ExceptionUser(ExceptionCode.TeacherSchedule);
        }
        // check the time period extends beyond the lunch.
        if (tietBatDau <= 5 && tietKetThuc > 5) {
            throw new ExceptionUser(ExceptionCode.ThePeriodTimeFail);
        }
        // check room schedule
        if (checkAllInRoom(tietBatDau, tietKetThuc, roomId, thu) == false) {
            throw new ExceptionUser(ExceptionCode.RoomIsChoosen);
        }
    }

    public void checkTheoryAndPractice(com.example.DKHP_UIT.entities.Class class1,
            com.example.DKHP_UIT.entities.Class class2) {
        if (class1.getThu() != class2.getThu()) {
            return;
        } else {
            if (class2.getFlagTH() == 1) {
                if (class2.getSectionOfDay() == 1 && class1.getTietBatDau() <= 5) {
                    throw new ExceptionUser(ExceptionCode.TheoryAndPractice);
                } else if (class2.getSectionOfDay() == 2 && class1.getTietBatDau() >= 5) {
                    throw new ExceptionUser(ExceptionCode.TheoryAndPractice);
                }
            } else {
                return;
            }
        }
    }

    public void checkDataForEditingTheoryClass(RequestEditClass requestEditClass,
            com.example.DKHP_UIT.entities.Class originalClass) {
        // get subject
        Subject subject = this.subjectRepository.findById(requestEditClass.getSubjectId()).get();

        if (originalClass.getTietBatDau() != requestEditClass.getTietBatDau()
                || originalClass.getThu() != requestEditClass.getThu()
                || originalClass.getRoom().getId().equals(requestEditClass.getRoomId()) == false
                || originalClass.getGiangVien().getId().equals(requestEditClass.getGiangVienId()) == false) {
            // check room
            if (checkAllInRoomWhenEdit(requestEditClass.getTietBatDau(),
                    requestEditClass.getTietBatDau() + subject.getSoTinChiLT() - 1, requestEditClass.getRoomId(),
                    requestEditClass.getThu(), requestEditClass.getClassId()) == false) {
                throw new ExceptionUser(ExceptionCode.EditRoomTheoryClass);
            }

            // check the time period extends beyond the lunch.
            if (requestEditClass.getTietBatDau() <= 5
                    && requestEditClass.getTietBatDau() + subject.getSoTinChiLT() - 1 > 5) {
                throw new ExceptionUser(ExceptionCode.ThePeriodTimeFail);
            }

            // check original teacher
            if (originalClass.getGiangVien().getId().equals(requestEditClass.getGiangVienId())) {
                if (checkAllWhenEdit(requestEditClass.getTietBatDau(),
                        requestEditClass.getTietBatDau() + subject.getSoTinChiLT() - 1, originalClass.getTietBatDau(),
                        originalClass.getThu(), originalClass.getGiangVien().getId(),
                        requestEditClass.getThu()) == false) {
                    throw new ExceptionUser(ExceptionCode.TeacherSchedule);
                }
            }
            // check new teacher
            else {
                List<List<Integer>> list = this.classRepository.getList(requestEditClass.getGiangVienId(),
                        requestEditClass.getThu());
                System.out.println(list);
                if (checkAll(requestEditClass.getTietBatDau(),
                        requestEditClass.getTietBatDau() + subject.getSoTinChiLT() - 1, list) == false) {
                    throw new ExceptionUser(ExceptionCode.TeacherSchedule);
                }
            }
        }

        System.out.println(requestEditClass.getSiso());
        System.out.println(originalClass.getSiso());
        if (requestEditClass.getSiso() != originalClass.getSiso()) {
            // get list practice class
            List<com.example.DKHP_UIT.entities.Class> list = this.classRepository
                    .getPracticeClass(requestEditClass.getClassId());
            System.out.println("List practice classes: " + list);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSiso(requestEditClass.getSiso() / list.size());
            }
            // save practice class
            this.classRepository.saveAll(list);
        }

        // save theory class
        originalClass = this.classMapper.convertRequestEditClass(requestEditClass, originalClass);
        this.classRepository.save(originalClass);
    }

    public void checkDataForEditingPracticeClassHT1(RequestEditClass requestEditClass,
            com.example.DKHP_UIT.entities.Class originalClass) {
        List<com.example.DKHP_UIT.entities.Class> list = this.classRepository
                .getPracticeClass(originalClass.getIdLT());
        Class class1 = this.classRepository.findById(originalClass.getIdLT()).get();
        if (requestEditClass.getFlagTH() == 2) {
            originalClass.setFlagTH(2);
            list.get(0).setFlagTH(2);
            list.get(0).setSiso(class1.getSiso());
            list.get(0).setRoom(null);
            this.classRepository.save(list.get(0));
            this.classRepository.delete(list.get(1));
        } else if (requestEditClass.getSectionOfDay() != originalClass.getSectionOfDay()
                || requestEditClass.getRoomId().equals(originalClass.getRoom().getId()) == false
                || requestEditClass.getGiangVienId().equals(originalClass.getGiangVien().getId()) == false
                || requestEditClass.getThu() != originalClass.getThu()
                || requestEditClass.getSectionOfDay() != originalClass.getSectionOfDay()) {

            // create tietBatDau, tietKetThuc
            int tietBatDau = 1;
            int tietKetThuc = 5;
            System.out.println(requestEditClass.getSectionOfDay());
            if (requestEditClass.getSectionOfDay() == 2) {
                tietBatDau = 6;
                tietKetThuc = 10;
            }
            // get subject
            Subject subject = this.subjectRepository.findById(requestEditClass.getSubjectId()).get();
            // check room
            if (checkAllInRoomWhenEdit(tietBatDau,
                    tietKetThuc, requestEditClass.getRoomId(),
                    requestEditClass.getThu(), requestEditClass.getClassId()) == false) {
                throw new ExceptionUser(ExceptionCode.EditRoomTheoryClass);
            }

            // check original teacher
            if (originalClass.getGiangVien().getId().equals(requestEditClass.getGiangVienId())) {
                if (checkAllWhenEdit(tietBatDau,
                        tietKetThuc, originalClass.getTietBatDau(),
                        originalClass.getThu(), originalClass.getGiangVien().getId(),
                        requestEditClass.getThu()) == false) {
                    throw new ExceptionUser(ExceptionCode.TeacherSchedule);
                }
            }
            // check new teacher
            else {
                List<List<Integer>> list1 = this.classRepository.getList(requestEditClass.getGiangVienId(),
                        requestEditClass.getThu());
                System.out.println(list1);
                if (checkAll(tietBatDau,
                        tietKetThuc, list1) == false) {
                    throw new ExceptionUser(ExceptionCode.TeacherSchedule);
                }
            }

            // check theory and practice.

            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSectionOfDay(requestEditClass.getSectionOfDay());
                checkTheoryAndPractice(class1, list.get(i));
                list.get(i).setRoom(this.roomRepository.findById(requestEditClass.getRoomId()).get());
                System.out.println(requestEditClass.getThu());
                list.get(i).setThu(requestEditClass.getThu());
                list.get(i).setStartDate(requestEditClass.getStartDate());
                list.get(i).setEndDate(requestEditClass.getEndDate());
                list.get(i).setGiangVien(this.giangVienRepository.findById(requestEditClass.getGiangVienId()).get());
                list.get(i).setTietBatDau(tietBatDau);
                list.get(i).setTietKetThuc(tietKetThuc);
                this.classRepository.save(list.get(i));
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setStartDate(requestEditClass.getStartDate());
                list.get(i).setEndDate(requestEditClass.getEndDate());
                this.classRepository.save(list.get(i));
            }
        }

    }

    public void checkDataForEditingPracticeClassHT2(RequestEditClass requestEditClass,
            com.example.DKHP_UIT.entities.Class originalClass) {
        Class classLT = this.classRepository.findById(originalClass.getIdLT()).get();
        if (requestEditClass.getFlagTH() == 1) {
            originalClass.setFlagTH(1);
            // create tiet ket thuc
            int tietBatDau = 1;
            int tietKetThuc = 5;
            if (requestEditClass.getSectionOfDay() == 2) {
                tietBatDau = 6;
                tietKetThuc = 10;
            }

            // check room
            if (checkAllInRoom(tietBatDau, tietKetThuc, requestEditClass.getRoomId(),
                    requestEditClass.getThu()) == false) {
                throw new ExceptionUser(ExceptionCode.EditRoomTheoryClass);
            }

            // check teacher
            List<List<Integer>> list = this.classRepository.getList(requestEditClass.getGiangVienId(),
                    requestEditClass.getThu());
            if (checkAll(tietBatDau, tietKetThuc, list) == false) {
                throw new ExceptionUser(ExceptionCode.TeacherSchedule);
            }

            // create 2 practice class
            Class class1 = this.classMapper.convertRequestEditClass(requestEditClass);
            Class class2 = this.classMapper.convertRequestEditClass(requestEditClass);

            checkTheoryAndPractice(classLT, class1);
            checkTheoryAndPractice(classLT, class2);
            class1.setClassName(classLT.getClassName() + ".1");
            class1.setSiso(originalClass.getSiso() / 2);
            class1.setIdLT(originalClass.getIdLT());
            class2.setClassName(classLT.getClassName() + ".2");
            class2.setSiso(originalClass.getSiso() / 2);
            class2.setIdLT(originalClass.getIdLT());
            this.classRepository.save(class1);
            this.classRepository.save(class2);
            this.classRepository.delete(originalClass);
        } else {
            originalClass.setNote(originalClass.getNote() + "..." + requestEditClass.getNote());
            GiangVien giangVien = this.giangVienRepository.findById(requestEditClass.getGiangVienId()).get();
            originalClass.setGiangVien(giangVien);
            this.classRepository.save(originalClass);
        }

    }
}
