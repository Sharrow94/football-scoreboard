package com.exercise.football.score.board.command;

public record FinishGameCommand (String gameId)implements BaseCommand{
    @Override
    public String getGameId() {
        return gameId;
    }
}
