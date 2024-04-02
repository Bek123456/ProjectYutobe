package org.example.projectyutobe.service;


import org.example.projectyutobe.dto.VideoLikeDTO;
import org.example.projectyutobe.dto.info.VideoLikeInfo;
import org.example.projectyutobe.entity.VideoLikeEntity;
import org.example.projectyutobe.exp.AppBadException;
import org.example.projectyutobe.repository.VideoLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoLikeService {
    @Autowired
    private VideoLikeRepository videoLikeRepository;
    public String created(VideoLikeDTO dto) {
        VideoLikeEntity entity =new VideoLikeEntity();
        entity.setType(dto.getType());
        entity.setVideoId(dto.getVideoId());
        entity.setProfileId(dto.getProfileId());
        entity.setCratedDate(LocalDateTime.now());
        videoLikeRepository.save(entity);
        return "created VideoLike";
    }

    public String remove(Integer videoLikeId) {
        videoLikeRepository.deleteById(videoLikeId);
        return "deleted videoLike";
    }

    public List<VideoLikeDTO> getUserLikedVideoList() {
        List<VideoLikeEntity> byOrderByCratedDateDesc = videoLikeRepository.findByOrderByCratedDateDesc();
        List<VideoLikeDTO>dtoList=new ArrayList<>();
        for (VideoLikeEntity entity:byOrderByCratedDateDesc){
            VideoLikeDTO dto=new VideoLikeDTO();
            dto.setCratedDate(entity.getCratedDate());
            dto.setType(entity.getType());
            dto.setProfileId(entity.getProfileId());
            dto.setVideoId(entity.getVideoId());
            dto.setId(entity.getId());
            dtoList.add(dto);
        }
        return dtoList;
    }


    public VideoLikeInfo getUserLikedVideoListUser(Integer userId) {
        List<VideoLikeEntity> byProfileId = videoLikeRepository.findByProfileId(userId);
        if (byProfileId.isEmpty()){
            throw new AppBadException("Not found profile video_like");
        }
        VideoLikeEntity entity = byProfileId.get(0);
        VideoLikeInfo info=new VideoLikeInfo();
        info.setVideo_name(entity.getVideoEntity().getAttachEntity().getOriginName());
        info.setVideo_preview_attach_id(entity.getVideoEntity().getPreviewAttachId());
        info.setVideo_preview_attach_url(entity.getVideoEntity().getAttachEntity().getPath());
        info.setId(entity.getId());
        info.setVideo_id(entity.getVideoId());
        info.setVideo_channel_id(entity.getVideoEntity().getChannelId());
        return info;
    }
}
