package com.food.suggester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DishRepository extends JpaRepository<Dish, Long> {
}

@Repository
interface QuestionRepository extends JpaRepository<Question, Long> {
}
