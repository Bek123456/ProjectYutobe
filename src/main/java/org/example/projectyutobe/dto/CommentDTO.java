package org.example.projectyutobe.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {

    private Integer profileId;

    private UUID videoId;

    private String content;

    private Integer replyId;

    private Integer like_count;

    private Integer dislike_count;

}
