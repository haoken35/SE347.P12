package com.example.DKHP_UIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.DKHP_UIT.entities.Subject;
import com.example.DKHP_UIT.entities.ctdt.CTDT;
import com.example.DKHP_UIT.entities.ctdt.CTDTID;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;

public interface CTDTRepository extends JpaRepository<CTDT, CTDTID> {
    @Query("select x from CTDT x where x.id.maKhoa = :maKhoa")
    public List<CTDT> getListCTDTByMaKhoa(@Param("maKhoa") String maKhoa);

    @Query("select x.subjects from CTDT x where x.id.hocKy = :hocKy and x.id.maKhoa = :maKhoa")
    public Set<Subject> getListSubjects(String maKhoa, Integer hocKy);

    @Query(value = "select ctdt_hoc_ky, ma_mon_hoc, subject.id, subject.so_tin_chilt, subject.so_tin_chith, subject.ten_mon_hoc subject \n"
            + //
            "from ctdt_subject \n" + //
            "\tinner join subject where ctdt_subject.subject_id = subject.id and ctdt_subject.ctdt_ma_khoa = :maKhoa and ctdt_hoc_ky = :hocKy", nativeQuery = true)

    public List<List<Object>> getCTDT(String maKhoa, int hocKy);

    @Query(value = "select count(*) from ctdt_subject where subject_id = :subjectId", nativeQuery = true)
    public int check1SubjectInCTDT(String subjectId);

    @Modifying
    @Transactional
    @Query(value = "delete from ctdt_subject where subject_id = :subjectId", nativeQuery = true)
    public void deleteSubjectInRepository(String subjectId);
}