package org.example.order_service.infrastructure.springdata.impl;

import org.example.order_service.infrastructure.springdata.entity.ProductEntity;
import org.example.order_service.infrastructure.springdata.repository.ProductRepository;
import org.example.order_service.shared.dto.ProductDTO;
import org.example.order_service.shared.port.ProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPortImpl implements ProductPort {

    private final ProductRepository productRepository;

    @Autowired
    public ProductPortImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO findById(int id) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        return new ProductDTO(productEntity.getId(), productEntity.getName(), productEntity.getPrice());
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productRepository.save(productEntity);
        return productDTO;
    }
}
