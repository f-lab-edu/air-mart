package com.airmart.ordercommand.domain;

import com.airmart.ordercommand.common.utils.IdentifierGenerator;
import com.airmart.ordercommand.domain.embed.OrderedItem;
import com.airmart.ordercommand.domain.embed.RecipientInfo;
import com.airmart.ordercommand.domain.embed.SenderInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor @Getter
@Table(name = "orders")
@Entity
public class Order extends BaseAuditor{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Data Format => YYYYMMDDHHMMSS-UUID
     */
    private String orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Embedded
    private OrderedItem orderedItem;

    @Embedded
    private RecipientInfo recipientInfo;

    @Embedded
    private SenderInfo senderInfo;

    private String shippingCode;
    /**
     * User 정보필요.
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_order_Id")
    private GroupOrder groupOrder;

    public Order(OrderType orderType, OrderedItem orderedItem, RecipientInfo recipientInfo,
        SenderInfo senderInfo) {
        this.orderId = IdentifierGenerator.randomIdentifier();
        this.orderType = orderType;
        this.orderedItem = orderedItem;
        this.recipientInfo = recipientInfo;
        this.senderInfo = senderInfo;
        this.status = OrderStatus.GROUP_BUYING_INIT;
    }

    public void joinGroupOrder(GroupOrder groupOrder) {
        if (groupOrder == null) throw new RuntimeException();
        this.groupOrder = groupOrder;
        groupOrder.addOrder(this);
    }

    public Long getQuantity() {
        return orderedItem
            .getQuantity().orElseGet(() -> 0L);
    }

    public void changeToReady() {
        if (this.status != OrderStatus.GROUP_BUYING_INIT) throw new RuntimeException();
        this.status = OrderStatus.SHIPPING_READY;
    }

    public void addShippingCode(String shippingCode) {
        if (this.status != OrderStatus.SHIPPING_READY) throw new IllegalArgumentException("잘못된 요청입니다.");
        if (this.shippingCode != null) throw new IllegalArgumentException("이미 입력된 배송지 입니다.");
        this.shippingCode = shippingCode;
        this.status = OrderStatus.SHIPPING;
    }
}
