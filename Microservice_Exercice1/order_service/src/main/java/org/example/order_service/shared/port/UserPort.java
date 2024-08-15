package org.example.order_service.shared.port;

import org.example.order_service.shared.dto.UserDTO;

public interface UserPort {

    UserDTO getUserById(int userId);
}
