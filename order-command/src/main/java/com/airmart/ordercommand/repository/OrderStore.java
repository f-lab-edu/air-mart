package com.airmart.ordercommand.repository;

import com.airmart.ordercommand.domain.GroupOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStore {
  private final OrderRepository orderRepository;
  private final GroupOrderRepository groupOrderRepository;


  public GroupOrder createGroupOrder(GroupOrder groupOrder) {
    return groupOrderRepository.save(groupOrder);
  }

  public GroupOrder findGroupOrderBy(String groupOrderId) {
    return groupOrderRepository.findByGroupOrderId(groupOrderId)
        .orElseThrow(RuntimeException::new);
  }
}
