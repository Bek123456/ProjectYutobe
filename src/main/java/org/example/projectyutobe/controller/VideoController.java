package org.example.projectyutobe.controller;


import org.example.projectyutobe.dto.PageDTO;
import org.example.projectyutobe.dto.VideoDTO;
import org.example.projectyutobe.dto.info.VideoShortInfo;
import org.example.projectyutobe.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    //1
   @PostMapping("/create")
   @PreAuthorize("hasAnyRole('USER','OWNER')")
   public ResponseEntity<String>create(@RequestBody VideoDTO dto){
       return ResponseEntity.ok(videoService.create(dto));
   }
 //2
  @PutMapping("/update/{videoId}")
  @PreAuthorize("hasAnyRole('USER','OWNER')")
  public ResponseEntity<String>update(@RequestBody VideoDTO dto,
                                        @PathVariable UUID videoId){
       return ResponseEntity.ok(videoService.update(dto,videoId));
  }
   //3
    @PutMapping("/updateChanche/{videoId}")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>updateChangeStatus(@RequestBody VideoDTO dto,
                                                    @PathVariable UUID videoId){
      return ResponseEntity.ok(videoService.updateChangeStatus(dto,videoId));
    }
    //4
   @GetMapping("/getViewCount/{videoId}")
    public ResponseEntity<VideoDTO>getViewCount(@PathVariable UUID videoId){
      return ResponseEntity.ok(videoService.getViewCount(videoId));
   }
//5
   @GetMapping("/getPaginationCategoryId")
    public ResponseEntity<PageImpl<VideoShortInfo>>getPaginationCategoryId1(@RequestParam(value = "page")Integer page,
                                                                            @RequestParam(value = "size")Integer size,
                                                                            @RequestParam(value = "categoryId") Integer categoryId){
       return ResponseEntity.ok(videoService.getPaginationCategoryId(page,size,categoryId));
   }
//6
    @GetMapping("/getVideoByTitle")
    public ResponseEntity<List<VideoShortInfo>>getVideoTitle(@RequestParam(value = "title")String title){
        return ResponseEntity.ok(videoService.getVideoTitle(title));
    }

    //7
    @GetMapping("/getVideoTag/{tag_id}")
    public ResponseEntity<PageImpl<VideoShortInfo>>getVideoTag(@PathVariable Integer tag_id,@RequestBody PageDTO dto){
        PageImpl<VideoShortInfo> videoTag = videoService.getVideoTag(tag_id,dto.getPage(),dto.getSize());
        return ResponseEntity.ok(videoTag);
    }




}










