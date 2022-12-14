package com.airmart.itemservice.itemcommand.controller;

import com.airmart.itemservice.common.dto.CommonResponse;
import com.airmart.itemservice.itemcommand.domain.Category;
import com.airmart.itemservice.itemcommand.dto.ItemCommandDTO;
import com.airmart.itemservice.itemcommand.service.ItemCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemCommandController {

    private final ItemCommandService itemCommandService;

    @GetMapping("/id/{id}")
    public ResponseEntity<CommonResponse<ItemCommandDTO.Response>> getItemById(@PathVariable String id){
        ItemCommandDTO.Response response = itemCommandService.getItemById(Long.parseLong(id));
        return ResponseEntity.ok().body(CommonResponse.success(response));
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<CommonResponse<List<ItemCommandDTO.Response>>> getItemsByCategory(
            @PathVariable String category, Pageable pageable){
        List<ItemCommandDTO.Response> itemList = itemCommandService
                .getItemList(Category.valueOf(category.toUpperCase()), pageable);
        return ResponseEntity.ok().body(CommonResponse.success(itemList));
    }

    @PostMapping
    public ResponseEntity<CommonResponse<String>> createItem(@RequestBody ItemCommandDTO.Request itemDTO){
        itemCommandService.createItem(itemDTO);
        return ResponseEntity.ok().body(CommonResponse.success("item created"));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<CommonResponse<String>> editItem(@RequestBody ItemCommandDTO.Request itemDTO,
                                           @PathVariable String id){
        itemCommandService.editItem(Long.parseLong(id), itemDTO);
        return ResponseEntity.ok().body(CommonResponse.success("item updated"));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<CommonResponse<String>> deleteItem(@PathVariable String id){
        itemCommandService.deleteItem(Long.parseLong(id));
        return ResponseEntity.ok().body(CommonResponse.success("item deleted"));
    }

    @GetMapping("/batch/closing")
    public ResponseEntity<CommonResponse<List<ItemCommandDTO.Response>>> getClosingItems(){
        List<ItemCommandDTO.Response> itemList = itemCommandService
                .getClosingItemList();
        return ResponseEntity.ok().body(CommonResponse.success(itemList));
    }
}
