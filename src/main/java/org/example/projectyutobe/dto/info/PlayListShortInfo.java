package org.example.projectyutobe.dto.info;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.PlaylistStatus;

import java.util.UUID;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayListShortInfo {
    private Integer id;
    private String name;
    private String description;
    private PlaylistStatus status;
    private Integer orderNumber;
    private UUID channelId;
    private String channelName;
    private String channelPhotoId;
    private String channelPhotoUrl;
    private Integer profileId;
    private String profileName;
    private String profileSurname;
    private String profilePhotoId;
    private String profilePhotoUrl;

}
