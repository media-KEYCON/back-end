package com.keycon.controller;

import com.keycon.config.ResponseApiMessage;
import com.keycon.domain.dto.Cart.CartResponseDto;
import com.keycon.domain.dto.Cart.CartStatusResponseDto;
import com.keycon.domain.dto.Cart.CartSaveRequestDto;
import com.keycon.domain.dto.Cart.CartUpdateRequestDto;
import com.keycon.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartController extends BaseController {

    private final static int SUCCESS_CODE = 200;
    private final CartService cartService;

    // 메뉴 하나 저장하는 API
    @PostMapping("/api/v1/cart")
    public ResponseEntity<ResponseApiMessage> addCart(@RequestBody CartSaveRequestDto requestDto) {
        CartResponseDto responseDto = cartService.save(requestDto);

        return sendResponseHttpByJson(SUCCESS_CODE, "Add in Cart", responseDto);
    }

    // 장바구니 내에 담긴 메뉴 Get API
    @GetMapping("/api/v1/cart/{orderUsersId}")
    public ResponseEntity<ResponseApiMessage> getCart(@PathVariable Long orderUsersId) {
        CartStatusResponseDto responseDto = cartService.getCartResponseDto(orderUsersId);

        return sendResponseHttpByJson(SUCCESS_CODE, "Load Cart", responseDto);
    }

    // 장바구니 닫을 때 장바구니 안에서 수정된 내용 저장하는 API
    @PutMapping("/api/v1/cart")
    public ResponseEntity<ResponseApiMessage> saveCart(@RequestBody CartUpdateRequestDto requestDto) {
        CartResponseDto responseDto = cartService.update(requestDto);

        return sendResponseHttpByJson(SUCCESS_CODE, "Update Cart", responseDto);
    }

}
