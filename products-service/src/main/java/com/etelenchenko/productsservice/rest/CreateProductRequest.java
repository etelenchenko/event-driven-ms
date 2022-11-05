package com.etelenchenko.productsservice.rest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class CreateProductRequest {

    @NotEmpty
    private String title;
    @Positive
    private BigDecimal price;
    @Positive
    private Integer quantity;
}
