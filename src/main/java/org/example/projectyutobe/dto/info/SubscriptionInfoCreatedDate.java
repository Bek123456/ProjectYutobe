package org.example.projectyutobe.dto.info;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SubscriptionInfoCreatedDate {
   private SubscriptionInfo info;
    private LocalDateTime created_date;
}
