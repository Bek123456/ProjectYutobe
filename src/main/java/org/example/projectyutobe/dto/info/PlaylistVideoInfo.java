package org.example.projectyutobe.dto.info;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class PlaylistVideoInfo {
//    playlist_id,video(id,preview_attach(id,url),title,duration),
//    channel(id,name),created_date, order_num

    private Integer playlist_id;
    private UUID video_id;
    private String preview_attach_id;
    private String preview_attach_url;
    private String title;
    private Long duration;
    private UUID channel_id;
    private String channel_name;
    private LocalDateTime created_date;
    private Integer order_num;
}
