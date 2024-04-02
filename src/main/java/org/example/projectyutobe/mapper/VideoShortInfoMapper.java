package org.example.projectyutobe.mapper;

import java.time.LocalDateTime;

public interface VideoShortInfoMapper {
    public String getId();

    public String geTitle();

    public String getPreviewAttachId();

    public LocalDateTime getPublishedDate();

    public Long getViewCount();

    public Integer getChannel();

    public String getChannelName();

    public String getPhotoId();
}