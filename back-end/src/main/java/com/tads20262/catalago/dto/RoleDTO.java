package com.tads20262.catalago.dto;

import com.tads20262.catalago.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private Long id;
    private String authority;

    public RoleDTO(Role entity){
        id = entity.getId();
        authority = entity.getAuthority();
    }
}
