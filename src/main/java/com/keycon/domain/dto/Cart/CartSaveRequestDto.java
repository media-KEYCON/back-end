package com.keycon.domain.dto.Cart;

import lombok.Getter;

@Getter
public class CartSaveRequestDto {
    Long menusId;
    Long orderUsersId;
    String menusOptions;
}
