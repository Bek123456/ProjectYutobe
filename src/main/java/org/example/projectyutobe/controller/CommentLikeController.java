package org.example.projectyutobe.controller;


import org.example.projectyutobe.dto.CommentLikeDTO;
import org.example.projectyutobe.dto.info.CommentLikeInfo;
import org.example.projectyutobe.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment_like")
public class CommentLikeController {
     @Autowired
     private CommentLikeService commentLikeService;
    @PostMapping("/create")
    public ResponseEntity<String>created(@RequestBody CommentLikeDTO dto){
        return ResponseEntity.ok(commentLikeService.created(dto));
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String>remove(@PathVariable Integer id){
       return  ResponseEntity.ok(commentLikeService.remove(id));
    }
    @GetMapping("/getlike")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<CommentLikeInfo>>getLike(){
        return ResponseEntity.ok(commentLikeService.getLike());
    }
    @GetMapping("/getlikeuser/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CommentLikeInfo>getLikeUser(@PathVariable Integer userId){
        return ResponseEntity.ok(commentLikeService.getLikeUser(userId));
    }
}
