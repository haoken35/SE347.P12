package com.example.DKHP_UIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.DKHP_UIT.entities.Staff;

public interface StaffRepository extends JpaRepository<Staff, String> {
    @Query("select staff from Staff staff where staff.email = :email")
    public Staff getStaffByEmail(String email);
}
