package org.example.tp_architecture.infrastructure.springdata.repository;

import org.example.tp_architecture.infrastructure.springdata.entity.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Integer> {
}
