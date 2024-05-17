package com.kicon.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column
    private String customerNumber;

    @OneToOne
    @JoinColumn(name = "orderId")
    private Orders orders;

    @ColumnDefault("0")
    private int point;

    @Builder
    public Customer(String customerNumber, Orders orders) {
        this.customerNumber = customerNumber;
        this.orders = orders;
        this.point = 0;
    }

    public int savePoint(int point) {
        this.point = point;

        return this.point;
    }
}
