package org.example.projectyutobe.config;


import org.example.projectyutobe.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    public static final String[]AUTH_WHITELIST={
            "/api/auth/*",
            "/api/auth/verification/email/*",
            "/attach/upload",
            "/attach/openById/*",
            "/attach/download/*",
            "/profile/getProfildteil",
            "/tag/create",
            "/tag/getAll",
            "/channel/getById/*",
            "/controller/getAll",
            "/video/getViewCount/*",
            "/video/getPaginationCategoryId",
            "/video/getVideoByTitle",
            "/video/getVideoTag/*",
            "/videotag/getVideoTagList/*",
            "/playlistvideo/update/*",
            "/playlistvideo/deleted",
            "playlistvideo/getAll/*",
            "/comment/getCommitVideoId/*",
            "/video_like/created",
            "/video_like/deleted/*",
            "/comment_like/create",
            "/comment_like/remove/*"

    };
    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry
                    .requestMatchers(AUTH_WHITELIST).permitAll()
                    .requestMatchers("/region/adm/*").hasRole("ADMIN")
                    .requestMatchers("/articletype/adm/*").hasRole("ADMIN")
                    .requestMatchers("/category/adm/*").hasRole("ADMIN")
                    .requestMatchers("/article/publish").hasAnyRole("ADMIN","PUBLISHER")
                    .anyRequest()
                    .authenticated();
        });
        //http.httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        return http.build();
    }
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return MDUtil.encode(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }
}
