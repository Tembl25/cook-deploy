package com.cookrecipes.controller;

import com.cookrecipes.dto.RecipeRequest;
import com.cookrecipes.entity.Recipe;
import com.cookrecipes.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@Tag(name = "Рецепты", description = "API для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    @Operation(summary = "Создание нового рецепта")
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeRequest request, Authentication authentication) {
        return ResponseEntity.ok(recipeService.createRecipe(request, authentication.getName()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление рецепта")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody RecipeRequest request, Authentication authentication) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, request, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id, Authentication authentication) {
        recipeService.deleteRecipe(id, authentication.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/favorite")
    @Operation(summary = "Добавление/удаление рецепта в избранное")
    public ResponseEntity<Void> toggleFavorite(@PathVariable Long id, Authentication authentication) {
        recipeService.toggleFavorite(id, authentication.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    @Operation(summary = "Получение рецептов текущего пользователя")
    public ResponseEntity<List<Recipe>> getMyRecipes(Authentication authentication) {
        return ResponseEntity.ok(recipeService.getUserRecipes(authentication.getName()));
    }

    @GetMapping("/favorites")
    @Operation(summary = "Получение избранных рецептов")
    public ResponseEntity<List<Recipe>> getFavorites(Authentication authentication) {
        return ResponseEntity.ok(recipeService.getUserFavorites(authentication.getName()));
    }
} 