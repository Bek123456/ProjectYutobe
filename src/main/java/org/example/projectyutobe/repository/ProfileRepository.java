package org.example.projectyutobe.repository;


import jakarta.transaction.Transactional;
import org.example.projectyutobe.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByEmail(String username);

    Optional<ProfileEntity> findByEmailAndPassword(String email, String encode);
    @Transactional
    @Modifying
    @Query("Update ProfileEntity  set email =?2 where id = ?1")
    void updateEmail(Integer id, String active);
}
