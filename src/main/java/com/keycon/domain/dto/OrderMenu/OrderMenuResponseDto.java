package com.keycon.domain.dto.OrderMenu;

import com.keycon.domain.entity.OrderMenu;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderMenuResponseDto {
    Long orderMenuId;
    String orderMenuName;
    int orderMenuPrice;
    int orderMenuAmount;
    List<String> orderMenuOptions;

    @Builder
    public OrderMenuResponseDto(OrderMenu orderMenu, List<String> orderMenuOptions) {
        this.orderMenuId = orderMenu.getOrderMenuId();
        this.orderMenuName = orderMenu.getMenusName();
        this.orderMenuAmount = orderMenu.getAmount();
        this.orderMenuPrice = orderMenu.getPrice();
        this.orderMenuOptions = orderMenuOptions;
    }
}
