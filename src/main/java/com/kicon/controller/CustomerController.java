package com.kicon.controller;

import com.kicon.config.ResponseApiMessage;
import com.kicon.domain.dto.Customer.CustomerSaveRequestDto;
import com.kicon.domain.entity.Customer;
import com.kicon.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController extends BaseController {
    private final static int SUCCESS_CODE = 200;
    private final CustomerService customerService;

    @ResponseBody
    @PostMapping("")
    public ResponseEntity<ResponseApiMessage> savePoint(@RequestBody CustomerSaveRequestDto requestDto) {
        // 조회
        Customer customer = customerService.findByCustomerNumber(requestDto.getCustomerNumber());

        // 이미 존재하는 번호라면, 바로 포인트 적립
        // 그렇지 않다면, 회원 생성 후 포인트 적립
        if(customer == null) {
            customer = customerService.save(requestDto);
        }
        int savedPoint = customerService.savePoint(customer);

        return sendResponseHttpByJson(SUCCESS_CODE, "Point saved. CUSTOMER_NUMBER=" + requestDto.getCustomerNumber() + "POINT=" + savedPoint, requestDto);
    }
}
