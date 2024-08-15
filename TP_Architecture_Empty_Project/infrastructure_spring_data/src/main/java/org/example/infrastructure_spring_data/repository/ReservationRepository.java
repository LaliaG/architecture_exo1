package org.example.infrastructure_spring_data.repository;

import org.example.infrastructure_spring_data.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {
}
