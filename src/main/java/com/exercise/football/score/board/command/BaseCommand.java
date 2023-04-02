package com.exercise.football.score.board.command;

public abstract class BaseCommand{

    private final String gameId;

    public BaseCommand(String gameId){
        this.gameId=gameId;
    }

    public String getGameId(){
        return gameId;
    }
}
