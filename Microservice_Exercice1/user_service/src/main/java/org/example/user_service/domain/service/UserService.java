package org.example.user_service.domain.service;

import org.example.user_service.shared.dto.UserDTO;
import org.example.user_service.shared.port.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserPort userPort;

    @Autowired
    public UserService(UserPort userPort) {
        this.userPort = userPort;
    }

    public UserDTO getUserById(int id) {
        return userPort.findById(id);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        return userPort.save(userDTO);
    }
}
