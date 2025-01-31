package com.lcwd.electronic.store.services.implementation;

import com.lcwd.electronic.store.dtos.AddItemToCartRequest;
import com.lcwd.electronic.store.dtos.CartDto;
import com.lcwd.electronic.store.entities.Cart;
import com.lcwd.electronic.store.entities.CartItem;
import com.lcwd.electronic.store.entities.Product;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.exception.ResourceNotFounException;
import com.lcwd.electronic.store.repositories.CartRepository;
import com.lcwd.electronic.store.repositories.ProductRepository;
import com.lcwd.electronic.store.repositories.UserRepository;
import com.lcwd.electronic.store.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.NoSuchFileException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class CartServiceImplementation implements CartService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
      int quantity =  request.getQuantity();
       String productId = request.getProuductId();
      Product product =  productRepository.findById(productId)
              .orElseThrow(()-> new ResourceNotFounException("product not found in database"));
      //fetch the user from DB
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFounException("user not found in database"));

        Cart cart = null;

        try{
          cart =  cartRepository.findByUser(user).get();

        }
        catch(NoSuchElementException e){
            cart = new Cart();
            cart.setCreatedAt(new Date());
        }

        //perform cart operation
      //  List<CartItem> items = cart.getItems();

        //crate items
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(product.getPrice() * quantity);
        cartItem.setCart(cart);


        cart.getItems().add(cartItem);
        cart.setUser(user);

       Cart updatedCart = cartRepository.save(cart);

return modelMapper.map(updatedCart, CartDto.class);

    }


    @Override
    public void removeItemFromCart(String userID, int cartItem) {

    }

    @Override
    public void clearCart(String userID) {

    }



}
