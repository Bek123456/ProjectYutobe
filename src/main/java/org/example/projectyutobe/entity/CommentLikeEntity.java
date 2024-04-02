package org.example.projectyutobe.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.CommentLikeType;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment_like_entity")
@Setter
@Getter
public class CommentLikeEntity {
//    id,profile_id,comment_id,created_date,type(Like,Dislike)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profileEntity;
    @Column(name = "comment_id")
    private Integer commentId;
    @ManyToOne
    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
    private CommentEntity commentEntity;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "type")
    private CommentLikeType type;
}
