package org.example.projectyutobe.repository;


import org.example.projectyutobe.entity.SubscriptionEntity;
import org.example.projectyutobe.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository
        extends JpaRepository<SubscriptionEntity,Integer> {
    List<SubscriptionEntity> findAllByProfileId(Integer profileId);
    List<SubscriptionEntity>findByChannelIdAndStatus(UUID channelId, SubscriptionStatus status);
    List<SubscriptionEntity>findAllByChannelId(UUID channelId);
}
