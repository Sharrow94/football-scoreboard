package com.exercise.football.score.board;

import java.time.LocalDateTime;

public class Game{
    private final Team homeTeam;
    private final Team awayTeam;
    private final LocalDateTime startedAt;

    public Game(Team homeTeam,Team awayTeam ){
        this.homeTeam=homeTeam;
        this.awayTeam=awayTeam;
        startedAt=LocalDateTime.now();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }
}
