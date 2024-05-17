package com.keycon.service;

import com.keycon.domain.dto.Menus.MenusResponseDto;
import com.keycon.domain.dto.Menus.MenusSaveRequestDto;
import com.keycon.domain.dto.Menus.MenusUpdateRequestDto;
import com.keycon.domain.entity.Category;
import com.keycon.domain.entity.Menus;
import com.keycon.repository.CategoryRepository;
import com.keycon.repository.MenusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MenusService {
    private final MenusRepository menusRepository;
    private final CategoryRepository categoryRepository;

    public Menus findMenus(Long menuId) {
        return menusRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다. MENU_ID=" + menuId));
    }

    public Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. CATEGORY_ID=" + categoryId));
    }

    public MenusResponseDto findById(Long menusId) { // 사용 X, update return값에 사용하기 위해 남겨둠
        Menus menus = findMenus(menusId);

        return new MenusResponseDto(menus);
    }

    @Transactional
    public Long save(Long categoryId, MenusSaveRequestDto requestDto) {
        Category category = findCategory(categoryId);
        Menus menus = requestDto.toEntity();
        menus.addCategory(category);
        category.addMenus(menus);

        return menusRepository.save(menus).getMenusId();
    }

    @Transactional
    public MenusResponseDto update(Long menusId, MenusUpdateRequestDto requestDto) {
        Menus menus = findMenus(menusId);

        menus.update(requestDto.getMenusName(), requestDto.getMenusPrice(), requestDto.isSoldOut());

        return findById(menusId);
    }

    @Transactional
    public Long delete(Long menusId) {
        Menus menus = findMenus(menusId);
        menusRepository.delete(menus);

        return menusId;
    }

}
