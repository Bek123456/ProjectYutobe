package org.example.projectyutobe.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.ProfileRole;
import org.example.projectyutobe.enums.ProfileStatus;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "surname is required")
    private String surname;
    @Email(message = "Email should be valid", regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotBlank(message = "Password field must has a value")
    private String password;
    private ProfileRole role;
    private ProfileStatus status;
    private String attachUrl;
    private String jwt;
}
