package org.example.projectyutobe.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.VideoStatus;
import org.example.projectyutobe.enums.VideoType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "video_entity")
@Setter
@Getter
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /////1
    @Column(name = "preview_attach_id")
    private String previewAttachId;
    @ManyToOne
    @JoinColumn(name = "preview_attach_id", insertable = false, updatable = false)
    private AttachEntity previeiwAttachEntity;
    /////2
    @Column(name = "title")
    private String title;

   //////3
    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity entity;
   //////4
    @Column(name = "attach_id")
    private String attachId;
    @ManyToOne
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attachEntity;
    //////////5
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    ////6
    @Column(name = "published_date")
    private LocalDateTime publishedDate;
     ////7
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VideoStatus status;
    ///8
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private VideoType type;
    ////9
    @Column(name = "view_count")
    private Integer viewCount;
    ///10
    @Column(name = "shared_count")
    private Integer sharedCount;
    ///11
    @Column(name = "description")
    private String description;
    /////12
    @Column(name = "channel_id")
    private UUID channelId;
    @ManyToOne
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    private ChannelEntity channelEntity;
    /////13
    @Column(name = "like_count")
    private Integer likeCount;
    /////14
    @Column(name = "dislike_count")
    private Integer dislikeCount;

}
