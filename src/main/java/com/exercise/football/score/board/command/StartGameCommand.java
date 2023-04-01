package com.exercise.football.score.board.command;

public record StartGameCommand(String gameId, String homeTeam, String awayTeam) implements BaseCommand {
    @Override
    public String getGameId() {
            return gameId;
    }
}

