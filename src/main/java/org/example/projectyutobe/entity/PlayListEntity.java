package org.example.projectyutobe.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.PlaylistStatus;

import java.util.UUID;
@Setter
@Getter
@Entity
@Table(name = "playlist_entity")
public class PlayListEntity  {
    ////id,channel_id,name,description,status(private,public),order_num
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "channel_id")
   private UUID channelId;
   @ManyToOne
   @JoinColumn(name = "channel_id", insertable = false, updatable = false)
   private ChannelEntity channelEntity;

   @Column(name = "name")
   private String name;

   @Column(name = "description",columnDefinition = "TEXT")
   private String description;

   @Column(name = "status")
   private PlaylistStatus status;

   @Column(name = "order_num")
   private Integer orderNum;

}
