package com.keycon.domain.dto.Owner;

import com.keycon.domain.entity.Owner;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerSaveRequestDto {
    String ownerName;
    String shopName;
    String password;

    public Owner toEntity() {
        return Owner.builder()
                .ownerName(ownerName)
                .shopName(shopName)
                .password(password)
                .build();
    }
}
