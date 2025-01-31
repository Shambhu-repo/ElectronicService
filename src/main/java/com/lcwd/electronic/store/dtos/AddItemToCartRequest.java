package com.lcwd.electronic.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemToCartRequest {
private String prouductId;
private int quantity;


}
