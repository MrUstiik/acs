package com.ftr.dgb.payments.action.catalog.web.rest;

import com.ftr.dgb.payments.action.catalog.service.CategoryService;
import com.ftr.dgb.payments.action.catalog.web.rest.errors.BadRequestAlertException;
import com.ftr.dgb.payments.action.catalog.service.dto.CategoryDto;
import com.ftr.dgb.payments.action.catalog.service.dto.CategoryCriteria;
import com.ftr.dgb.payments.action.catalog.service.CategoryQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ftr.dgb.payments.action.catalog.domain.Category}.
 */
@RestController
@RequestMapping("/api")
public class CategoryResource {

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    private static final String ENTITY_NAME = "actionCatalogServiceCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryService categoryService;

    private final CategoryQueryService categoryQueryService;

    public CategoryResource(CategoryService categoryService, CategoryQueryService categoryQueryService) {
        this.categoryService = categoryService;
        this.categoryQueryService = categoryQueryService;
    }

    /**
     * {@code POST  /categories} : Create a new category.
     *
     * @param categoryDto the categoryDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryDto, or with status {@code 400 (Bad Request)} if the category has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) throws URISyntaxException {
        log.debug("REST request to save Category : {}", categoryDto);
        if (categoryDto.getId() != null) {
            throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryDto result = categoryService.save(categoryDto);
        return ResponseEntity.created(new URI("/api/categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categories} : Updates an existing category.
     *
     * @param categoryDto the categoryDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryDto,
     * or with status {@code 400 (Bad Request)} if the categoryDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categories")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto) throws URISyntaxException {
        log.debug("REST request to update Category : {}", categoryDto);
        if (categoryDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategoryDto result = categoryService.save(categoryDto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categories} : get all the categories.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categories in body.
     */
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(CategoryCriteria criteria) {
        log.debug("REST request to get Categories by criteria: {}", criteria);
        List<CategoryDto> entityList = categoryQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /categories/count} : count all the categories.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/categories/count")
    public ResponseEntity<Long> countCategories(CategoryCriteria criteria) {
        log.debug("REST request to count Categories by criteria: {}", criteria);
        return ResponseEntity.ok().body(categoryQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /categories/:id} : get the "id" category.
     *
     * @param id the id of the categoryDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);
        Optional<CategoryDto> categoryDto = categoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryDto);
    }

    /**
     * {@code DELETE  /categories/:id} : delete the "id" category.
     *
     * @param id the id of the categoryDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.debug("REST request to delete Category : {}", id);
        categoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
