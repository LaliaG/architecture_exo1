package org.example.order_service.infrastructure.springdata.impl;

import org.example.order_service.infrastructure.springdata.entity.OrderEntity;
import org.example.order_service.infrastructure.springdata.repository.OrderRepository;
import org.example.order_service.shared.dto.OrderDTO;
import org.example.order_service.shared.port.OrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPortImpl implements OrderPort {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderPortImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO findById(int id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);
        return new OrderDTO(orderEntity.getId(), orderEntity.getUserId(), orderEntity.getProductId());
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDTO.getId());
        orderEntity.setUserId(orderDTO.getUserId());
        orderEntity.setProductId(orderDTO.getProductId());
        orderRepository.save(orderEntity);
        return orderDTO;
    }
}
