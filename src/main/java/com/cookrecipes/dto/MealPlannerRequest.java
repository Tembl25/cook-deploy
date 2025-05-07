package com.cookrecipes.dto;

import lombok.Data;
import java.util.List;

@Data
public class MealPlannerRequest {
    private String userId;
    private List<DailyPlan> plans;
    private String lastUpdated;
} 