package com.example.DKHP_UIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.example.DKHP_UIT.entities.Class;
import com.example.DKHP_UIT.entities.Student;
import com.example.DKHP_UIT.response.StudentResponseList;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query(value = "select st.mssv, st.ten_day_du, st.ten_khoa, st.ten_nganh, st.gioi_tinh from Student st limit 10 offset :from", nativeQuery = true)
    public List<List<String>> getStudentList(int from);

    @Query("select student.classes from Student student where student.id = :studentId")
    public List<Class> listRegisteredClass(String studentId);

    @Query(value = "SELECT tiet_bat_dau, tiet_ket_thuc FROM class " +
            "INNER JOIN student_class ON class.id = student_class.class_id " +
            "INNER JOIN student ON student_class.student_id = student.mssv where thu = :thu && mssv = :mssv && flagTH != 2", nativeQuery = true)
    public List<List<Integer>> getStudentScheduleIn1Day(int thu, String mssv);

}