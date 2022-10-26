package com.airmart.ordercommand.domain;

import com.airmart.ordercommand.common.utils.IdentifierGenerator;
import com.airmart.ordercommand.domain.embed.Address;

import java.util.Objects;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Getter @NoArgsConstructor
public class Shipping extends BaseAuditor{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String shippingId;
    @Column(unique = true)
    private String shippingCode;
    @Embedded
    private Address address;
    private String shippingRequests;

    @Builder
    public Shipping(String shippingCode, Address address, String shippingRequests) {
        Objects.requireNonNull(shippingCode, "배송 코드는 필수 정보입니다.");
        Objects.requireNonNull(address, "주소는 필수정보 입니다.");
        this.shippingId = IdentifierGenerator.randomIdentifier();
        this.shippingCode = shippingCode;
        this.address = address;
        this.shippingRequests = shippingRequests;
    }
}
