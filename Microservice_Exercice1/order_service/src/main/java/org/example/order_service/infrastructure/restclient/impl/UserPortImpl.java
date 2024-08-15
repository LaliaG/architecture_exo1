package org.example.order_service.infrastructure.restclient.impl;

import org.example.order_service.shared.dto.UserDTO;
import org.example.order_service.shared.port.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserPortImpl implements UserPort {

    private final RestTemplate restTemplate;

    @Autowired
    public UserPortImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDTO getUserById(int userId) {
        String url = "http://localhost:8081/users/" + userId;
        return restTemplate.getForObject(url, UserDTO.class);
    }
}
