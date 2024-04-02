package org.example.projectyutobe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachDTO {
    private String id;
    @NotBlank(message = "OriginalName field must has a value")
    private String originalName;
    private String path;
    private String type;
    private String duration;
    private Long size;
    private LocalDateTime createdData;
    private String url;
}
