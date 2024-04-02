package org.example.projectyutobe.controller;

import org.example.projectyutobe.config.CustomUserDetails;
import org.example.projectyutobe.dto.ChannelDTO;
import org.example.projectyutobe.service.ChannelService;
import org.example.projectyutobe.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/channel")
public class ChannelController {
   @Autowired
   private ChannelService channelService;
   //1
    @PostMapping("/created")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>created(@RequestBody ChannelDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(channelService.create(dto,customUserDetails.getId()));
    }
    //2
    @PutMapping("/update/{channelId}")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>update(@PathVariable(name = "channelId") UUID channelId,@RequestBody ChannelDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(channelService.update(customUserDetails.getId(),dto,channelId));
    }

    //3
    @PutMapping("/updatePhoto/{channelId}")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>updatePhoto(@PathVariable(name = "channelId") UUID channelId,
                                             @RequestBody ChannelDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(channelService.updatePhoto(customUserDetails.getId(),dto,channelId));
    }
    //4
    @PutMapping("/updateBanner/{channelId}")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>updateBanner(@PathVariable(name = "channelId") UUID channelId,
                                             @RequestBody ChannelDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(channelService.updateBanner(customUserDetails.getId(),dto,channelId));
    }
    //5
    @GetMapping("/getPagination")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageImpl<ChannelDTO>>getPaginationAdmin(@RequestParam(value = "page")Integer page,
                                                             @RequestParam(value = "size")Integer size){
        PageImpl<ChannelDTO> paginationAdmin = channelService.getPaginationAdmin(page, size);
        return ResponseEntity.ok(paginationAdmin);
    }
    //6
    @GetMapping("/getById/{channelId}")
    public ResponseEntity<ChannelDTO>getById(@PathVariable UUID channelId){
        return ResponseEntity.ok(channelService.getById(channelId));
    }
    //7
    @GetMapping("/changeStatus")
    @PreAuthorize("hasAnyRole('ADMIN','USER','OWNER')")
    public ResponseEntity<List<ChannelDTO>>changeStatus(@RequestBody ChannelDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        List<ChannelDTO> channelDTOList = channelService.changeStatus(dto, customUserDetails.getId());
        return ResponseEntity.ok(channelDTOList);
    }
    //8
    @GetMapping("/userchannellist")
    public ResponseEntity<List<ChannelDTO>>getUserList(){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(channelService.getUserList(customUserDetails.getId()));
    }


}


