package com.exercise.football.score.board;

import com.exercise.football.score.board.command.FinishGameCommand;
import com.exercise.football.score.board.command.StartGameCommand;
import com.exercise.football.score.board.command.UpdateScoreCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreboardLibraryTest {

    @Test
    @DisplayName("Start a new game, assuming initial score 0 – 0 and adding it the scoreboard.")
    public void start_new_match() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        //when
        scoreBoard.startGame(new StartGameCommand(String.valueOf(1L),"France", "Poland"));
        //then
        assertEquals(1,scoreBoard.getGames().size());
        Game game = scoreBoard.getGames().get(0);
        assertEquals(String.valueOf(1L),game.getGameId());
        assertEquals(0, game.getHomeTeam().getScore());
        assertEquals(0, game.getAwayTeam().getScore());
    }

    @Test
    @DisplayName("Update score. This should receive a pair of absolute scores: home team score and away team score.")
    public void update_score() {
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startGame(new StartGameCommand(String.valueOf(1L),"France", "Poland"));
        //when
        scoreBoard.updateScore(new UpdateScoreCommand(String.valueOf(1L),0,1));
        //then
        assertEquals(1,scoreBoard.getGames().size());
        assertEquals(0,scoreBoard.getGames().get(0).getHomeTeam().getScore());
        assertEquals(1,scoreBoard.getGames().get(0).getAwayTeam().getScore());
    }

    @Test
    @DisplayName("Finish game currently in progress. This removes a match from the scoreboard.")
    public void finish_game(){
        //given
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startGame(new StartGameCommand(String.valueOf(1L),"France", "Poland"));
        //when
        scoreBoard.finishGame(new FinishGameCommand(String.valueOf(1L)));
        //then
        assertEquals(0,scoreBoard.getGames().size());
    }
}