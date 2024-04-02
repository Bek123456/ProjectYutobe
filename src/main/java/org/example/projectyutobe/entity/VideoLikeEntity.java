package org.example.projectyutobe.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.VideoLikeType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "video_like_entity")
@Setter
@Getter
public class VideoLikeEntity {
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
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    @ManyToOne
    private VideoEntity videoEntity;

    @Column(name = "created_date")
    private LocalDateTime cratedDate;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private VideoLikeType type;
}
