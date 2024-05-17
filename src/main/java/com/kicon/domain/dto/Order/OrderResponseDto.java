package com.kicon.domain.dto.Order;

import com.kicon.domain.entity.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    Long orderId;
    Long orderNumber;
    boolean takeOut;
    int totalPrice;

    public OrderResponseDto(Orders orders) {
        this.orderId = orders.getOrderId();
        this.orderNumber = orders.getOrderNumber();
        this.takeOut = orders.isTakeOut();
        this.totalPrice = orders.getTotalPrice();
    }
}
