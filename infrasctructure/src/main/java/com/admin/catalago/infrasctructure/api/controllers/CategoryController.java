package com.admin.catalago.infrasctructure.api.controllers;

import com.admin.catalago.application.category.create.CreateCategoryCommand;
import com.admin.catalago.application.category.create.CreateCategoryOutput;
import com.admin.catalago.application.category.create.CreateCategoryUseCase;
import com.admin.catalago.application.category.delete.DeleteCategoryUseCase;
import com.admin.catalago.application.category.retrieve.get.GetCategoryByIdUseCase;
import com.admin.catalago.application.category.retrieve.list.ListCategoriesUseCase;
import com.admin.catalago.application.category.update.UpdateCategoryCommand;
import com.admin.catalago.application.category.update.UpdateCategoryOutput;
import com.admin.catalago.application.category.update.UpdateCategoryUseCase;
import com.admin.catalago.domain.pagination.SearchQuery;
import com.admin.catalago.domain.pagination.Pagination;
import com.admin.catalago.domain.validation.handler.Notification;
import com.admin.catalago.infrasctructure.api.CategoryAPI;
import com.admin.catalago.infrasctructure.category.models.CategoryListResponse;
import com.admin.catalago.infrasctructure.category.models.CategoryResponse;
import com.admin.catalago.infrasctructure.category.models.CreateCategoryRequest;
import com.admin.catalago.infrasctructure.category.models.UpdateCategoryRequest;
import com.admin.catalago.infrasctructure.category.presenters.CategoryAPIPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;

    public CategoryController(
            final CreateCategoryUseCase createCategoryUseCase,
            final GetCategoryByIdUseCase getCategoryByIdUseCase,
            final UpdateCategoryUseCase updateCategoryUseCase,
            final DeleteCategoryUseCase deleteCategoryUseCase,
            final ListCategoriesUseCase listCategoriesUseCase
    ) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
        this.getCategoryByIdUseCase = Objects.requireNonNull(getCategoryByIdUseCase);
        this.updateCategoryUseCase = Objects.requireNonNull(updateCategoryUseCase);
        this.deleteCategoryUseCase = Objects.requireNonNull(deleteCategoryUseCase);
        this.listCategoriesUseCase = Objects.requireNonNull(listCategoriesUseCase);
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryRequest input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);

        return this.createCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public Pagination<CategoryListResponse> listCategories(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return listCategoriesUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(CategoryAPIPresenter::present);
    }

    @Override
    public CategoryResponse getById(final String id) {
        return CategoryAPIPresenter.present(this.getCategoryByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateCategoryRequest input) {
        final var aCommand = UpdateCategoryCommand.with(
                id,
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateCategoryOutput, ResponseEntity<?>> onSuccess =
                ResponseEntity::ok;

        return this.updateCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public void deleteById(final String anId) {
        this.deleteCategoryUseCase.execute(anId);
    }
}