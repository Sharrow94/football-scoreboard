package com.exercise.football.score.board.command;

public class StartGameCommand extends BaseCommand {
    private final String homeTeam;
    private final String awayTeam;

    public StartGameCommand(String gameId,String homeTeam,String awayTeam) {
        super(gameId);
        this.homeTeam=homeTeam;
        this.awayTeam=awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }
}

