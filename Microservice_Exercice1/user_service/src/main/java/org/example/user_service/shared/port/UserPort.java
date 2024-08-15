package org.example.user_service.shared.port;

import org.example.user_service.shared.dto.UserDTO;

public interface UserPort {

    UserDTO findById(int id);
    UserDTO save(UserDTO user);
}
