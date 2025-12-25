package com.food.suggester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return quizService.getAllQuestions();
    }

    @GetMapping("/dishes")
    public List<Dish> getAllDishes() {
        return quizService.getAllDishes();
    }

    @PostMapping("/suggest")
    public Dish getSuggestion(@RequestBody SuggestionRequest request) {
        Dish suggested = quizService.suggestDish(request.getTags());

        if (request.getUsername() != null && !request.getUsername().isEmpty()) {
            userRepository.findByUsername(request.getUsername()).ifPresent(user -> {
                user.addToHistory(suggested);
                userRepository.save(user);
            });
        }
        return suggested;
    }

    @GetMapping("/user/{username}/stats")
    public java.util.Map<String, Integer> getUserStats(@PathVariable String username) {
        java.util.Map<String, Integer> stats = new java.util.HashMap<>();
        userRepository.findByUsername(username).ifPresent(user -> {
            for (Dish d : user.getHistory()) {
                stats.put(d.getName(), stats.getOrDefault(d.getName(), 0) + 1);
            }
        });
        return stats;
    }
}
