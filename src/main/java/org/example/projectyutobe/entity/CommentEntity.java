package org.example.projectyutobe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment_entity")
@Setter
@Getter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profileEntity;


    @Column(name = "video_id")
    private UUID videoId;
    @ManyToOne
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private VideoEntity videoEntity;


    @Column(name = "content")
    private String content;

    @Column(name = "reply_id")
    private Integer replyId;
    @ManyToOne
    @JoinColumn(name = "reply_id",insertable = false, updatable = false)
    private CommentEntity reply;

    @Column(name = "like_count")
    private Integer like_count;

    @Column(name = "dislike_count")
    private Integer dislike_count;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
