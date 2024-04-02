package org.example.projectyutobe.util;


import jakarta.servlet.http.HttpServletRequest;
import org.example.projectyutobe.dto.JwtDTO;
import org.example.projectyutobe.enums.ProfileRole;
import org.example.projectyutobe.exp.ForbiddenException;

public class HttpRequestUtil {
    public static Integer getProfileId(HttpServletRequest request, ProfileRole... requiredRoleList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");

        if (requiredRoleList.length==0){
            return id;
        }

        for (ProfileRole requiredRole : requiredRoleList) {
            if (role.equals(requiredRole)) {
                return id;
            }

        }
        throw new ForbiddenException("Method not allowed");

    }
    public static JwtDTO getJWTDTO(HttpServletRequest request, ProfileRole... requiredRoleList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        JwtDTO dto = new JwtDTO(id,role);
        for (ProfileRole requiredRole : requiredRoleList) {
            if (role.equals(requiredRole)) {
                return dto;
            }
        }
        throw new ForbiddenException("Method not allowed");
    }
}
