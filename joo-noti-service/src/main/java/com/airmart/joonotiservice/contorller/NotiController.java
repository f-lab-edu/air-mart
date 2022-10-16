package com.airmart.joonotiservice.contorller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/joo/noti")
public class NotiController {
    @GetMapping
    public String notiHello(){
        return "hello noti";
    }
}
