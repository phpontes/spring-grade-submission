package com.ltp.gradesubmission.pojo;

import java.util.UUID;

import com.ltp.gradesubmission.validation.Score;

import jakarta.validation.constraints.NotBlank;

public class Grade {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Subject is mandatory")
    private String subject;

    @Score(message = "Score must be a letter grade")
    private String score;
    private String id;

    public Grade(String name, String subject, String score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
        this.id = UUID.randomUUID().toString();
    }

    public Grade() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
