package org.example.projectyutobe.config;


import org.example.projectyutobe.entity.ProfileEntity;
import org.example.projectyutobe.exp.AppBadException;
import org.example.projectyutobe.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(username);
        if (optional.isEmpty()) {
            throw new AppBadException("Bad Credentials. Mazgi");
        }

        ProfileEntity profile = optional.get();
        return new CustomUserDetails(profile.getId(), profile.getEmail(),
                profile.getPassword(), profile.getStatus(), profile.getRole());
    }
}
