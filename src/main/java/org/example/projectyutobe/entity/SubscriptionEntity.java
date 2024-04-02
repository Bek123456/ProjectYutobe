package org.example.projectyutobe.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.NotificationType;
import org.example.projectyutobe.enums.SubscriptionStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "subscription_entity")
public class SubscriptionEntity {

//    id,profile_id,channel_id,created_date, unsubscribe_date, status (active,block),notification_type(All,Personalized,Non)
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;

      @Column(name = "profile_id")
      private Integer profileId;
      @ManyToOne
      @JoinColumn(name = "profile_id", insertable = false, updatable = false)
      private ProfileEntity profileEntity;


      @Column(name = "channel_id")
      private UUID channelId;
      @ManyToOne
      @JoinColumn(name = "channel_id", insertable = false, updatable = false)
      private ChannelEntity channelEntity;

      @Column(name = "created_date")
      private LocalDateTime createdDate;

      @Column(name = "unsubscribe_date")
      private LocalDateTime unsubscribeDate;

      @Column(name = "status")
      private SubscriptionStatus status;

      @Column(name = "notification_type")
      private NotificationType notificationType;

}
