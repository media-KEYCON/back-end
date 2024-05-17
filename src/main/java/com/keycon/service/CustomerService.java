package com.keycon.service;

import com.keycon.domain.dto.Customer.CustomerSaveRequestDto;
import com.keycon.domain.entity.Customer;
import com.keycon.domain.entity.Orders;
import com.keycon.repository.CustomerRepository;
import com.keycon.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public Customer findCustomer(String customerNumber) {
        return customerRepository.findByCustomerNumber(customerNumber)
                .orElse(null);
    }

    public Orders findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다. ORDER_ID=" + orderId));
    }

    // 회원 조회
    @Transactional
    public Customer findByCustomerNumber(String customerNumber) {
        return findCustomer(customerNumber);
    }

    // 회원 생성
    @Transactional
    public Customer save(CustomerSaveRequestDto requestDto) {
        Orders orders = findOrder(requestDto.getOrderId());
        Customer customer = new Customer(requestDto.getCustomerNumber(), orders);

        customerRepository.save(customer);

        return customer;
    }

    // 포인트 적립
    @Transactional
    public int savePoint(Customer customer) {
        int savedPoint = customer.getPoint() + customer.getOrders().getTotalPrice() / 100;

        return customer.savePoint(savedPoint);
    }
}
