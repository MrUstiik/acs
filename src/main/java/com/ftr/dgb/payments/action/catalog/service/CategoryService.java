package com.ftr.dgb.payments.action.catalog.service;

import com.ftr.dgb.payments.action.catalog.service.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ftr.dgb.payments.action.catalog.domain.Category}.
 */
public interface CategoryService {

    /**
     * Save a category.
     *
     * @param categoryDto the entity to save.
     * @return the persisted entity.
     */
    CategoryDto save(CategoryDto categoryDto);

    /**
     * Get all the categories.
     *
     * @return the list of entities.
     */
    List<CategoryDto> findAll();


    /**
     * Get the "id" category.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoryDto> findOne(Long id);

    /**
     * Delete the "id" category.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
