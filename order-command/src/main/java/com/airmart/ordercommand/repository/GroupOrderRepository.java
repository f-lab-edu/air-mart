package com.airmart.ordercommand.repository;

import com.airmart.ordercommand.domain.GroupOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupOrderRepository extends JpaRepository<GroupOrder, Long> {

}
