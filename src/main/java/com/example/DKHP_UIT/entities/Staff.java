package com.example.DKHP_UIT.entities;

import com.example.DKHP_UIT.abstract_class.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String staffId;
    private String fullName;
    private String email;
    private String account;
    private String password;
    private String code;

    // 1 staff can be an Admin, if the flagAdmin is 1
    // in the System, we have 1 parent Admin who have permission to allow 1 staff to
    // admin or change 1 admin to staff.
    private Integer flagAdmin;
    private Integer flagParentAdmin;
}
