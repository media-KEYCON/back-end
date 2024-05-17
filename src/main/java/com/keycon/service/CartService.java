package com.keycon.service;

import com.keycon.domain.dto.Cart.CartResponseDto;
import com.keycon.domain.dto.Cart.CartStatusResponseDto;
import com.keycon.domain.dto.Cart.CartSaveRequestDto;
import com.keycon.domain.dto.Cart.CartUpdateRequestDto;
import com.keycon.domain.dto.OrderMenu.OrderMenuResponseDto;
import com.keycon.domain.dto.OrderMenu.OrderMenuUpdateRequestDto;
import com.keycon.domain.entity.*;
import com.keycon.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    private final OrderUsersService orderUsersService;

    private final MenuOptionsRepository menuOptionsRepository;

    private final OrderMenuService orderMenuService;

    private Cart findCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("해당 장바구니가 없습니다. CART_ID=" + cartId));
    }

    private MenuOptions findMenuOptions(Long menuOptionsId) {
        return menuOptionsRepository.findById(menuOptionsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴 옵션이 없습니다. MENU_OPTIONS_ID=" + menuOptionsId));
    }

    private OrderUsers findOrderUsers(Long orderUsersId) {
        return orderUsersService.findById(orderUsersId);
    }

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    // 메뉴 새로 클릭할 때마다 호출된다.
    @Transactional
    public CartResponseDto save(CartSaveRequestDto requestDto) {
        Cart cart;
        OrderUsers orderUsers;

        if (requestDto.getOrderUsersId() == null) {
            cart = Cart.builder().build();
            saveCart(cart);
            orderUsers = orderUsersService.save(cart);
        } else {
            orderUsers = findOrderUsers(requestDto.getOrderUsersId());
            cart = orderUsers.getCart();
        }

        // 카트에 메뉴 담기
        OrderMenu orderMenu = orderMenuService.getOrderMenu(requestDto.getMenusId(), requestDto.getMenusOptions(), cart);
        cart.addMenus(orderMenu);

        return CartResponseDto.builder()
                .cartId(cart.getCartId())
                .totalPrice(cart.getTotalPrice())
                .totalAmount(cart.getTotalAmount())
                .build();
    }

    @Transactional
    public CartResponseDto update(CartUpdateRequestDto requestDto) {
        for (OrderMenuUpdateRequestDto orderMenuUpdateRequestDto : requestDto.getOrderMenuUpdateRequestDtoList()) {
            orderMenuService.update(orderMenuUpdateRequestDto);
        }

        Cart cart = findCart(requestDto.getCartId());

        int sumOfAmount = 0;
        int sumOfPrice = 0;

        for (OrderMenu orderMenu : cart.getMenus()) {
            sumOfAmount += orderMenu.getAmount();
            sumOfPrice += orderMenu.getPrice() * orderMenu.getAmount();
        }

        cart.update(sumOfAmount, sumOfPrice);

        return CartResponseDto.builder()
                .cartId(cart.getCartId())
                .totalPrice(cart.getTotalPrice())
                .totalAmount(cart.getTotalAmount())
                .build();
    }

    public CartStatusResponseDto getCartResponseDto(Long orderUsersId) {
        OrderUsers users = findOrderUsers(orderUsersId);
        Cart cart = users.getCart();
        List<OrderMenu> menus = cart.getMenus();
        List<OrderMenuResponseDto> menuResponseDtoList = new ArrayList<>();

        // 장바구니에 있는 메뉴 개수 만큼 OrderMenuResponseDto 만들어서 넣기
        for (OrderMenu orderMenu : menus) {
            List<String> orderMenuOptions = getOrderMenuOptions(orderMenu.getOptionsList());
            OrderMenuResponseDto responseDto = OrderMenuResponseDto.builder()
                    .orderMenuOptions(orderMenuOptions)
                    .orderMenu(orderMenu)
                    .build();

            menuResponseDtoList.add(responseDto);
        }

        return CartStatusResponseDto.builder()
                .orderMenuResponseDtoList(menuResponseDtoList)
                .cart(cart)
                .users(users)
                .build();
    }

    public List<String> getOrderMenuOptions(String options) {
        String[] optionsIds = options.split(",");
        List<String> optionsNames = new ArrayList<>();

        if (options.equals("")) {
            return optionsNames;
        }

        for (String option : optionsIds) {
            Long optionsId = Long.parseLong(option);
            optionsNames.add(findMenuOptions(optionsId).getMenuOptionsContents());
        }

        return optionsNames;
    }

}
