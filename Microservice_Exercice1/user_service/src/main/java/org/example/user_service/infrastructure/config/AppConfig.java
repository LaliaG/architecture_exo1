package org.example.user_service.infrastructure.config;

import org.example.user_service.domain.service.UserService;
import org.example.user_service.infrastructure.springdata.portimpl.UserPortImpl;
import org.example.user_service.infrastructure.springdata.repository.UserRepository;
import org.example.user_service.shared.port.UserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserService userService(UserPort userPort) {
        return new UserService(userPort);
    }

    @Bean
    public UserPort userPort(UserRepository userRepository) {
        return new UserPortImpl(userRepository);
    }
}
