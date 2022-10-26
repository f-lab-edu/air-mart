package com.airmart.ordercommand.repository;

import com.airmart.ordercommand.domain.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
  Optional<Order> findByOrderId(String orderId);
}
