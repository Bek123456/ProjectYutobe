package org.example.projectyutobe.dto.info;

import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.CommentLikeType;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentLikeInfo {
    private Integer id;
    private Integer profile_id;
    private Integer comment_id;
    private LocalDateTime created_date;
    private CommentLikeType type;
}
