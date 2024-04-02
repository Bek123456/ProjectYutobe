package org.example.projectyutobe.service;


import org.example.projectyutobe.dto.PlayListVideoDTO;
import org.example.projectyutobe.dto.info.PlaylistVideoInfo;
import org.example.projectyutobe.entity.PlayListVideoEntity;
import org.example.projectyutobe.enums.VideoStatus;
import org.example.projectyutobe.exp.AppBadException;
import org.example.projectyutobe.repository.PlayListVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayListVideoService {
    @Autowired
    private PlayListVideoRepository playListVideoRepository;
    public String created(PlayListVideoDTO dto) {
        PlayListVideoEntity entity=new PlayListVideoEntity();
        entity.setPlayListId(dto.getPlaylist_id());
        entity.setVideoId(dto.getVideo_id());
        entity.setOrderNum(dto.getOrder_num());
        entity.setCreatedDate(LocalDateTime.now());
        playListVideoRepository.save(entity);
        return "created playlistvideo";
    }

    public String update(PlayListVideoDTO dto,Integer id) {
        Optional<PlayListVideoEntity> optionalPlayListVideo =
                playListVideoRepository.findById(id);
        if (optionalPlayListVideo.isEmpty()){
            throw new AppBadException("not found playlist_video");
        }
        PlayListVideoEntity entity = optionalPlayListVideo.get();
        entity.setVideoId(dto.getVideo_id());
        entity.setPlayListId(dto.getPlaylist_id());
        entity.setOrderNum(dto.getOrder_num());
        return "update playlist_video";
    }

    public String deleted(PlayListVideoDTO dto) {
        List<PlayListVideoEntity> byVideoIdAndPlayListId = playListVideoRepository.findByVideoIdAndPlayListId(dto.getVideo_id(), dto.getPlaylist_id());
        playListVideoRepository.deleteAll(byVideoIdAndPlayListId);
        return "deleted playlist_video";
    }

    public List<PlaylistVideoInfo> getAll(Integer playListId) {
        List<PlayListVideoEntity> byPlayListId = playListVideoRepository.findByPlayListId(playListId);
        List<PlaylistVideoInfo>playlistVideoInfos=new ArrayList<>();
        for (PlayListVideoEntity entity:byPlayListId){
            if (entity.getVideoEntity().getStatus().equals(VideoStatus.PUBLIC)){
                playlistVideoInfos.add(toInfo(entity));
            }
        }
        return playlistVideoInfos;
    }

    public PlaylistVideoInfo toInfo(PlayListVideoEntity entity){
        PlaylistVideoInfo info=new PlaylistVideoInfo();

        info.setVideo_id(entity.getVideoId());
        info.setDuration(entity.getVideoEntity().getAttachEntity().getDuration());
        info.setChannel_name(entity.getVideoEntity().getChannelEntity().getName());
        info.setChannel_id(entity.getVideoEntity().getChannelId());
        info.setTitle(entity.getVideoEntity().getTitle());
        info.setOrder_num(entity.getOrderNum());
        info.setPlaylist_id(entity.getPlayListId());
        info.setCreated_date(entity.getCreatedDate());
        info.setPreview_attach_id(entity.getVideoEntity().getPreviewAttachId());
        info.setPreview_attach_url(entity.getVideoEntity().getPrevieiwAttachEntity().getPath());
        return info;
    }
}
