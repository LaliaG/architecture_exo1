package org.example.order_service.infrastructure.springdata.repository;

import org.example.order_service.infrastructure.springdata.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
