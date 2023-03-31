package com.exercise.football.score.board;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    private final List<Game> games=new ArrayList<>();

    public Game startGame(StartGameEvent startGameEvent) {
        Game game=new Game(new Team(startGameEvent.homeTeam(),0),new Team(startGameEvent.awayTeam(), 0));
        games.add(game);
        return game;
    }

    public List<Game>getGames(){
        return this.games;
    }
}
