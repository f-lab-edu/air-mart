package com.airmart.itemservice.itemcommand.service.util;

import com.airmart.itemservice.itemcommand.domain.Category;
import com.airmart.itemservice.itemcommand.domain.entity.Item;
import com.airmart.itemservice.itemcommand.dto.ItemCommandDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ItemCommandMapper {
    public ItemCommandDTO.Response toItemDtoResponse(Item item){
        return ItemCommandDTO.Response.builder()
                .itemId(item.getId())
                .itemName(item.getName())
                .itemPrice(item.getPrice())
                .category(item.getCategory().toString())
                .userName(item.getUserName())
                .closingDateTime(item.getClosedAt().toString())
                .build();
    }

    public Item toItemEntity(ItemCommandDTO.Request itemDto){
        return Item.builder()
                .name(itemDto.getItemName())
                .price(itemDto.getItemPrice())
                .userName(itemDto.getUserName())
                .category(Category.valueOf(itemDto.getCategory().toUpperCase()))
                .closedAt(LocalDateTime.now()
                        .plusDays(itemDto.getDays() != null ? itemDto.getDays() : 7L))
                .build();

    }

}
