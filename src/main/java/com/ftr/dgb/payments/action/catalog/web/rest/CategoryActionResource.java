package com.ftr.dgb.payments.action.catalog.web.rest;

import com.ftr.dgb.payments.action.catalog.service.CategoryActionService;
import com.ftr.dgb.payments.action.catalog.web.rest.errors.BadRequestAlertException;
import com.ftr.dgb.payments.action.catalog.service.dto.CategoryActionDto;
import com.ftr.dgb.payments.action.catalog.service.dto.CategoryActionCriteria;
import com.ftr.dgb.payments.action.catalog.service.CategoryActionQueryService;

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
 * REST controller for managing {@link com.ftr.dgb.payments.action.catalog.domain.CategoryAction}.
 */
@RestController
@RequestMapping("/api")
public class CategoryActionResource {

    private final Logger log = LoggerFactory.getLogger(CategoryActionResource.class);

    private static final String ENTITY_NAME = "actionCatalogServiceCategoryAction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryActionService categoryActionService;

    private final CategoryActionQueryService categoryActionQueryService;

    public CategoryActionResource(CategoryActionService categoryActionService, CategoryActionQueryService categoryActionQueryService) {
        this.categoryActionService = categoryActionService;
        this.categoryActionQueryService = categoryActionQueryService;
    }

    /**
     * {@code POST  /category-actions} : Create a new categoryAction.
     *
     * @param categoryActionDto the categoryActionDto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryActionDto, or with status {@code 400 (Bad Request)} if the categoryAction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/category-actions")
    public ResponseEntity<CategoryActionDto> createCategoryAction(@Valid @RequestBody CategoryActionDto categoryActionDto) throws URISyntaxException {
        log.debug("REST request to save CategoryAction : {}", categoryActionDto);
        if (categoryActionDto.getId() != null) {
            throw new BadRequestAlertException("A new categoryAction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryActionDto result = categoryActionService.save(categoryActionDto);
        return ResponseEntity.created(new URI("/api/category-actions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /category-actions} : Updates an existing categoryAction.
     *
     * @param categoryActionDto the categoryActionDto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryActionDto,
     * or with status {@code 400 (Bad Request)} if the categoryActionDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryActionDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/category-actions")
    public ResponseEntity<CategoryActionDto> updateCategoryAction(@Valid @RequestBody CategoryActionDto categoryActionDto) throws URISyntaxException {
        log.debug("REST request to update CategoryAction : {}", categoryActionDto);
        if (categoryActionDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategoryActionDto result = categoryActionService.save(categoryActionDto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryActionDto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /category-actions} : get all the categoryActions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryActions in body.
     */
    @GetMapping("/category-actions")
    public ResponseEntity<List<CategoryActionDto>> getAllCategoryActions(CategoryActionCriteria criteria) {
        log.debug("REST request to get CategoryActions by criteria: {}", criteria);
        List<CategoryActionDto> entityList = categoryActionQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /category-actions/count} : count all the categoryActions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/category-actions/count")
    public ResponseEntity<Long> countCategoryActions(CategoryActionCriteria criteria) {
        log.debug("REST request to count CategoryActions by criteria: {}", criteria);
        return ResponseEntity.ok().body(categoryActionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /category-actions/:id} : get the "id" categoryAction.
     *
     * @param id the id of the categoryActionDto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryActionDto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-actions/{id}")
    public ResponseEntity<CategoryActionDto> getCategoryAction(@PathVariable Long id) {
        log.debug("REST request to get CategoryAction : {}", id);
        Optional<CategoryActionDto> categoryActionDto = categoryActionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryActionDto);
    }

    /**
     * {@code DELETE  /category-actions/:id} : delete the "id" categoryAction.
     *
     * @param id the id of the categoryActionDto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/category-actions/{id}")
    public ResponseEntity<Void> deleteCategoryAction(@PathVariable Long id) {
        log.debug("REST request to delete CategoryAction : {}", id);
        categoryActionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
