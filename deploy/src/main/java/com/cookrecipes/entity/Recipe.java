package com.cookrecipes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(columnDefinition = "TEXT")
    private String ingredients;
    
    @Column(columnDefinition = "TEXT")
    private String instructions;
    
    private String imageUrl;
    
    private Integer cookingTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecipeCategory category;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    
    @ManyToMany(mappedBy = "favoriteRecipes")
    private Set<User> favoritedBy = new HashSet<>();
} 