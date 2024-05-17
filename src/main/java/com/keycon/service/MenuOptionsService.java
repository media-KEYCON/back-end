package com.keycon.service;

import com.keycon.domain.dto.MenuOptions.MenuOptionsResponseDto;
import com.keycon.domain.dto.MenuOptions.MenuOptionsSaveRequestDto;
import com.keycon.domain.dto.MenuOptions.MenuOptionsUpdateRequestDto;
import com.keycon.domain.entity.MenuOptions;
import com.keycon.domain.entity.Menus;
import com.keycon.repository.MenuOptionsRepository;
import com.keycon.repository.MenusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MenuOptionsService {
    private final MenuOptionsRepository menuOptionsRepository;
    private final MenusRepository menusRepository;

    public MenuOptions findMenuOptions(Long menuOptionsId) {
        return menuOptionsRepository.findById(menuOptionsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 옵션이 메뉴에 존재하지 않습니다. MENU_OPTIONS_ID=" + menuOptionsId));

    }

    public Menus findMenus(Long menusId) {
        return menusRepository.findById(menusId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다. MENU_ID=" + menusId));
    }

    @Transactional
    public Long save(Long menusId, MenuOptionsSaveRequestDto requestDto) {
        Menus menus = findMenus(menusId);
        MenuOptions menuOptions = requestDto.toEntity();
        menuOptions.addMenus(menus);
        menus.addMenuOptions(menuOptions);

        return menuOptionsRepository.save(menuOptions).getMenuOptionsId();
    }

    public MenuOptionsResponseDto findById(Long menuOptionsId) {
        MenuOptions menuOptions = findMenuOptions(menuOptionsId);

        return new MenuOptionsResponseDto(menuOptions);
    }

    @Transactional
    public MenuOptionsResponseDto update(Long menuOptionsId, MenuOptionsUpdateRequestDto requestDto) {
        MenuOptions menuOptions = findMenuOptions(menuOptionsId);

        menuOptions.update(requestDto.getMenuOptionsCategory(), requestDto.getMenuOptionsContents(), requestDto.getMenuOptionsPrice(), requestDto.isMandatory());

        return findById(menuOptionsId);
    }

    @Transactional
    public Long delete(Long menuOptionsId) {
        MenuOptions menuOptions = findMenuOptions(menuOptionsId);
        menuOptionsRepository.delete(menuOptions);

        return menuOptionsId;
    }
}
