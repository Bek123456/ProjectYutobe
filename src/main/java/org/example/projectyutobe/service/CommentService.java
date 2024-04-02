package org.example.projectyutobe.service;


import org.example.projectyutobe.config.CustomUserDetails;
import org.example.projectyutobe.dto.CommentDTO;
import org.example.projectyutobe.dto.info.CommentInfo;
import org.example.projectyutobe.dto.info.CommentProfileInfo;
import org.example.projectyutobe.entity.CommentEntity;
import org.example.projectyutobe.enums.ProfileRole;
import org.example.projectyutobe.exp.AppBadException;
import org.example.projectyutobe.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    public String created(CommentDTO dto, Integer id) {
        CommentEntity entity = toDo(dto);
        entity.setProfileId(id);
        commentRepository.save(entity);
        return "created comment";
    }
    private CommentEntity toDo(CommentDTO dto){
        CommentEntity entity=new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setReplyId(dto.getReplyId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setDislike_count(dto.getDislike_count());
        entity.setLike_count(dto.getLike_count());
        entity.setVideoId(dto.getVideoId());
        return entity;
    }


    public String updateUser(CommentDTO dto, Integer commentId, Integer user_id) {
        Optional<CommentEntity> entityOptional = commentRepository.findById(commentId);
        if (entityOptional.isEmpty()){
            throw new AppBadException("not found Comment");
        }
        CommentEntity entity = entityOptional.get();
        if (!entity.getProfileId().equals(user_id)){
            throw new AppBadException("not found profile comment");
        }
        entity.setContent(dto.getContent());
        entity.setReplyId(dto.getReplyId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setDislike_count(dto.getDislike_count());
        entity.setLike_count(dto.getLike_count());
        entity.setVideoId(dto.getVideoId());
        commentRepository.save(entity);
        return "update comment owner";
    }


    public String updateAdmin(CommentDTO dto, Integer commentId) {
        Optional<CommentEntity> entityOptional = commentRepository.findById(commentId);
        if (entityOptional.isEmpty()){
            throw new AppBadException("not found Comment");
        }
        CommentEntity entity = entityOptional.get();
        entity.setContent(dto.getContent());
        entity.setReplyId(dto.getReplyId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setDislike_count(dto.getDislike_count());
        entity.setLike_count(dto.getLike_count());
        entity.setVideoId(dto.getVideoId());
        commentRepository.save(entity);
        return "update comment user";
    }


    public String deleted(CustomUserDetails customUserDetails, Integer commentId) {
          if (customUserDetails.getRole().equals(ProfileRole.ROLE_ADMIN)){
              return "deleted admin comment";
          }
          if (customUserDetails.getRole().equals(ProfileRole.ROLE_OWNER)){
             return "deleted owner comment";
          }
          return "deleted user comment";
    }

    public PageImpl<CommentDTO> getPage(Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page-1,size);
        Page<CommentEntity> page1=commentRepository.findAll(pageable);
        List<CommentEntity> commentEntities=page1.getContent();
        List<CommentDTO>commentDTOList=new ArrayList<>();
        for (CommentEntity playListEntity:commentEntities){
            commentDTOList.add(getDtp(playListEntity));
        }
        Long totalSize=page1.getTotalElements();
        return new PageImpl<>(commentDTOList,pageable,totalSize);
    }

    public CommentDTO getDtp(CommentEntity entity){
        CommentDTO dto=new CommentDTO();
        dto.setContent(entity.getContent());
        dto.setDislike_count(entity.getDislike_count());
        dto.setLike_count(entity.getLike_count());
        dto.setProfileId(entity.getProfileId());
        dto.setVideoId(entity.getVideoId());
        dto.setReplyId(entity.getReplyId());
        return dto;
    }

    public CommentProfileInfo getCommitListProfileId(Integer profileId) {
        Optional<CommentEntity> optionalComment = commentRepository.findByProfileId(profileId);
        if (optionalComment.isEmpty()){
            throw new AppBadException("not found comment");
        }
        CommentEntity entity = optionalComment.get();
        return getCommentProfileInfo(entity);

    }
    public CommentProfileInfo getCommentProfileInfo(CommentEntity entity){
        CommentProfileInfo info=new CommentProfileInfo();
        info.setId(entity.getId());//1
        info.setTitle(entity.getVideoEntity().getTitle());//2
        info.setVideoId(entity.getVideoId());//3
        info.setDislike_count(entity.getDislike_count());//4
        info.setLike_count(entity.getLike_count());//5
        info.setCreatedDate(entity.getCreatedDate());//6
        info.setContent(entity.getContent());//7
        info.setVideoChannelName(entity.getVideoEntity().getChannelEntity().getName());//8
        info.setPreviewAttachId(entity.getVideoEntity().getPreviewAttachId());//9
        info.setDuration(entity.getVideoEntity().getChannelEntity().getAttachEntity().getDuration());
        return info;
    }


    public List<CommentInfo> getCommitVideoId(UUID videoId) {
        List<CommentEntity> byVideoId = commentRepository.findByVideoId(videoId);
        List<CommentInfo>commentInfos=new ArrayList<>();
        for (CommentEntity entity:byVideoId){
            CommentInfo info=new CommentInfo();
            info.setId(entity.getId());
            info.setProfileName(entity.getProfileEntity().getName());
            info.setDislike_count(entity.getDislike_count());
            info.setLike_count(entity.getLike_count());
            info.setContent(entity.getContent());
            info.setProfileId(entity.getProfileId());
            info.setCreated_date(entity.getCreatedDate());
            info.setProfileUrl(entity.getProfileEntity().getAttachEntity().getPath());
            info.setProfilePhotoId(entity.getProfileEntity().getPhotoId());
            info.setProfileSurname(entity.getProfileEntity().getSurname());
            commentInfos.add(info);
        }
        return commentInfos;
    }
}
