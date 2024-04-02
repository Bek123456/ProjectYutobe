package org.example.projectyutobe.repository;


import org.example.projectyutobe.entity.VideoLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VideoLikeRepository extends
        JpaRepository<VideoLikeEntity,Integer> {
    List<VideoLikeEntity>findByOrderByCratedDateDesc();
   List<VideoLikeEntity>findByProfileId(Integer profileId);
}
