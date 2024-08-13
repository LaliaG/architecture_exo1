package org.example.tp_architecture.infrastructure.springdata.repository;

import org.example.tp_architecture.infrastructure.springdata.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {
}
