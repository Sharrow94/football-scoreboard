package com.exercise.football.score.board;

public class Team {
    private final String name;
    private Integer score;

    public Team(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
