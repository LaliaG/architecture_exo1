package org.example.user_service.infrastructure.springdata.repository;

import org.example.user_service.infrastructure.springdata.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
