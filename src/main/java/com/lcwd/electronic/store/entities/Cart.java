package com.lcwd.electronic.store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    private String cartId;

private Date createdAt;

@OneToOne
private User user;

//maping cart-item

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItem> items = new ArrayList<>();



}

/* we have assumption for the cart
* 1.  user is reqired to perform cart operation
* 2.User has only one cart
* */