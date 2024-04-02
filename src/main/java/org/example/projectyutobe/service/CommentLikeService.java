package org.example.projectyutobe.service;

import org.example.projectyutobe.dto.CommentLikeDTO;
import org.example.projectyutobe.dto.info.CommentLikeInfo;
import org.example.projectyutobe.entity.CommentLikeEntity;
import org.example.projectyutobe.exp.AppBadException;
import org.example.projectyutobe.repository.CommentLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

;

@Service
public class CommentLikeService {


    @Autowired
    private CommentLikeRepository commentLikeRepository;
    public String created(CommentLikeDTO dto) {
        CommentLikeEntity entity=new CommentLikeEntity();
        entity.setCommentId(dto.getCommentId());
        entity.setProfileId(dto.getProfileId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setType(dto.getType());
        commentLikeRepository.save(entity);
        return "created commentLike";
    }

    public String remove(Integer id) {
        commentLikeRepository.deleteById(id);
        return "deleted commentlike";
    }

    public List<CommentLikeInfo> getLike() {
        List<CommentLikeEntity> allByOrderByCreatedDateDesc =
                commentLikeRepository.findAllByOrderByCreatedDateDesc();
        List<CommentLikeInfo>infoList=new ArrayList<>();
        for (CommentLikeEntity entity:allByOrderByCreatedDateDesc){
           infoList.add(getCommentInfo(entity));
        }
        return infoList;

    }
    public CommentLikeInfo getCommentInfo(CommentLikeEntity entity){
        CommentLikeInfo info=new CommentLikeInfo();
        info.setComment_id(entity.getCommentId());
        info.setId(entity.getId());
        info.setType(entity.getType());
        info.setCreated_date(entity.getCreatedDate());
        info.setProfile_id(entity.getProfileId());
        return info;
    }

    public CommentLikeInfo getLikeUser(Integer userId) {
        List<CommentLikeEntity> byProfileId = commentLikeRepository.findByProfileId(userId);
        if (byProfileId.isEmpty()){
            throw new AppBadException("NOT FOUND PROFILE");
        }
        CommentLikeEntity entity = byProfileId.get(0);
        return getCommentInfo(entity);
    }
}
