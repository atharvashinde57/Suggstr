package com.food.suggester;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private java.util.List<Dish> history = new java.util.ArrayList<>();

    public java.util.List<Dish> getHistory() {
        return history;
    }

    public void addToHistory(Dish dish) {
        this.history.add(dish);
    }

    @Column
    private String fullName;

    @Column
    private Integer age;

    @Column
    private String email;

    @Column
    private String favCuisine;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavCuisine() {
        return favCuisine;
    }

    public void setFavCuisine(String favCuisine) {
        this.favCuisine = favCuisine;
    }
}
