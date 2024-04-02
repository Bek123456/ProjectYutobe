package org.example.projectyutobe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoTagDTO {
    private UUID video_id;
    private Integer tag_id;
}
