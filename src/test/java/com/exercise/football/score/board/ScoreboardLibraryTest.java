package com.exercise.football.score.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreboardLibraryTest {

    @Test
    @DisplayName("Start a new game, assuming initial score 0 – 0 and adding it the scoreboard")
    public void start_new_match() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        //when
        Game game = scoreBoard.startGame(new StartGameEvent("France", "Poland"));
        //then
        assertEquals(1,scoreBoard.getGames().size());
        assertEquals(0,game.getHomeTeam().score());
        assertEquals(0,game.getAwayTeam().score());
    }

}