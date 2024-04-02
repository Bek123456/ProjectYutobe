package org.example.projectyutobe.repository;


import org.example.projectyutobe.entity.VideoTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VideoTagRepository extends JpaRepository<VideoTagEntity,Integer> {
    boolean deleteByVideoIdAndTagId(UUID videoId, Integer tagId);
    List<VideoTagEntity>findAllByVideoIdAndTagId(UUID videoId, Integer tagId);
    List<VideoTagEntity>findAllByVideoId(UUID videoId);
}
