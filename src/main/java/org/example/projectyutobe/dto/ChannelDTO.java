package org.example.projectyutobe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.ChannelStatus;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {
    private String name;
    private String photoId;
    private String description;
    private ChannelStatus status;
    private String bannerId;
    private Integer profileId;
    private Integer subscribeCount=0;
}
