package org.example.projectyutobe.controller;
import org.example.projectyutobe.dto.VideoTagDTO;
import org.example.projectyutobe.service.VideoTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/videotag")
public class VideoTagController {
    @Autowired
    private VideoTagService videoTagService;
    @PostMapping("/created")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>created(@RequestBody VideoTagDTO dto){
         return ResponseEntity.ok(videoTagService.created(dto));
    }

    @DeleteMapping("/deleted")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>delete(@RequestBody VideoTagDTO dto){
        return ResponseEntity.ok(videoTagService.deleted(dto));
    }
    @GetMapping("/getVideoTagList/{videoId}")
    public ResponseEntity<List<VideoTagDTO>>getVideoTagList(@PathVariable UUID videoId){
        return ResponseEntity.ok(videoTagService.getVideoTagList(videoId));
    }


}
