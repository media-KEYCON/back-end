package com.kicon.domain.dto.Owner;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerLoginRequestDto {
    private String ownerName;
    private String password;

}
