package org.example.order_service.shared.port;

import org.example.order_service.shared.dto.ProductDTO;

public interface ProductPort {

    ProductDTO findById(int id);
    ProductDTO save(ProductDTO product);
}
