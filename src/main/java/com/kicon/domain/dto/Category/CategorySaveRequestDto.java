package com.kicon.domain.dto.Category;

import com.kicon.domain.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategorySaveRequestDto {
    String categoryName;

    public Category toEntity() {
        return Category.builder()
                .categoryName(categoryName)
                .build();
    }
}
