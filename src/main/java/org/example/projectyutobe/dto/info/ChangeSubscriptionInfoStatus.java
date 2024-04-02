package org.example.projectyutobe.dto.info;


import lombok.Getter;
import lombok.Setter;
import org.example.projectyutobe.enums.SubscriptionStatus;

import java.util.UUID;

@Setter
@Getter
public class ChangeSubscriptionInfoStatus {
   private SubscriptionStatus status;
   private UUID channel_id;
}
