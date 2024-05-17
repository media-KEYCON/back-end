package com.kicon.domain.dto.Cart;

import com.kicon.domain.dto.OrderMenu.OrderMenuUpdateRequestDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CartUpdateRequestDto {

    Long cartId;
    List<OrderMenuUpdateRequestDto> orderMenuUpdateRequestDtoList;

}
