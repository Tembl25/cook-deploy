package com.cookrecipes.repository;

import com.cookrecipes.entity.Recipe;
import com.cookrecipes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByAuthor(User author);
    List<Recipe> findByFavoritedBy(User user);
} 