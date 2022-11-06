package com.etelenchenko.productsservice.command;

import com.etelenchenko.productsservice.event.ProductCreatedEvent;
import com.etelenchenko.productsservice.model.ProductLookupEntity;
import com.etelenchenko.productsservice.repository.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductLookupEventHandler {

    private final ProductLookupRepository productLookupRepository;

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductLookupEntity productLookupEntity = new ProductLookupEntity(
                productCreatedEvent.getProductId(),
                productCreatedEvent.getTitle());

        productLookupRepository.save(productLookupEntity);
    }
}
