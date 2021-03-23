package com.ftr.dgb.payments.action.catalog.service;

import com.ftr.dgb.payments.action.catalog.service.dto.CategoryActionDto;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ftr.dgb.payments.action.catalog.domain.CategoryAction}.
 */
public interface CategoryActionService {

    /**
     * Save a categoryAction.
     *
     * @param categoryActionDto the entity to save.
     * @return the persisted entity.
     */
    CategoryActionDto save(CategoryActionDto categoryActionDto);

    /**
     * Get all the categoryActions.
     *
     * @return the list of entities.
     */
    List<CategoryActionDto> findAll();


    /**
     * Get the "id" categoryAction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoryActionDto> findOne(Long id);

    /**
     * Delete the "id" categoryAction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
