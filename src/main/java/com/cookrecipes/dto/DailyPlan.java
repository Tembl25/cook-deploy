package com.cookrecipes.dto;

import lombok.Data;
import java.util.List;

@Data
public class DailyPlan {
    private String date;
    private List<Meal> meals;
    
    @Data
    public static class Meal {
        private String type; // breakfast, lunch, dinner, snack
        private List<Recipe> recipes;
    }
    
    @Data
    public static class Recipe {
        private Long id;
        private String title;
        private String description;
    }
} 