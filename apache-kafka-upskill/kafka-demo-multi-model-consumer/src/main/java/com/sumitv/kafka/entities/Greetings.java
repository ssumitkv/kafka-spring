package com.sumitv.kafka.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class Greetings {
    private String message;
    private String greetingName;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return greetingName;
    }

    public void setgreetingName(String greetingName) {
        this.greetingName = greetingName;
    }

    public Greetings() {
    }

    public Greetings(String message, String name) {
        this.message = message;
        this.greetingName = name;
    }
}
