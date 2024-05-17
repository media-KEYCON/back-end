package com.keycon.service;

import com.keycon.domain.dto.Order.OrderResponseDto;
import com.keycon.domain.dto.Order.OrderSaveRequestDto;
import com.keycon.domain.entity.Cart;
import com.keycon.domain.entity.Orders;
import com.keycon.repository.CartRepository;
import com.keycon.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public Cart findCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장바구니입니다. CART_ID=" + cartId));
    }

    public Orders findOrders(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다. ORDER_ID=" + orderId));
    }

    @Transactional
    public OrderResponseDto save(OrderSaveRequestDto requestDto) {
        Cart cart = findCart(requestDto.getCartId());
        Orders orders = orderRepository.save(new Orders(requestDto.isTakeOut(), cart.getTotalPrice(), cart));

        return new OrderResponseDto(orders);
    }

    @Transactional
    public OrderResponseDto findById(Long orderId) {
        Orders orders = findOrders(orderId);

        return new OrderResponseDto(orders);
    }

    @Transactional
    public Long delete(Long orderId) {
        Orders orders = findOrders(orderId);
        orderRepository.delete(orders);

        return orderId;
    }
}
