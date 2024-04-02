package org.example.projectyutobe.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.ChannelStatus;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "channel_entity")
public class ChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo_id")
    private String photoId;
    @OneToOne
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private AttachEntity attachEntity;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ChannelStatus status;

    @Column(name = "banner_id")
    private String bannerId;
    @OneToOne
    @JoinColumn(name = "banner_id", insertable = false, updatable = false)
    private AttachEntity bannerEntity;


    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profileEntity;

}
