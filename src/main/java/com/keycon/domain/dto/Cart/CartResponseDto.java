package com.keycon.domain.dto.Cart;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartResponseDto {
    Long cartId;
    int totalPrice;
    int totalAmount;

    @Builder
    public CartResponseDto(Long cartId, int totalPrice, int totalAmount) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.totalAmount = totalAmount;
    }
}
