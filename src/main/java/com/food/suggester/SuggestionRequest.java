package com.food.suggester;

import java.util.List;

public class SuggestionRequest {
    private List<String> tags;
    private String username;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
