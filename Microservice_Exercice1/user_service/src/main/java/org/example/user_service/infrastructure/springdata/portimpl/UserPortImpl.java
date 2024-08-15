package org.example.user_service.infrastructure.springdata.portimpl;

import org.example.user_service.infrastructure.springdata.entity.UserEntity;
import org.example.user_service.infrastructure.springdata.repository.UserRepository;
import org.example.user_service.shared.dto.UserDTO;
import org.example.user_service.shared.port.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPortImpl implements UserPort {

    private final UserRepository userRepository;

    @Autowired
    public UserPortImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findById(int id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userRepository.save(userEntity);
        return userDTO;
    }
}
