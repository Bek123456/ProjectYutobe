package org.example.projectyutobe.controller;

import org.example.projectyutobe.config.CustomUserDetails;
import org.example.projectyutobe.dto.ChangeDTO;
import org.example.projectyutobe.dto.ProfileDTO;
import org.example.projectyutobe.dto.RegistrationDTO;
import org.example.projectyutobe.service.ProfileService;
import org.example.projectyutobe.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
     @PostMapping("/create")
     @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String>creted(@RequestBody RegistrationDTO registrationDTO){
       return ResponseEntity.ok(profileService.created(registrationDTO));
     }
     @PutMapping("/chanchePasword")
     @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR')")
     public ResponseEntity<String>changePassword(@RequestBody ChangeDTO parol){
         CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
         return ResponseEntity.ok(profileService.changePassword(parol.getPassword(),customUserDetails.getId()));
     }
     @PutMapping("/updatemail")
     @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR')")
     public ResponseEntity<String>changeEmail(@RequestBody ChangeDTO email){
         CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
         return ResponseEntity.ok(profileService.changeEmail(email,customUserDetails.getId()));
     }
     @PutMapping("/updateNameSurname")
     @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR')")
     public ResponseEntity<String>updateNameSurname(@RequestBody ChangeDTO dto){
         CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
         return ResponseEntity.ok(profileService.updateNameSurname(customUserDetails.getId(),dto));
     }
     @GetMapping("/getProfildteil")
     public ResponseEntity<List<ProfileDTO>>getProfileDetail(){
         return ResponseEntity.ok(profileService.getProfileDetail());
     }

     @PutMapping("/updatePhoto")
    public ResponseEntity<String>updatePhoto(@RequestParam(value = "profileId")Integer profileId,
                                             @RequestParam(value = "photoId")String photoId){
         return null;
     }

}
