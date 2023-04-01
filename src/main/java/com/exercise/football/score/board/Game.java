package com.exercise.football.score.board;

public class Game implements Comparable<Game> {
    private final String gameId;
    private final Team homeTeam;
    private final Team awayTeam;
    private final Long startedAt;

    private GameStatus status;

    public Game(String gameId, String homeTeam, String awayTeam) {
        this.gameId = gameId;
        this.homeTeam = new Team(homeTeam, 0);
        this.awayTeam = new Team(awayTeam, 0);
        this.startedAt = System.nanoTime();
        this.status = GameStatus.STARTED;
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

    public GameStatus getStatus() {
        return this.status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Integer totalScore() {
        return this.homeTeam.getScore() + this.awayTeam.getScore();
    }

    public int compareTo(Game other) {
        int i = other.totalScore().compareTo(totalScore());
        if (i != 0) return i;
        return other.startedAt.compareTo(startedAt);
    }
}
