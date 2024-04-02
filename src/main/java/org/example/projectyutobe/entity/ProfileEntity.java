package org.example.projectyutobe.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.ProfileRole;
import org.example.projectyutobe.enums.ProfileStatus;


@Setter
@Getter
@Entity
@Table(name = "profile")
public class ProfileEntity extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ProfileRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProfileStatus status;
    @Column(name = "photo_id")
    private String photoId;
    @ManyToOne
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private AttachEntity attachEntity;
}

