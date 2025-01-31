package com.lcwd.electronic.store.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {


    private String productId;


    private String title;

    private String description;


    private int price;

    private int discountedPrice;

    private int quantity;

    private Date addedDate;

    private boolean live;


    private boolean stock;
}
