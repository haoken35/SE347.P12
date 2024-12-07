package com.example.DKHP_UIT.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DKHP_UIT.entities.Role;
import com.example.DKHP_UIT.repository.RoleRepository;
import com.example.DKHP_UIT.request.RoleRequestCreate;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    // @Autowired
    // private RoleService roleService;

    @Transactional
    @PostMapping("/getRole")
    public ResponseEntity getRole() {
        Optional<Role> optional = roleRepository.findById(1);
        return ResponseEntity.ok().body(optional.get());

    }

    // @PostMapping("/createRolePermission")
    // public ResponseEntity createRole(@RequestBody List<RoleRequestCreate> list) {
    // return this.roleService.createRole(list);
    // }
}
