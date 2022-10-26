package com.airmart.ordercommand.controller;

import static com.airmart.ordercommand.dto.request.OrderRequestDto.*;

import com.airmart.ordercommand.common.dto.CommonResponse;
import com.airmart.ordercommand.dto.response.OrderResponseDto.GroupOrderCreateResponse;
import com.airmart.ordercommand.dto.response.OrderResponseDto.OrderCreateResponse;
import com.airmart.ordercommand.service.OrderFacade;
import javax.servlet.annotation.HttpConstraint;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    private final OrderFacade orderFacade;
    @PostMapping("/hello")
    public String helloPost() {
        return "Hello";
    }

    /**
     * 새로운 Group Order 생성을 위한 API
     * @param request:OrderCreateRequest
     * @return CommonResponse
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<GroupOrderCreateResponse> createGroupOrder(
        @RequestBody GroupOrderCreateRequest request
    ) {
        return CommonResponse.success(
            orderFacade.createGroupOrder(request)
        );
    }

    /**
     * 이미 존재하는 Group Order 에 참여하기 위한 API
     * @param groupOrderId
     * @param request
     * @return CommonResponse<OrderCreateResponse>
     */
    @PostMapping("/{groupOrderId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<OrderCreateResponse> createOrder(
        @PathVariable String groupOrderId,
        @RequestBody OrderCreateRequest request
    ) {
        return CommonResponse.success(
            orderFacade.createOrder(groupOrderId, request)
        );
    }

    /**
     * Order에 - Shipping 등록
     * @param orderId
     * @param request
     * @return
     */
    @PostMapping("/{orderId}/shipping-register")
    public CommonResponse registerShipping(
        @PathVariable String orderId,
        @RequestBody ShippingRegisterRequest request
        ) {
        return CommonResponse.success(
            orderFacade.registerShipping(orderId, request)
        );
    }
}
