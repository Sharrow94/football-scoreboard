package com.exercise.football.score.board;

import java.time.LocalDateTime;


public class Game {
    private final String gameId;
    private final Team homeTeam;
    private final Team awayTeam;
    private final LocalDateTime startedAt;

    public Game(String gameId, String homeTeam, String awayTeam) {
        this.gameId = gameId;
        this.homeTeam = new Team(homeTeam, 0);
        this.awayTeam = new Team(awayTeam, 0);
        startedAt = LocalDateTime.now();
    }

    public String getGameId() {
        return gameId;
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
