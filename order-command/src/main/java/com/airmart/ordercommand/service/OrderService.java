package com.airmart.ordercommand.service;

import com.airmart.ordercommand.domain.GroupOrder;
import com.airmart.ordercommand.domain.Order;
import com.airmart.ordercommand.repository.OrderStore;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
  private final OrderStore orderStore;

  public GroupOrder createGroupOrder(GroupOrder newOrder) {
    GroupOrder groupOrder = orderStore.createGroupOrder(newOrder);
    groupOrder.checkGroupOrderSuccess();
    // 공구 성공여부 판단 -> 성공시 부가 작업 실행
    return groupOrder;
  }

  public GroupOrder addOrder(String groupOrderId, Order newOrder) {
    // Group 참여 가능여부 체크
    GroupOrder groupOrder = orderStore.findGroupOrderBy(groupOrderId);
    groupOrder.validOrderCreation();
    newOrder.joinGroupOrder(groupOrder);
    // Redis 에 정보 쌓기 ? ( 클라우드 공통으로 쓴다고 가정? )
    groupOrder.checkGroupOrderSuccess();
    return groupOrder;
  }

  public void updateShippingCode(String orderId, String shippingCode) {
    //Todo: CustomException 으로 변경 필요
    Order findOrder = orderStore.findByOrderId(orderId)
        .orElseThrow(() -> new RuntimeException("해당 주문을 찾을 수 없습니다."));
    findOrder.addShippingCode(shippingCode);
  }
}
