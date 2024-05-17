package com.keycon.repository;

import com.keycon.domain.entity.OrderUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderUsersRepository extends JpaRepository<OrderUsers, Long> {
}
