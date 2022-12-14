package com.airmart.ordercommand.service;

import com.airmart.ordercommand.domain.GroupOrder;
import com.airmart.ordercommand.domain.Order;
import com.airmart.ordercommand.dto.request.OrderRequestDto.GroupOrderCreateRequest;
import com.airmart.ordercommand.dto.request.OrderRequestDto.OrderCreateRequest;
import com.airmart.ordercommand.dto.response.OrderResponseDto.GroupOrderCreateResponse;
import com.airmart.ordercommand.dto.response.OrderResponseDto.OrderCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderFacade {
  private final OrderService orderService;
  public OrderCreateResponse createOrder(String groupOrderId, OrderCreateRequest request) {
    // Item 조회, 재고 확인 ( price 조회 필요 )
    Order newOrder = request.toEntity(0L);
    GroupOrder groupOrder = orderService.addOrder(groupOrderId, newOrder);
    // 결제 (paymentService) -> 실패 대응 ( Fallback 패턴 으로 주문 실패로 변경 필요 )
    return null;
  }

  public GroupOrderCreateResponse createGroupOrder(GroupOrderCreateRequest request) {
    // Item 조회, 재고 확인 ( price 와 minimumQuantity 조회 필요 )
    // GroupOrder 와 Order 생성
    GroupOrder groupOrder = orderService.createGroupOrder(request.toEntity(0L, 5));
    // Redis 에 정보 쌓기 ? ( 클라우드 공통으로 쓴다고 가정? )
    groupOrder.checkGroupOrderSuccess();
    // 결제 (paymentService) -> 실패 대응 ( Fallback 패턴 으로 주문 실패로 변경 필요 )
   return null;
  }
}
