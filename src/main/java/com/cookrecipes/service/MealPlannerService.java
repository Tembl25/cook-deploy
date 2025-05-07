package com.cookrecipes.service;

import com.cookrecipes.dto.DailyPlan;
import com.cookrecipes.dto.MealPlannerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealPlannerService {
    
    public void savePlans(MealPlannerRequest request) {
        // TODO: Реализовать сохранение планов в базу данных
    }
    
    public List<DailyPlan> loadPlans(String userId) {
        // TODO: Реализовать загрузку планов из базы данных
        return List.of();
    }
} 