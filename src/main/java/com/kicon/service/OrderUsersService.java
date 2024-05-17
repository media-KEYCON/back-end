package com.kicon.service;

import com.kicon.domain.entity.Cart;
import com.kicon.domain.entity.OrderUsers;
import com.kicon.repository.OrderUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderUsersService {

    private final OrderUsersRepository orderUsersRepository;

    private OrderUsers findOrderUsers(Long orderUsersId) {
        return orderUsersRepository.findById(orderUsersId)
                .orElseThrow(() -> new IllegalArgumentException("해당 고객이 존재하지 않습니다. ORDER_USERS=" + orderUsersId));
    }

    @Transactional
    public OrderUsers save(Cart cart) {
        OrderUsers orderUsers = OrderUsers.builder()
                .cart(cart)
                .build();

        return orderUsersRepository.save(orderUsers);
    }

    public OrderUsers findById(Long orderUsersId) {
        return findOrderUsers(orderUsersId);
    }
}
