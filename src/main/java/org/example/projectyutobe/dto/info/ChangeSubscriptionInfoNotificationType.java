package org.example.projectyutobe.dto.info;

import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.NotificationType;

import java.util.UUID;
@Getter
@Setter
public class ChangeSubscriptionInfoNotificationType {
    private NotificationType type;
    private UUID channel_id;
}
