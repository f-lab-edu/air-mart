package com.airmart.ordercommand.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

public class OrderResponseDto {

  @Data
  public static class OrderCreateResponse {

  }

  @Data
  public static class GroupOrderCreateResponse {

  }

  @Data @AllArgsConstructor
  public static class RegisterShippingResponse {
    private String shippingCode;
  }


}
