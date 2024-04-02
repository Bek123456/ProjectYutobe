package org.example.projectyutobe.dto.info;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VideoLikeInfo {
    //id,video(id,name,channel(id,name),duration),preview_attach(id,url)
    private Integer id;
    private UUID video_id;
    private String video_name;
    private UUID video_channel_id;
    private String video_channel_name;
    private String video_attach_duration;
    private String video_preview_attach_id;
    private String video_preview_attach_url;

}
