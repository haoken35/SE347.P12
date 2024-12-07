package com.example.DKHP_UIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.DKHP_UIT.entities.OpenSubject.OpenSubject;
import com.example.DKHP_UIT.entities.OpenSubject.OpenSubjectId;
import java.util.List;

public interface OpenSubjectRepository extends JpaRepository<OpenSubject, OpenSubjectId> {

    @Query(value = "select subject_id from open_subject where hoc_ky = :hocKy and nam = :nam", nativeQuery = true)
    public List<String> getListOpenSubjectWithYearAndSemester(int hocKy, int nam);
}
