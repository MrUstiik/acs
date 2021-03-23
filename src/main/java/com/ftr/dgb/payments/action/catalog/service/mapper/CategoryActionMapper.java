package com.ftr.dgb.payments.action.catalog.service.mapper;


import com.ftr.dgb.payments.action.catalog.domain.*;
import com.ftr.dgb.payments.action.catalog.service.dto.CategoryActionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoryAction} and its DTO {@link CategoryActionDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface CategoryActionMapper extends EntityMapper<CategoryActionDTO, CategoryAction> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.categoryId", target = "categoryCategoryId")
    CategoryActionDTO toDto(CategoryAction categoryAction);

    @Mapping(source = "categoryId", target = "category")
    CategoryAction toEntity(CategoryActionDTO categoryActionDTO);

    default CategoryAction fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategoryAction categoryAction = new CategoryAction();
        categoryAction.setId(id);
        return categoryAction;
    }
}
