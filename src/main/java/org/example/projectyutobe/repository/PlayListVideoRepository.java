package org.example.projectyutobe.repository;


import org.example.projectyutobe.entity.PlayListVideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PlayListVideoRepository extends
        JpaRepository<PlayListVideoEntity,Integer> {
    List<PlayListVideoEntity>findByVideoIdAndPlayListId(UUID videoId, Integer playListId);
    List<PlayListVideoEntity>findByPlayListId(Integer playListId);
}
