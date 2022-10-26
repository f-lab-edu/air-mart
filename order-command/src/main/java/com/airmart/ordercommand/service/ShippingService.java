package com.airmart.ordercommand.service;

import com.airmart.ordercommand.domain.Shipping;
import com.airmart.ordercommand.repository.OrderStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingService {

  private final OrderStore orderStore;

  public String createShipping(Shipping shipping) {
    Shipping createdShipping = orderStore.createShipping(shipping);
    return createdShipping.getShippingCode();
  }
}
