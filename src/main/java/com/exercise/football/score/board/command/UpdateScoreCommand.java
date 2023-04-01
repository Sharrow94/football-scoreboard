package com.exercise.football.score.board.command;

public record UpdateScoreCommand(String gameId, Integer homeTeam, Integer awayTeam) implements BaseCommand{
    @Override
    public String getGameId() {
        return gameId;
    }
}
