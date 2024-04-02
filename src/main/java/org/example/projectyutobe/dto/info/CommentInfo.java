package org.example.projectyutobe.dto.info;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentInfo {

    private Integer id;

    private String content;

    private LocalDateTime created_date;

    private Integer like_count;

    private Integer dislike_count;

    private Integer profileId;

    private String profileName;

    private String profileSurname;

    private String profilePhotoId;

    private String profileUrl;

}
