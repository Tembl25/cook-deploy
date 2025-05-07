package com.cookrecipes.service;

import com.cookrecipes.dto.DailyPlan;
import com.cookrecipes.dto.MealPlannerRequest;
import com.cookrecipes.entity.User;
import com.cookrecipes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealPlannerService {
    private final UserRepository userRepository;
    
    public void savePlans(MealPlannerRequest request) {
        User user = userRepository.findByUsername(request.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // TODO: Реализовать сохранение планов в базу данных
    }
    
    public List<DailyPlan> loadPlans(String userId) {
        User user = userRepository.findByUsername(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // TODO: Реализовать загрузку планов из базы данных
        return List.of();
    }
} 