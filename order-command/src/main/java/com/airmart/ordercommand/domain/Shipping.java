package com.airmart.ordercommand.domain;

import com.airmart.ordercommand.domain.embed.Address;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor @AllArgsConstructor
public class Shipping extends BaseAuditor{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "송장 번호")
    private String shippingCode;
    @Embedded
    private Address address;
    @Column(columnDefinition = "배송 시 요청사항")
    private String shippingRequests;
}
