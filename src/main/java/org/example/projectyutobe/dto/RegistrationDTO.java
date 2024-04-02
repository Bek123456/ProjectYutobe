package org.example.projectyutobe.dto;


import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.ProfileRole;
import org.example.projectyutobe.enums.ProfileStatus;

@Setter
@Getter
public class RegistrationDTO {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private ProfileRole profileRole;
    private ProfileStatus status;
}
