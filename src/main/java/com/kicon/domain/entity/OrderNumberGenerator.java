package com.kicon.domain.entity;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderNumberGenerator {
    private static AtomicInteger currentOrderNumber = new AtomicInteger(1);
    private static LocalDate lastDate = LocalDate.now();

    public static synchronized Long generateOrderNumber() {
        LocalDate currentDate = LocalDate.now();

        if (!currentDate.isEqual(lastDate)) {
            currentOrderNumber.set(1);
            lastDate = currentDate;
        }

        return (long) currentOrderNumber.getAndIncrement();
    }
}