package org.example.projectyutobe.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.NotificationType;
import org.example.projectyutobe.enums.SubscriptionStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionDTO {

    private Integer id;

    private Integer profileId;

    private UUID channelId;

    private LocalDateTime createdDate;

    private LocalDateTime unsubscribeDate;

    private SubscriptionStatus status;

    private NotificationType notificationType;
}
