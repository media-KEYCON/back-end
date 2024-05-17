package com.kicon.controller;

import com.kicon.config.ResponseApiMessage;
import com.kicon.domain.dto.Owner.OwnerLoginRequestDto;
import com.kicon.domain.dto.Owner.OwnerResponseDto;
import com.kicon.domain.dto.Owner.OwnerSaveRequestDto;
import com.kicon.domain.dto.Owner.OwnerUpdateRequestDto;
import com.kicon.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RequiredArgsConstructor
@RestController
public class OwnerController extends BaseController {
    private final static int SUCCESS_CODE = 200;
    private final static int FAILURE_CODE = 400;
    private final OwnerService ownerService;

    @PostMapping("api/v1/owner/login")
    public ResponseEntity<ResponseApiMessage> login(@RequestBody OwnerLoginRequestDto requestDto) {
        OwnerResponseDto responseDto;

        try {
            responseDto = ownerService.findForLogin(requestDto);
        } catch (IllegalArgumentException exception) {
            return sendResponseHttpByJson(FAILURE_CODE, "Failed to Login, There's no such Id=" + requestDto.getOwnerName(), -1);
        }


        if (responseDto == null) {
            return sendResponseHttpByJson(FAILURE_CODE, "Failed to Login, Password is different!", -1);
        }

        return sendResponseHttpByJson(SUCCESS_CODE, "Successfully Logged in.", responseDto);
    }

    @PostMapping("api/v1/owner")
    public ResponseEntity<ResponseApiMessage> save(@RequestBody OwnerSaveRequestDto requestDto) {
        Long savedOwnersId = ownerService.save(requestDto);

        return sendResponseHttpByJson(SUCCESS_CODE, "Owner saved.", savedOwnersId);
    }

    @GetMapping("api/v1/owner/{ownerId}")
    public ResponseEntity<ResponseApiMessage> findById(@PathVariable Long ownerId) {
        OwnerResponseDto responseDto = ownerService.findById(ownerId);

        return sendResponseHttpByJson(SUCCESS_CODE, "Owner loaded. OWNER_ID=" + ownerId, responseDto);
    }

    @GetMapping("api/v1/owner/all")
    public ResponseEntity<ResponseApiMessage> findAllOwner() {
        List<OwnerResponseDto> responseDtoList = ownerService.findAll();

        return sendResponseHttpByJson(SUCCESS_CODE, "All owners loaded.", responseDtoList);
    }

    @PutMapping("api/v1/owner/{ownerId}")
    public ResponseEntity<ResponseApiMessage> update(@PathVariable Long ownerId, @RequestBody OwnerUpdateRequestDto requestDto) {
        OwnerResponseDto responseDto = ownerService.update(ownerId, requestDto);

        return sendResponseHttpByJson(SUCCESS_CODE, "Owner updated. OWNER_ID=" + ownerId, responseDto);
    }

    @DeleteMapping("api/v1/owner/{ownerId}")
    public ResponseEntity<ResponseApiMessage> delete(@PathVariable Long ownerId) {
        Long deletedOwnerId = ownerService.delete(ownerId);

        return sendResponseHttpByJson(SUCCESS_CODE, "Owner deleted. OWNER_ID=" + ownerId, deletedOwnerId);
    }
}
