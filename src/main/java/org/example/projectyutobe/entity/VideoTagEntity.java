package org.example.projectyutobe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "video_tag_entity")
public class VideoTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //////////
    @Column(name = "video_id")
    private UUID videoId;
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    @ManyToOne
    private VideoEntity videoEntity;
    /////////////
    @Column(name = "tag_id")
    private Integer tagId;
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    @ManyToOne
    private TagEntity tagEntity;
    ///////////
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
