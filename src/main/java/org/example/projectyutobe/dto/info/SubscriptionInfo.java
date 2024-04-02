package org.example.projectyutobe.dto.info;


import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.NotificationType;

import java.util.UUID;
@Setter
@Getter
public class SubscriptionInfo {
    private Integer id;
    private UUID channelId;
    private String channelName;
    private String channelPhotoId;
    private String channelPhotoUrl;
    private NotificationType notificationType;
}
