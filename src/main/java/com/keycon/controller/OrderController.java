package com.keycon.controller;

import com.keycon.config.ResponseApiMessage;
import com.keycon.domain.dto.Order.OrderResponseDto;
import com.keycon.domain.dto.Order.OrderSaveRequestDto;
import com.keycon.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@RestController
public class OrderController extends BaseController {
    private final static int SUCCESS_CODE = 200;
    private final OrderService orderService;

    @ResponseBody
    @PostMapping("")
    public ResponseEntity<ResponseApiMessage> save(@RequestBody OrderSaveRequestDto requestDto) {
        OrderResponseDto responseDto = orderService.save(requestDto);

        return sendResponseHttpByJson(SUCCESS_CODE, "Saved Order.", responseDto);
    }

    @ResponseBody
    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseApiMessage> findById(@PathVariable Long orderId) {
        OrderResponseDto responseDto = orderService.findById(orderId);

        return sendResponseHttpByJson(SUCCESS_CODE, "Order loaded. ORDER_ID=" + orderId, responseDto);
    }

//    @ResponseBody
//    @DeleteMapping("/{orderId}")
//    public ResponseEntity<ResponseApiMessage> delete(@PathVariable Long orderId) {
//        Long deletedOrderId = orderService.delete(orderId);
//
//        return sendResponseHttpByJson(SUCCESS_CODE, "ORDER DELETED. ORDER_ID=" + orderId, deletedOrderId);
//    }
}
