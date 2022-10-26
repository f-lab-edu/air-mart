package com.airmart.ordercommand.domain;

import com.airmart.ordercommand.domain.embed.Address;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class Shipping extends BaseAuditor{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String shippingCode;
    @Embedded
    private Address address;
    private String shippingRequests;

    @Builder
    public Shipping(String shippingCode, Address address, String shippingRequests) {
        this.shippingCode = shippingCode;
        this.address = address;
        this.shippingRequests = shippingRequests;
    }
}
