package org.example.order_service.shared.port;

import org.example.order_service.shared.dto.OrderDTO;

public interface OrderPort {

    OrderDTO findById(int id);
    OrderDTO save(OrderDTO order);
}
