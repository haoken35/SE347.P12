package com.example.DKHP_UIT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.DKHP_UIT.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select permission_id, permission_name, group_name, role_id, role_name from permission inner join role_permission on permission.id = role_permission.permission_id inner join role on role.id = role_permission.role_id", nativeQuery = true)
    public List<Object> getPermissionAndRole();

    @Query(value = "select permission_name from role \n" + //
            "\tinner join role_permission on role.id = role_permission.role_id \n" + //
            "    inner join permission on role_permission.permission_id = permission.id\n" + //
            "where role_id = :role", nativeQuery = true)
    public List<String> getPermissionOf1Role(int role);
}
