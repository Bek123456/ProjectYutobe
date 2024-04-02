package org.example.projectyutobe.controller;


import org.example.projectyutobe.dto.PlayListVideoDTO;
import org.example.projectyutobe.dto.info.PlaylistVideoInfo;
import org.example.projectyutobe.service.PlayListVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlistvideo")
public class PlayListVideoController {
    @Autowired
    private PlayListVideoService playListVideoService;

    @PostMapping("/created")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>create(@RequestBody PlayListVideoDTO dto){
        return ResponseEntity.ok(playListVideoService.created(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String>update(@RequestBody PlayListVideoDTO dto,@PathVariable Integer id){
        return ResponseEntity.ok(playListVideoService.update(dto,id));
    }
    @DeleteMapping("/deleted")
    public ResponseEntity<String>deleted(@RequestBody PlayListVideoDTO dto){
        return ResponseEntity.ok(playListVideoService.deleted(dto));
    }
    @GetMapping("/getAll/{playListId}")
    public ResponseEntity<List<PlaylistVideoInfo>>getAll(@PathVariable Integer playListId){
       return ResponseEntity.ok(playListVideoService.getAll(playListId));
    }

}
