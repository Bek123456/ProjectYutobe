package org.example.projectyutobe.controller;


import org.example.projectyutobe.config.CustomUserDetails;
import org.example.projectyutobe.dto.PlaylistDTO;
import org.example.projectyutobe.dto.info.PlayListShortInfo;
import org.example.projectyutobe.enums.ProfileRole;
import org.example.projectyutobe.service.PlayListService;
import org.example.projectyutobe.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlayListController {
   @Autowired
   private PlayListService playListService;
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String>create(@RequestBody PlaylistDTO dto){
        String s = playListService.create(dto);
        return ResponseEntity.ok(s);
    }
    @PutMapping("/update2/{playListId}")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>update(@RequestBody PlaylistDTO dto,
                                        @PathVariable Integer playListId){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(playListService.update(dto,customUserDetails.getId(),playListId));
    }
    @GetMapping("/changePlayList")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<List<PlaylistDTO>>changeStatus(@RequestBody PlaylistDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(playListService.changeStatus(dto,customUserDetails.getId()));
    }
    @DeleteMapping("/delete/{playListId}")
    @PreAuthorize("hasAnyRole('USER','OWNER','ADMIN')")
    public ResponseEntity<String>delete(@PathVariable Integer playListId){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        if (customUserDetails.getRole().equals(ProfileRole.ROLE_ADMIN)){
            return ResponseEntity.ok(playListService.deletedAdmin(playListId));
        }
       return ResponseEntity.ok(playListService.deletedUser(playListId,customUserDetails.getId()));
    }
    @GetMapping("/palayListPage")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageImpl<PlayListShortInfo>>playPagenation(@RequestParam(value = "page")Integer page,
                                                                     @RequestParam(value = "size")Integer size){
       PageImpl<PlayListShortInfo>playlistDTOS= playListService.playPage(page,size);
       return ResponseEntity.ok(playlistDTOS);
    }
    @GetMapping("/palayListPageDesc")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageImpl<PlayListShortInfo>>playPagenationDesc(@RequestParam(value = "page")Integer page,
                                                                         @RequestParam(value = "size")Integer size){
        PageImpl<PlayListShortInfo>playlistDTOS= playListService.playPageDesc(page,size);
        return ResponseEntity.ok(playlistDTOS);
    }

}

