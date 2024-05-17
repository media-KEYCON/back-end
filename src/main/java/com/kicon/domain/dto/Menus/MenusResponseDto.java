package com.kicon.domain.dto.Menus;

import com.kicon.domain.dto.MenuOptions.MenuOptionsResponseDto;
import com.kicon.domain.entity.MenuOptions;
import com.kicon.domain.entity.Menus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MenusResponseDto {
    Long menusId;
    String menusName;
    int menusPrice;
    boolean soldOut;
    List<MenuOptionsResponseDto> menuOptionsList;

    public MenusResponseDto(Menus menus) {
        this.menusId = menus.getMenusId();
        this.menusName = menus.getMenusName();
        this.menusPrice = menus.getMenusPrice();
        this.soldOut = menus.isSoldOut();
        this.menuOptionsList = new ArrayList<>();
        List<MenuOptions> menuOptions = menus.getMenuOptionsList();
        if(menuOptions != null) {
            for (MenuOptions allMenuOptions : menuOptions) {
                this.menuOptionsList.add(new MenuOptionsResponseDto(allMenuOptions));
            }
        }
    }
}
