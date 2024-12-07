package com.example.DKHP_UIT.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

import com.example.DKHP_UIT.entities.OpenSubject.OpenSubject;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "class")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    private String className;
    private Integer siso; // min >= 30
    private Date startDate;
    private Date endDate;
    private Integer tietBatDau;
    private Integer tietKetThuc;
    private Integer thu;
    private Integer flagTH; // 0 1 2
    private String note;
    private String idLT;
    private Date startDate1;
    private Date endDate1;
    private int sectionOfDay;
    private Integer currentSiSo;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    // @ManyToOne
    // @JoinColumn(name = "subject_id")
    // private OpenSubject subject;

    @ManyToOne
    @JoinColumn(name = "giangvien_id")
    private GiangVien giangVien;

    @ManyToMany(mappedBy = "classes")
    private Set<Student> students = new HashSet<>();
}
