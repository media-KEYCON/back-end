package com.keycon.domain.dto.MenuOptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuOptionsUpdateRequestDto {
    String menuOptionsCategory;
    String menuOptionsContents;
    int menuOptionsPrice;
    boolean mandatory;
}
