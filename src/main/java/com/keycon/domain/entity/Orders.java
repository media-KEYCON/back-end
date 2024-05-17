package com.keycon.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column
    private Long orderNumber;

    @Column
    private boolean takeOut;

    @Column
    private int totalPrice;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Cart cart;

    @OneToOne(mappedBy = "orders")
    private Customer customer;

    @Builder
    public Orders(boolean takeOut, int totalPrice, Cart cart) {
        this.takeOut = takeOut;
        this.totalPrice = totalPrice;
        this.cart = cart;
    }

    @PrePersist
    public void generateOrderNumber() {
        this.orderNumber = OrderNumberGenerator.generateOrderNumber();
    }
}
