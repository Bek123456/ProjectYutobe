package org.example.projectyutobe.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.PlaylistStatus;

import java.util.UUID;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaylistDTO {

    private Integer id;

    private UUID channelId;

    private String name;

    private String description;

    private PlaylistStatus status;

    private Integer orderNumber;
}
