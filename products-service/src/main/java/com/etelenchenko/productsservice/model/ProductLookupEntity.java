package com.etelenchenko.productsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_lookup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLookupEntity {

    @Id
    private String productId;

    @Column(unique = true)
    private String title;
}
