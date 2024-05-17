package com.kicon.domain.dto.MenuOptions;

import com.kicon.domain.entity.MenuOptions;
import lombok.Getter;

@Getter
public class MenuOptionsResponseDto {
    Long menuOptionsId;
    String menusName; // 무슨 메뉴의 옵션인지 알려줌
    String menuOptionsCategory;
    String menuOptionsContents;
    int menuOptionsPrice;
    boolean mandatory;

    public MenuOptionsResponseDto(MenuOptions menuOptions) {
        this.menuOptionsId = menuOptions.getMenuOptionsId();
        this.menusName = menuOptions.getMenus().getMenusName();
        this.menuOptionsCategory = menuOptions.getMenuOptionsCategory();
        this.menuOptionsContents = menuOptions.getMenuOptionsContents();
        this.menuOptionsPrice = menuOptions.getMenuOptionsPrice();
        this.mandatory = menuOptions.isMandatory();
    }
}
