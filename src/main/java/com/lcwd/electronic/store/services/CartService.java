package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.dtos.CartDto;

public interface CartService {

    //add items to cart:
    //case1: if cart for user is not available, we will create the cart for the user
    //case2: cart available add the items to cart

    CartDto addItemToCart(String userId , AddItemToCartRequest request);

    //remove item from cart
    void removeItemFromCart(String userID, int cartItem);

    //remove all items from cart
    void clearCart(String userID);




}
