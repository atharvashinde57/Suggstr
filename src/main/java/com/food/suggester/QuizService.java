package com.food.suggester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll(
                org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC, "name"));
    }

    public Dish suggestDish(List<String> selectedTags) {
        if (selectedTags == null)
            selectedTags = new ArrayList<>();

        System.out.println("DEBUG: Suggesting for tags: " + selectedTags);
        List<Dish> allDishes = dishRepository.findAll();

        Dish bestMatch = null;
        int maxScore = 0; // Require at least one match (score > 0)

        for (Dish dish : allDishes) {
            int score = 0;
            String[] dishTags = dish.getTags().split(",");
            for (String userTag : selectedTags) {
                if (userTag == null)
                    continue;
                for (String dishTag : dishTags) {
                    if (dishTag.trim().equalsIgnoreCase(userTag.trim())) {
                        score++;
                    }
                }
            }
            if (score > maxScore) {
                maxScore = score;
                bestMatch = dish;
            }
        }

        if (bestMatch == null && !allDishes.isEmpty()) {
            System.out.println("DEBUG: No direct match found. Returning random dish.");
            return allDishes.get(new Random().nextInt(allDishes.size()));
        }

        System.out.println("DEBUG: Found match: " + (bestMatch != null ? bestMatch.getName() : "null"));
        return bestMatch;
    }
}
