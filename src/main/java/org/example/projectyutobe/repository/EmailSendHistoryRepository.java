package org.example.projectyutobe.repository;


import org.example.projectyutobe.entity.EmailSendHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface EmailSendHistoryRepository extends JpaRepository<EmailSendHistoryEntity,Integer> {

    @Query("SELECT count (s) from EmailSendHistoryEntity s where s.email =?1 and s.createdDate between ?2 and ?3")
    Long countSendEmail(String email, LocalDateTime from, LocalDateTime to);
}
