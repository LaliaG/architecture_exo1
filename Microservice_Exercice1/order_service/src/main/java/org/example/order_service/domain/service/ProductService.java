package org.example.order_service.domain.service;

import org.example.order_service.shared.dto.ProductDTO;
import org.example.order_service.shared.port.ProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductPort productPort;

    @Autowired
    public ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    public ProductDTO getProductById(int id) {
        return productPort.findById(id);
    }
}
