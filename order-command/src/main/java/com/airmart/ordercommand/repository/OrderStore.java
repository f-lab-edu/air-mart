package com.airmart.ordercommand.repository;

import com.airmart.ordercommand.domain.GroupOrder;
import com.airmart.ordercommand.domain.Order;
import com.airmart.ordercommand.domain.Shipping;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStore {
  private final OrderRepository orderRepository;
  private final GroupOrderRepository groupOrderRepository;

  private final ShippingRepository shippingRepository;

  public GroupOrder createGroupOrder(GroupOrder groupOrder) {
    return groupOrderRepository.save(groupOrder);
  }

  public GroupOrder findGroupOrderBy(String groupOrderId) {
    return groupOrderRepository.findByGroupOrderId(groupOrderId)
        .orElseThrow(RuntimeException::new);
  }

  public Shipping createShipping(Shipping shipping) {
    return shippingRepository.save(shipping);
  }

  public Optional<Order> findByOrderId(String orderId) {
    return orderRepository.findByOrderId(orderId);
  }
}
