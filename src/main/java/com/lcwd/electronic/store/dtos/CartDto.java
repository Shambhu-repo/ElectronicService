package com.lcwd.electronic.store.dtos;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CartDto {


private String cartId;
private Date createdAt;
private UserDto user;
private List<CartItemDto> items ;


}
