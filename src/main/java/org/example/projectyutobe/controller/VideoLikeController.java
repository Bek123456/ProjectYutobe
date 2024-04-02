package org.example.projectyutobe.controller;


import org.example.projectyutobe.dto.VideoLikeDTO;
import org.example.projectyutobe.dto.info.VideoLikeInfo;
import org.example.projectyutobe.service.VideoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video_like")
public class VideoLikeController {
    @Autowired
    private VideoLikeService videoLikeService;
    @PostMapping("/created")
    public ResponseEntity<String>created(@RequestBody VideoLikeDTO dto){
        return ResponseEntity.ok(videoLikeService.created(dto));
    }

    @DeleteMapping("/deleted/{video_like_id}")
    public ResponseEntity<String>remove(@PathVariable Integer video_like_id){
        return ResponseEntity.ok(videoLikeService.remove(video_like_id));
    }

    @GetMapping("/userlikedvideolist")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<VideoLikeDTO>>getUserLikedVideoList(){
        return ResponseEntity.ok(videoLikeService.getUserLikedVideoList());
    }

    @GetMapping("/userlikedvideolistuserid/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<VideoLikeInfo>getUserLikedVideoListUser(@PathVariable Integer userId){
        return  ResponseEntity.ok(videoLikeService.getUserLikedVideoListUser(userId));
    }


}
