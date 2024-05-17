package com.kicon.service;

import com.kicon.domain.dto.Category.CategoryResponseDto;
import com.kicon.domain.dto.Category.CategorySaveRequestDto;
import com.kicon.domain.dto.Category.CategoryUpdateRequestDto;
import com.kicon.domain.entity.Category;
import com.kicon.domain.entity.Owner;
import com.kicon.repository.CategoryRepository;
import com.kicon.repository.MenusRepository;
import com.kicon.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final OwnerRepository ownerRepository;
    private final MenusRepository menusRepository;

    public Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. CATEGORY_ID=" + categoryId));
    }

    public Owner findOwner(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 관리자가 없습니다. OWNER_ID=" + ownerId));
    }


    @Transactional
    public Long save(Long ownerId, CategorySaveRequestDto requestDto) {
        Owner owner = findOwner(ownerId);
        Category category = requestDto.toEntity();
        category.addOwner(owner);
        owner.addCategory(category);

        return categoryRepository.save(category).getCategoryId();
    }

    public CategoryResponseDto findById(Long categoryId) {
        Category category = findCategory(categoryId);

        return new CategoryResponseDto(category);
    }

    public List<CategoryResponseDto> findAll(Long ownerId) {
        List<CategoryResponseDto> responseDtoList = new ArrayList<>();
        Owner owner = findOwner(ownerId);
        List<Category> categoryList = owner.getCategoryList();

        for(Category category : categoryList) {
            responseDtoList.add(new CategoryResponseDto(category));
        }

        return responseDtoList;
    }

    @Transactional
    public CategoryResponseDto update(Long categoryId, CategoryUpdateRequestDto requestDto) {
        Category category = findCategory(categoryId);

        category.update(requestDto.getCategoryName());

        return findById(categoryId);
    }

    @Transactional
    public Long delete(Long categoryId) {
        Category category = findCategory(categoryId);
        categoryRepository.delete(category);

        return categoryId;
    }
}
