package com.etelenchenko.productsservice.query;

import com.etelenchenko.productsservice.event.ProductCreatedEvent;
import com.etelenchenko.productsservice.model.ProductEntity;
import com.etelenchenko.productsservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("product-group")
public class ProductEventHandler {

    private final ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productEntity);

        productRepository.save(productEntity);

        throw new Exception("Forcing exception in ProductEventHandler class");
    }
}
