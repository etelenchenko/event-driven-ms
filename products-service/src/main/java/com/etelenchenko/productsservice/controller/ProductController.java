package com.etelenchenko.productsservice.controller;

import com.etelenchenko.productsservice.command.CreateProductCommand;
import com.etelenchenko.productsservice.query.FindProductsQuery;
import com.etelenchenko.productsservice.rest.CreateProductRequest;
import com.etelenchenko.productsservice.rest.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @GetMapping
    public CompletableFuture<List<ProductResponse>> getProducts() {
        FindProductsQuery findProductsQuery = new FindProductsQuery();
        return queryGateway.query(findProductsQuery, ResponseTypes.multipleInstancesOf(ProductResponse.class));
    }

    @PostMapping
    public CompletableFuture<String> create(@RequestBody @Valid CreateProductRequest createProductRequest) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRequest.getPrice())
                .title(createProductRequest.getTitle())
                .quantity(createProductRequest.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();

        return commandGateway.send(createProductCommand);
    }
}
