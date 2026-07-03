package br.com.gestao.api.category.mapper;

import br.com.gestao.api.category.domain.Category;
import br.com.gestao.api.category.dto.CategoryRequest;
import br.com.gestao.api.category.dto.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request){
        return new Category(
                request.name().trim(),
                request.description().trim()
        );
    }

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public void updateEntity(Category category, CategoryRequest request) {
        category.update(
                request.name().trim(),
                request.description().trim()
        );
    }
}
