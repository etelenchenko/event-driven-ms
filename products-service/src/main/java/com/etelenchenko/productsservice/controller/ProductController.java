package com.etelenchenko.productsservice.controller;

import com.etelenchenko.productsservice.command.CreateProductCommand;
import com.etelenchenko.productsservice.rest.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CommandGateway commandGateway;

    @GetMapping
    public String index() {
        return "Products service is running";
    }

    @PostMapping
    public String create(@RequestBody @Valid CreateProductRequest createProductRequest) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRequest.getPrice())
                .title(createProductRequest.getTitle())
                .quantity(createProductRequest.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();

        return commandGateway.sendAndWait(createProductCommand);
    }
}
