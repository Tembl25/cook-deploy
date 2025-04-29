package com.cookrecipes.service;

import com.cookrecipes.dto.RecipeRequest;
import com.cookrecipes.entity.Recipe;
import com.cookrecipes.entity.User;
import com.cookrecipes.repository.RecipeRepository;
import com.cookrecipes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public Recipe createRecipe(RecipeRequest request, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Recipe recipe = Recipe.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .ingredients(request.getIngredients())
                .instructions(request.getInstructions())
                .imageUrl(request.getImageUrl())
                .cookingTime(request.getCookingTime())
                .author(author)
                .build();

        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Long id, RecipeRequest request, String username) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        if (!recipe.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You can only update your own recipes");
        }

        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setIngredients(request.getIngredients());
        recipe.setInstructions(request.getInstructions());
        recipe.setImageUrl(request.getImageUrl());
        recipe.setCookingTime(request.getCookingTime());

        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id, String username) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        if (!recipe.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You can only delete your own recipes");
        }

        recipeRepository.delete(recipe);
    }

    @Transactional
    public void toggleFavorite(Long recipeId, String username) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getFavoriteRecipes().contains(recipe)) {
            user.getFavoriteRecipes().remove(recipe);
            recipe.getFavoritedBy().remove(user);
        } else {
            user.getFavoriteRecipes().add(recipe);
            recipe.getFavoritedBy().add(user);
        }

        userRepository.save(user);
        recipeRepository.save(recipe);
    }

    public List<Recipe> getUserRecipes(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return recipeRepository.findByAuthor(user);
    }

    public List<Recipe> getUserFavorites(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return recipeRepository.findByFavoritedBy(user);
    }
} 