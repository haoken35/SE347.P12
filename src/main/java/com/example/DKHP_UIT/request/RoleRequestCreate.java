package com.example.DKHP_UIT.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestCreate {
    private int permissionId;
    private String permissionName;
    private String groupName;
    private int roleId;
    private String roleName;
}
