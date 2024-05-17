package com.keycon.domain.dto.Menus;
import com.keycon.domain.entity.Menus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenusSaveRequestDto {
    String menusName;
    int menusPrice;
    boolean soldOut;

    public Menus toEntity() {
        return Menus.builder()
                .menusName(menusName)
                .menusPrice(menusPrice)
                .soldOut(soldOut)
                .build();
    }
}
