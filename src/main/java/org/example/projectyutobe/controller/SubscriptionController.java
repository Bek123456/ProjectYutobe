package org.example.projectyutobe.controller;


import org.example.projectyutobe.config.CustomUserDetails;
import org.example.projectyutobe.dto.SubscriptionDTO;
import org.example.projectyutobe.dto.info.ChangeSubscriptionInfoNotificationType;
import org.example.projectyutobe.dto.info.ChangeSubscriptionInfoStatus;
import org.example.projectyutobe.dto.info.SubscriptionInfo;
import org.example.projectyutobe.dto.info.SubscriptionInfoCreatedDate;
import org.example.projectyutobe.service.SubscriptionService;
import org.example.projectyutobe.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String>created(@RequestBody SubscriptionDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(subscriptionService.created(dto,customUserDetails.getId()));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String>changeSubscription(@RequestBody ChangeSubscriptionInfoStatus info){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(subscriptionService.changeSubscription(info,customUserDetails.getId()));
    }

    @PutMapping("/updateNotificationType")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String>changeSubscriptionNotificationType(@RequestBody
                                                                    ChangeSubscriptionInfoNotificationType type){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(subscriptionService.changeSubscriptionNotificationType(customUserDetails.getId(),type));
    }

    @GetMapping("/getusersubscription")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<SubscriptionInfo>>getusersubscription(){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(subscriptionService.getUserSubscription(customUserDetails.getId()));
    }

    @GetMapping("/getusersubscriptionadmin/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<SubscriptionInfoCreatedDate>>getUserSubscriptionAdmin(@PathVariable Integer userId){
//        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(subscriptionService.getUserSubscriptionAdmin(userId));
    }


}
