package org.example.projectyutobe.repository;


import org.example.projectyutobe.entity.CommentLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity,Integer> {
    List<CommentLikeEntity>findAllByOrderByCreatedDateDesc();
    List<CommentLikeEntity>findByProfileId(Integer profileId);
}
