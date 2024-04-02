package org.example.projectyutobe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "play_list_video_entity")
@Setter
@Getter
public class PlayListVideoEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "playlist_id")
   private Integer playListId;
   @ManyToOne
   @JoinColumn(name = "playlist_id", insertable = false, updatable = false)
   private PlayListEntity playListEntity;
   @Column(name = "video_id")
   private UUID videoId;
   @ManyToOne
   @JoinColumn(name = "video_id", insertable = false, updatable = false)
   private VideoEntity videoEntity;
   @Column(name = "created_date")
   private LocalDateTime createdDate;
   @Column(name = "order_num")
   private Integer orderNum;

}
