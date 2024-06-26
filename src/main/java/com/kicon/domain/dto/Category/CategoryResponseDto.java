package com.kicon.domain.dto.Category;

import com.kicon.domain.dto.Menus.MenusResponseDto;
import com.kicon.domain.entity.Category;
import com.kicon.domain.entity.Menus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CategoryResponseDto {
    Long categoryId;
    String categoryName;
    List<MenusResponseDto> menusList;

    public CategoryResponseDto(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.menusList = new ArrayList<>();
        List<Menus> menus = category.getCategoryMenusList();
        if (menus!=null) {
            for (Menus allMenusWithCategory : menus) {
                this.menusList.add(new MenusResponseDto(allMenusWithCategory));
            }
        }
    }
}
