package com.kicon.controller;

import com.kicon.config.ResponseApiMessage;
import com.kicon.domain.dto.Category.CategoryResponseDto;
import com.kicon.domain.dto.Category.CategorySaveRequestDto;
import com.kicon.domain.dto.Category.CategoryUpdateRequestDto;
import com.kicon.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
public class CategoryController extends BaseController {
    private final static int SUCCESS_CODE = 200;
    private final CategoryService categoryService;

    @PostMapping("api/v1/category/{ownerId}")
    public ResponseEntity<ResponseApiMessage> save(@PathVariable Long ownerId, @RequestBody CategorySaveRequestDto requestDto) {
        Long savedCategoryId = categoryService.save(ownerId, requestDto);

        return sendResponseHttpByJson(SUCCESS_CODE, "Category saved.", savedCategoryId);
    }

    @GetMapping("api/v1/category/{categoryId}")
    public ResponseEntity<ResponseApiMessage> findById(@PathVariable Long categoryId) {
        CategoryResponseDto responseDto = categoryService.findById(categoryId);

        return sendResponseHttpByJson(SUCCESS_CODE, "Category loaded. CATEGORY_ID=" + categoryId, responseDto);
    }

    @GetMapping("api/v1/category/all/{ownerId}")
    public ResponseEntity<ResponseApiMessage> findAllCategory(@PathVariable Long ownerId) {
        List<CategoryResponseDto> responseDtoList = categoryService.findAll(ownerId);

        return sendResponseHttpByJson(SUCCESS_CODE, "All categories loaded.", responseDtoList);
    }

    @PutMapping("api/v1/category/{categoryId}")
    public ResponseEntity<ResponseApiMessage> update(@PathVariable Long categoryId, @RequestBody CategoryUpdateRequestDto requestDto) {
        CategoryResponseDto responseDto = categoryService.update(categoryId, requestDto);

        return sendResponseHttpByJson(SUCCESS_CODE, "Category updated. CATEGORY_ID=" + categoryId, responseDto);
    }

    @DeleteMapping("api/v1/category/{categoryId}")
    public ResponseEntity<ResponseApiMessage> delete(@PathVariable Long categoryId) {
        Long deletedCategoryId = categoryService.delete(categoryId);

        return sendResponseHttpByJson(SUCCESS_CODE, "Category deleted. CATEGORY_ID=" + categoryId, deletedCategoryId);
    }
}
