package com.example.DKHP_UIT.service;

import java.lang.classfile.ClassFile.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.DKHP_UIT.entities.Permission;
import com.example.DKHP_UIT.entities.Role;
import com.example.DKHP_UIT.repository.PermissionRepository;
import com.example.DKHP_UIT.repository.RoleRepository;
import com.example.DKHP_UIT.request.RoleRequestCreate;
import com.example.DKHP_UIT.response.ResponseCode;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    public ResponseEntity<String> createRole(List<RoleRequestCreate> list) {
        for (RoleRequestCreate roleRequest : list) {
            Optional<Role> optionalRole = this.roleRepository.findById(roleRequest.getRoleId());

            Role role;
            if (optionalRole.isPresent()) {
                role = optionalRole.get();
            } else {
                role = Role.builder()
                        .id(roleRequest.getRoleId())
                        .roleName(roleRequest.getRoleName())
                        .permissions(new HashSet<>())
                        .build();
            }

            // Check if the Permission exists
            Permission permission;
            permission = Permission.builder()
                    .id(roleRequest.getPermissionId())
                    .permissionName(roleRequest.getPermissionName())
                    .groupName(roleRequest.getGroupName())
                    .build();
            // Save the new permission if it doesn't exist
            permission = permissionRepository.save(permission);
            // Add permission to role
            role.getPermissions().add(permission);
            this.roleRepository.save(role);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok().body("Roles created or updated successfully");
    }

    public ResponseEntity removeDkhpPermission() {
        Role role = roleRepository.findById(1).get();
        Permission permission = permissionRepository.findById(1).get();
        role.getPermissions().remove(permission);
        this.roleRepository.save(role);
        return ResponseEntity.ok().body(ResponseCode.jsonOfResponseCode(ResponseCode.CloseDKHP));
    }

    public ResponseEntity openDkhpPermission() {
        Role role = roleRepository.findById(1).get();
        Permission permission = permissionRepository.findById(1).get();
        role.getPermissions().add(permission);
        this.roleRepository.save(role);
        return ResponseEntity.ok().body(ResponseCode.jsonOfResponseCode(ResponseCode.OpenDKHP));
    }

}
