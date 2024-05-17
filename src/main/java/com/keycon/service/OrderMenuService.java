package com.keycon.service;

import com.keycon.domain.dto.OrderMenu.OrderMenuUpdateRequestDto;
import com.keycon.domain.entity.*;
import com.keycon.repository.MenuOptionsRepository;
import com.keycon.repository.MenusRepository;
import com.keycon.repository.OrderMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderMenuService {
    private final MenusRepository menusRepository;
    private final MenuOptionsRepository menuOptionsRepository;
    private final OrderMenuRepository orderMenuRepository;


    private Menus findMenus(Long menusId) {
        return menusRepository.findById(menusId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다. MENUS_ID=" + menusId));
    }

    private MenuOptions findMenuOptions(Long menuOptionsId) {
        return menuOptionsRepository.findById(menuOptionsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴 옵션이 없습니다. MENU_OPTIONS_ID=" + menuOptionsId));
    }

    private OrderMenu findOrderMenu(Long orderMenuId) {
        return orderMenuRepository.findById(orderMenuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문 메뉴가 없습니다. ORDER_MENU_ID=" + orderMenuId));
    }

    @Transactional
    public OrderMenu getOrderMenu(Long menusId, String menuOptions, Cart cart) {
        Menus menus = findMenus(menusId);

        OrderMenu orderMenu = OrderMenu.builder()
                .cart(cart)
                .optionsList(menuOptions)
                .menusName(menus.getMenusName())
                .price(getPrice(menus, menuOptions))
                .build();

        return orderMenuRepository.save(orderMenu);
    }

    int getPrice(Menus menus, String optionsList) {
        int price = menus.getMenusPrice();

        if (optionsList.equals("")) {
            return price;
        }

        String[] options = optionsList.split(",");

        for (String option : options) {
            Long optionsId = Long.parseLong(option);
            price += findMenuOptions(optionsId).getMenuOptionsPrice();
        }

        return price;
    }

    @Transactional
    public void update(OrderMenuUpdateRequestDto requestDto) {
        OrderMenu orderMenu = findOrderMenu(requestDto.getOrderMenuId());

        // 장바구니 내에서 삭제된 경우 -> 디비에서 삭제
        if (requestDto.isDeleted()) {
            delete(requestDto.getOrderMenuId());
        }

        // 아닌 경우 수량 업데이트
        orderMenu.update(requestDto.getOrderMenuAmount());
    }

    @Transactional
    public Long delete(Long orderMenuId) {
        OrderMenu orderMenu = findOrderMenu(orderMenuId);

        orderMenuRepository.delete(orderMenu);
        orderMenu.disjoin();

        return orderMenuId;
    }
}
