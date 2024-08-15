package org.example.infrastructure_spring_data.repository;

import org.example.infrastructure_spring_data.entity.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Integer> {
}
