package com.kicon.service;

import com.kicon.domain.dto.Owner.OwnerLoginRequestDto;
import com.kicon.domain.dto.Owner.OwnerResponseDto;
import com.kicon.domain.dto.Owner.OwnerSaveRequestDto;
import com.kicon.domain.dto.Owner.OwnerUpdateRequestDto;
import com.kicon.domain.entity.Owner;
import com.kicon.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public Owner findOwner(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 없습니다. OWNER_ID=" + ownerId));
    }

    public OwnerResponseDto findForLogin(OwnerLoginRequestDto requestDto) {
        Owner owner = ownerRepository.findByOwnerName(requestDto.getOwnerName())
                .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 없습니다. OWNER_NAME=" + requestDto.getOwnerName()));

        if (owner.getPassword().equals(requestDto.getPassword())) {
            return new OwnerResponseDto(owner);
        }

        return null;
    }

    @Transactional
    public Long save(OwnerSaveRequestDto requestDto) {
        Owner owner = requestDto.toEntity();

        return ownerRepository.save(owner).getOwnerId();
    }

    public OwnerResponseDto findById(Long ownerId) {
        Owner owner = findOwner(ownerId);

        return new OwnerResponseDto(owner);
    }

    public List<OwnerResponseDto> findAll() {
        List<OwnerResponseDto> ownerResponseDtoList = new ArrayList<>();
        List<Owner> ownerList = ownerRepository.findAll();

        for(Owner owner : ownerList) {
            ownerResponseDtoList.add(new OwnerResponseDto(owner));
        }

        return ownerResponseDtoList;
    }

    @Transactional
    public OwnerResponseDto update(Long ownerId, OwnerUpdateRequestDto requestDto) {
        Owner owner = findOwner(ownerId);

        owner.update(requestDto.getShopName(), requestDto.getPassword());

        return findById(ownerId);
    }

    @Transactional
    public Long delete(Long ownerId) {
        Owner owner = findOwner(ownerId);
        ownerRepository.delete(owner);

        return ownerId;
    }
}
