package com.airmart.ordercommand.repository;

import com.airmart.ordercommand.domain.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {}
