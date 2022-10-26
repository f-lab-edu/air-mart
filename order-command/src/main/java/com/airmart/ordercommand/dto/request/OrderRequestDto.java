package com.airmart.ordercommand.dto.request;

import com.airmart.ordercommand.domain.GroupOrder;
import com.airmart.ordercommand.domain.Order;
import com.airmart.ordercommand.domain.OrderType;
import com.airmart.ordercommand.domain.embed.Address;
import com.airmart.ordercommand.domain.embed.OrderedItem;
import com.airmart.ordercommand.domain.embed.RecipientInfo;
import com.airmart.ordercommand.domain.embed.SenderInfo;
import java.time.LocalDateTime;
import lombok.Data;

public class OrderRequestDto {
  @Data
  public static class OrderCreateRequest {
    private Long itemId;

    private Long quantity;

    private SendInfoRequest sendInfo;

    private RecipientInfoRequest recipientInfo;

    public Order toEntity(long price) {
      return new Order(
          OrderType.GROUP_ORDER
          , OrderedItem.newInstance(this.quantity, price)
          , recipientInfo.toVO()
          , sendInfo.toVo()
      );
    }
  }

  @Data
  public static class GroupOrderCreateRequest {
    private Long itemId;
    // 구매 수량
    private Long quantity;

    private long hours;

    private SendInfoRequest sendInfo;

    private RecipientInfoRequest recipientInfo;

    /**
     * price 및 minimumQuantity 는 Item을 통해 발생한다.
     *
     * @return
     * @param price
     * @param minimumQuantity
     */
    public GroupOrder toEntity(long price, int minimumQuantity) {
      LocalDateTime startDate = LocalDateTime.now();
      LocalDateTime endDate = startDate.plusHours(hours);
      Order newOrder = new Order(
          OrderType.GROUP_ORDER
          , OrderedItem.newInstance(this.quantity, price)
          , recipientInfo.toVO()
          , sendInfo.toVo()
      );


      GroupOrder groupOrder = GroupOrder.builder()
          .minimumQuantity(minimumQuantity)
          .startDate(startDate)
          .endDate(endDate)
          .order(newOrder)
          .build();


      return groupOrder;
    }
  }

  @Data
  public static class SendInfoRequest {
    private String senderName;
    private String senderPhoneNumber;

    public SenderInfo toVo() {
      return SenderInfo.newInstance(senderName, senderPhoneNumber);
    }
  }
  @Data
  public static class RecipientInfoRequest {
    private String recipientName;
    private String firstAddress;
    private String secondAddress;
    private String zipCode;
    private String recipientPhoneNumber;

    public RecipientInfo toVO() {
      return RecipientInfo.newInstance(recipientName, recipientPhoneNumber
          , firstAddress, secondAddress, zipCode);
    }
  }


  /**
   * 송장 번호 입력을 위한 API 요청에 사용될 DTO
   */
  @Data
  public static class ShippingRegisterRequest {
    private String shippingCode;
    private AddressRequest address;
    private String shippingRequests;
  }

  @Data
  public static class AddressRequest {
    private String firstAddress;
    private String secondAddress;
    private String zipCode;

  }

}
