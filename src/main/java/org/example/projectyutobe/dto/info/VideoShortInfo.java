package org.example.projectyutobe.dto.info;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoShortInfo {
//    VideShortInfo(id,title, preview_attach(id,url),
//    published_date, channel(id,name,photo(url)),
//    view_count,duration)
    private UUID videoId;
    private String videoTitle;
    private String preview_attachId;
    private String preview_attachUrl;
    private LocalDateTime published_date;
    private UUID channelId;
    private String channelName;
    private String channelPhotoUrl;
    private Integer video_view_count;
    private Long duration;
}
