package com.kicon.repository;

import com.kicon.domain.entity.OrderUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderUsersRepository extends JpaRepository<OrderUsers, Long> {
}
