package com.example.DKHP_UIT.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.DKHP_UIT.entities.OpenSubject.OpenSubject;
import com.example.DKHP_UIT.entities.ctdt.CTDT;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String maMonHoc;
    private String tenMonHoc;
    @Column(name = "dsMaMonHocTruoc", columnDefinition = "BLOB")
    private List<String> dsMaMonHocTruoc;
    private String loaiMonHoc;
    private int soTinChiLT;
    private int soTinChiTH;
    private String maKhoa;

    @ManyToMany(mappedBy = "subjects")
    private Set<CTDT> ctdts = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "subject_id")
    private Set<OpenSubject> listOpenSubject;

    @OneToMany
    @JoinColumn(name = "subject_id")
    private Set<Class> listClasses = new HashSet();
}
