package com.kicon.domain.dto.MenuOptions;

import com.kicon.domain.entity.MenuOptions;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuOptionsSaveRequestDto {
    String menuOptionsCategory;
    String menuOptionsContents;
    int menuOptionsPrice;
    boolean mandatory;

    public MenuOptions toEntity() {
        return MenuOptions.builder()
                .menuOptionsCategory(menuOptionsCategory)
                .menuOptionsContents(menuOptionsContents)
                .menuOptionsPrice(menuOptionsPrice)
                .mandatory(mandatory)
                .build();
    }

}
