package model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.bean.UserRole;

@Getter
@Setter
@AllArgsConstructor
public class UserRoleDTO {
    private UUID roleId;
    private String roleName;
    private Integer permission;
    
    static public UserRoleDTO fromEntity(UserRole userRole) {
        return new UserRoleDTO(
            userRole.getRoleId(), 
            userRole.getRoleName(), 
            userRole.getPermission()
        );
    }
}
