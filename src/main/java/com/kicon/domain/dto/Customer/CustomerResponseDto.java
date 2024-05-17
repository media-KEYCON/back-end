package com.kicon.domain.dto.Customer;

import com.kicon.domain.entity.Customer;

public class CustomerResponseDto {
    String customerNumber;
    int point;

    public CustomerResponseDto(Customer customer) {
        this.customerNumber = customer.getCustomerNumber();
        this.point = customer.getPoint();
    }
}
