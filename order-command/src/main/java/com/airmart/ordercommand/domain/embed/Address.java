package com.airmart.ordercommand.domain.embed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable @NoArgsConstructor
public class Address {
    private String firstAddress;
    private String secondAddress;
    private String zipCode;

    private Address(String firstAddress, String secondAddress, String zipCode) {
        this.firstAddress = firstAddress;
        this.secondAddress = secondAddress;
        this.zipCode = zipCode;
    }

    public static Address newInstance(String firstAddress, String secondAddress, String zipCode) {
        return new Address(firstAddress, secondAddress, zipCode);
    }
}
