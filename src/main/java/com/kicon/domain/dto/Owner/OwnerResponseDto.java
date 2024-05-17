package com.kicon.domain.dto.Owner;

import com.kicon.domain.entity.Owner;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class OwnerResponseDto {
    Long ownerId;
    String ownerName;
    String shopName;
    String password;
    String createdDate;
    String modifiedDate;

    public OwnerResponseDto(Owner owner) {
        this.ownerId = owner.getOwnerId();
        this.ownerName = owner.getOwnerName();
        this.shopName = owner.getShopName();
        this.password = owner.getPassword();
        this.createdDate = owner.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        this.modifiedDate = owner.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }
}
