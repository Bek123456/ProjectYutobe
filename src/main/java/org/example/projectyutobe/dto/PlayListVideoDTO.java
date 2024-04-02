package org.example.projectyutobe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PlayListVideoDTO {
    private Integer playlist_id;
    private UUID video_id;
    private Integer order_num;
}
