package com.kicon.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class OrderMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderMenuId;

    @Column
    private String menusName;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    private String optionsList;

    private int amount = 0;

    private int price;

    @Builder
    public OrderMenu(String menusName, String optionsList, int price, Cart cart) {
        this.menusName = menusName;
        this.optionsList = optionsList;
        this.amount = 1;
        this.price = price;
        this.cart = cart;
    }

    public void update(int amount) {
        this.amount = amount;
    }

    public void disjoin() {
        this.cart.disjoin(this);
        this.cart = null;
    }
}
