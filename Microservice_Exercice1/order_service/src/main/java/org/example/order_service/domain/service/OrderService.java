package org.example.order_service.domain.service;

import org.example.order_service.infrastructure.restclient.impl.UserPortImpl;
import org.example.order_service.shared.dto.OrderDTO;
import org.example.order_service.shared.dto.UserDTO;
import org.example.order_service.shared.port.OrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderPort orderPort;
    private final UserPortImpl userPort;

    @Autowired
    public OrderService(OrderPort orderPort, UserPortImpl userPort) {
        this.orderPort = orderPort;
        this.userPort = userPort;
    }

    public OrderDTO getOrderById(int id) {
        OrderDTO order = orderPort.findById(id);
        UserDTO user = userPort.getUserById(order.getUserId());
        return order;
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        return orderDTO;
    }
}
