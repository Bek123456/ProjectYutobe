package org.example.projectyutobe.dto;


import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.CommentLikeType;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentLikeDTO {
    private Integer id;
    private Integer profileId;
    private Integer commentId;
    private LocalDateTime createdDate;
    private CommentLikeType type;
}
