package com.example.DKHP_UIT.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permission")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    @Id
    private Integer id;
    private String permissionName;
    private String groupName;

    @ManyToMany(mappedBy = "permissions")
    @JsonBackReference
    private Set<Role> roles = new HashSet<>();

    @Override
    public int hashCode() {

        return this.id;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", groupName='" + groupName + '\'' +
                // Không gọi toString() cho roles để tránh vòng lặp
                ", rolesCount=" + (roles != null ? roles.size() : 0) +
                '}';
    }
}
/* */