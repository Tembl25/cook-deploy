package com.cookrecipes.controller;

import com.cookrecipes.dto.DailyPlan;
import com.cookrecipes.dto.MealPlannerRequest;
import com.cookrecipes.service.MealPlannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal-planner")
@RequiredArgsConstructor
@Tag(name = "Планировщик питания", description = "API для работы с планами питания")
public class MealPlannerController {
    private final MealPlannerService mealPlannerService;

    @PostMapping("/save")
    @Operation(summary = "Сохранение планов питания")
    public ResponseEntity<Void> savePlans(@RequestBody MealPlannerRequest request, Authentication authentication) {
        mealPlannerService.savePlans(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/load/{userId}")
    @Operation(summary = "Загрузка планов питания")
    public ResponseEntity<List<DailyPlan>> loadPlans(@PathVariable String userId, Authentication authentication) {
        return ResponseEntity.ok(mealPlannerService.loadPlans(userId));
    }
} 