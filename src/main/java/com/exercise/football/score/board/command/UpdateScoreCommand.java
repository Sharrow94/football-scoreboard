package com.exercise.football.score.board.command;

public class UpdateScoreCommand extends BaseCommand{

    private final Integer homeTeam;
    private final Integer awayTeam;

    public UpdateScoreCommand(String gameId,Integer homeTeam,Integer awayTeam) {
        super(gameId);
        this.homeTeam=homeTeam;
        this.awayTeam=awayTeam;
    }

    public Integer getHomeTeam() {
        return homeTeam;
    }

    public Integer getAwayTeam() {
        return awayTeam;
    }
}
