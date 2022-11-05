package com.etelenchenko.productsservice.query;

import com.etelenchenko.productsservice.repository.ProductRepository;
import com.etelenchenko.productsservice.rest.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    @QueryHandler
    public List<ProductResponse> findProducts(FindProductsQuery findProductsQuery) {
        return productRepository.findAll().stream()
                .map(entity -> {
                    ProductResponse productResponse = new ProductResponse();
                    BeanUtils.copyProperties(entity, productResponse);
                    return productResponse;
                })
                .collect(Collectors.toList());
    }
}
