package com.kicon.controller;

import com.kicon.config.ResponseApiMessage;
import com.kicon.domain.dto.MenuOptions.MenuOptionsResponseDto;
import com.kicon.domain.dto.MenuOptions.MenuOptionsSaveRequestDto;
import com.kicon.domain.dto.MenuOptions.MenuOptionsUpdateRequestDto;
import com.kicon.service.MenuOptionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RequiredArgsConstructor
@RestController
@Log4j2
public class MenuOptionsController extends BaseController {
    private final static int SUCCESS_CODE = 200;
    private final MenuOptionsService menuOptionsService;

    @PostMapping("api/v1/menuoptions/{menusId}")
    public ResponseEntity<ResponseApiMessage> save(@PathVariable Long menusId, @RequestBody MenuOptionsSaveRequestDto requestDto) {
        Long savedMenuOptionsId = menuOptionsService.save(menusId, requestDto);

        return sendResponseHttpByJson(SUCCESS_CODE, "Option saved", savedMenuOptionsId);
    }

    @PutMapping("api/v1/menuoptions/{menuOptionsId}")
    public ResponseEntity<ResponseApiMessage> update(@PathVariable Long menuOptionsId, @RequestBody MenuOptionsUpdateRequestDto requestDto) {
        MenuOptionsResponseDto responseDto = menuOptionsService.update(menuOptionsId, requestDto);

        return sendResponseHttpByJson(SUCCESS_CODE, "Option updated. OPTION_ID=" + menuOptionsId, responseDto);
    }

    @DeleteMapping("api/v1/menuoptions/{menuOptionsId}")
    public ResponseEntity<ResponseApiMessage> delete(@PathVariable Long menuOptionsId) {
        Long deletedMenuOptionsId = menuOptionsService.delete(menuOptionsId);

        return sendResponseHttpByJson(SUCCESS_CODE, "Option deleted. OPTION_ID=" + menuOptionsId, deletedMenuOptionsId);
    }


}
