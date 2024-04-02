package org.example.projectyutobe.dto.info;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class CommentProfileInfo {

    private Integer id;

    private String content;

    private LocalDateTime createdDate;

    private Integer like_count;

    private Integer dislike_count;

    private UUID videoId;

    private String videoChannelName;

    private String previewAttachId;

    private String title;

    private Long duration;

}
