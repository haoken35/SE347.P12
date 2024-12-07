package com.example.DKHP_UIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.example.DKHP_UIT.entities.Class;

public interface ClassRepository extends JpaRepository<Class, String> {
        @Query("select count(c) from Class c where c.className like :subjectName")
        public int getNumberOfClassLikeSubjectName(@Param("subjectName") String subjectName);

        @Query(value = "select thu, tiet_bat_dau, tiet_ket_thuc from class where giangvien_id = :giangVienId && thu is not null && thu = :thu", nativeQuery = true)
        public List<List<Integer>> getList(String giangVienId, int thu);

        @Query(value = "select thu, tiet_bat_dau, tiet_ket_thuc from class where thu is not null && thu <> :thu && tiet_bat_dau <> :tietBd && giangvien_id = :giangVienId && thu = :thu1", nativeQuery = true)
        public List<List<Integer>> getList(String giangVienId, int thu, int tietBd, int thu1);

        @Query(value = "select tiet_bat_dau, tiet_ket_thuc from class where room_id = :roomId && thu = :thu", nativeQuery = true)
        public List<List<Integer>> shceduleOneDayOfRoom(String roomId, int thu);

        @Query(value = "select tiet_bat_dau, tiet_ket_thuc from class where room_id = :roomId && thu = :thu && class.id <> :classId", nativeQuery = true)
        public List<List<Integer>> shceduleOneDayOfRoom(String roomId, int thu, String classId);

        @Query("select c from Class c where c.className like :className%")
        public List<Class> getRelativeClass(String className);

        @Query("select c from Class c where c.idLT = :classId order by className")
        public List<Class> getPracticeClass(String classId);

        @Query("select count(*) from Class c where c.idLT = :classId")
        public int getNumberOfPractices(String classId);

        @Query(value = "select count(*) from class where subject_id = :subjectId", nativeQuery = true)
        public int getClassFollowingBySubjectId(String subjectId);

}
