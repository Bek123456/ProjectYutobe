package org.example.projectyutobe.service;


import lombok.extern.slf4j.Slf4j;
import org.example.projectyutobe.dto.AuthDTO;
import org.example.projectyutobe.dto.ProfileDTO;
import org.example.projectyutobe.entity.ProfileEntity;
import org.example.projectyutobe.enums.AppLanguage;
import org.example.projectyutobe.enums.ProfileStatus;
import org.example.projectyutobe.exp.AppBadException;
import org.example.projectyutobe.repository.ProfileRepository;
import org.example.projectyutobe.util.JWTUtil;
import org.example.projectyutobe.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceBundleService resourceBundleService;

    public ProfileDTO auth(AuthDTO profile, AppLanguage language) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(profile.getEmail(),
                MDUtil.encode(profile.getPassword()));

        if (optional.isEmpty()) {
            //   String message = resourceBundleMessageSource.getMessage("email.password.wrong", null, new Locale(language.name()));

            log.warn("Email or Password is wrong{}",profile.getEmail());
            throw new AppBadException(resourceBundleService.getMessage("email.password.wrong",language));
        }

        ProfileEntity entity = optional.get();

        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)){
            throw new AppBadException("Profile not active");
        }
        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setRole(entity.getRole());
        dto.setJwt(JWTUtil.encode(entity.getEmail()

                ,entity.getRole()));
        return dto;
    }

}
