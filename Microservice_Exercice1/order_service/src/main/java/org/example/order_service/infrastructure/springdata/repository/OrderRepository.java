package org.example.order_service.infrastructure.springdata.repository;

import org.example.order_service.infrastructure.springdata.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
}
