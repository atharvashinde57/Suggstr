package com.food.suggester;

import javax.persistence.*;

@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String tagEffect;

    public Option() {
    }

    public Option(Long id, String text, String tagEffect) {
        this.id = id;
        this.text = text;
        this.tagEffect = tagEffect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTagEffect() {
        return tagEffect;
    }

    public void setTagEffect(String tagEffect) {
        this.tagEffect = tagEffect;
    }
}
