package com.keycon.domain.dto.Cart;

import com.keycon.domain.dto.OrderMenu.OrderMenuUpdateRequestDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CartUpdateRequestDto {

    Long cartId;
    List<OrderMenuUpdateRequestDto> orderMenuUpdateRequestDtoList;

}
