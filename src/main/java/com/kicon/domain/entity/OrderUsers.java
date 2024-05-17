package com.kicon.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class OrderUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderUsersId;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Cart cart;

    @Builder
    public OrderUsers(Cart cart) {
        this.cart = cart;
    }
}
