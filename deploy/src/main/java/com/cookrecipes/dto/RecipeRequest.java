package com.cookrecipes.dto;

import com.cookrecipes.entity.RecipeCategory;
import lombok.Data;

@Data
public class RecipeRequest {
    private String title;
    private String description;
    private String ingredients;
    private String instructions;
    private String imageUrl;
    private Integer cookingTime;
    private RecipeCategory category;
} 