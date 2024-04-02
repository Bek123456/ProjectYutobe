package org.example.projectyutobe.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.VideoLikeType;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoLikeDTO {
    private Integer id;
    private Integer profileId;
    private UUID videoId;
    private LocalDateTime cratedDate;
    private VideoLikeType type;
}
