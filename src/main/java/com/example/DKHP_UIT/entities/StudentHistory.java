package com.example.DKHP_UIT.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_history")
public class StudentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String subjectId;
    private String studentId;
    private int hocKy;
    private int nam;
    private int className;
    private int thu;
    private int tietBatDau;
}
