package com.exercise.football.score.board;

import com.exercise.football.score.board.command.FinishGameCommand;
import com.exercise.football.score.board.command.StartGameCommand;
import com.exercise.football.score.board.command.UpdateScoreCommand;
import com.exercise.football.score.board.exception.ScoreboardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreboardLibraryTest {

    @Test
    @DisplayName("Start a new game, assuming initial score 0 – 0 and adding it the scoreboard.")
    public void start_new_match() {
        //given
        ScoreBoardService scoreBoard = new ScoreBoard();
        //when
        scoreBoard.startGame(new StartGameCommand(valueOf(1L), "France", "Poland"));
        //then
        assertEquals(1, scoreBoard.getGames().size());
        Game game = scoreBoard.getGames().get(0);
        assertEquals(valueOf(1L), game.getGameId());
        assertEquals(0, game.getHomeTeam().getScore());
        assertEquals(0, game.getAwayTeam().getScore());
    }

    @Test
    @DisplayName("Throw an exception during match starting if one of the teams is null")
    public void start_new_match_throws_exception(){
        //given
        ScoreBoardService scoreBoard = new ScoreBoard();
        String gameId=valueOf(1L);
        String awayTeam="Poland";
        //when //then
        ScoreboardException exception = assertThrows(ScoreboardException.class, () -> scoreBoard.startGame(new StartGameCommand(gameId, null, awayTeam)));
        assertEquals(format("Can not start game with parameters: id=%s ,homeTeam=%s ,awayTeam=%s",gameId,null,awayTeam),exception.getMessage());
    }

    @Test
    @DisplayName("Update score. This should receive a pair of absolute scores: home team score and away team score.")
    public void update_score() {
        //given
        ScoreBoardService scoreBoard = new ScoreBoard();
        scoreBoard.startGame(new StartGameCommand(valueOf(1L), "France", "Poland"));
        //when
        scoreBoard.updateScore(new UpdateScoreCommand(valueOf(1L), 0, 1));
        //then
        assertEquals(1, scoreBoard.getGames().size());
        assertEquals(0, scoreBoard.getGames().get(0).getHomeTeam().getScore());
        assertEquals(1, scoreBoard.getGames().get(0).getAwayTeam().getScore());
    }

    @Test
    @DisplayName("Update score throws exception if one of the teams has score= -1")
    public void update_score_throws_exception(){
        //given
        String gameId=valueOf(1L);
        ScoreBoardService scoreBoard = new ScoreBoard();
        scoreBoard.startGame(new StartGameCommand(gameId, "France", "Poland"));
        //when //then
        ScoreboardException exception = assertThrows(ScoreboardException.class, () -> scoreBoard.updateScore(new UpdateScoreCommand(gameId, -1, 1)));
        assertEquals("UpdateCommand in incorrect state",exception.getMessage());
    }

    @Test
    @DisplayName("Finish game currently in progress. This removes a match from the scoreboard.")
    public void finish_game() {
        //given
        ScoreBoardService scoreBoard = new ScoreBoard();
        scoreBoard.startGame(new StartGameCommand(valueOf(1L), "France", "Poland"));
        //when
        scoreBoard.finishGame(new FinishGameCommand(valueOf(1L)));
        //then
        assertEquals(0, scoreBoard.getGames().size());
    }

    @Test
    @DisplayName("Finish game throws exception when game is not existing anymore")
    public void finish_game_throws_exception(){
        //give
        String gameId=valueOf(1L);
        ScoreBoardService scoreBoard = new ScoreBoard();
        //when //then
        ScoreboardException exception = assertThrows(ScoreboardException.class, () -> scoreBoard.finishGame(new FinishGameCommand(gameId)));
        assertEquals("Can not find game in progress with id: "+gameId,exception.getMessage());
    }

    @Test
    @DisplayName("Get a summary of games in progress ordered by their total score. The games with the same" +
            "total score will be returned ordered by the most recently started match in the scoreboard.")
    public void get_summary_of_games_in_progress() {
        //given
        ScoreBoardService scoreBoard = new ScoreBoard();
        scoreBoard.startGame(new StartGameCommand(valueOf(1L), "Mexico", "Canada"));
        scoreBoard.updateScore(new UpdateScoreCommand(valueOf(1L),0,5));
        scoreBoard.startGame(new StartGameCommand(valueOf(2L), "Spain", "Brazil"));
        scoreBoard.updateScore(new UpdateScoreCommand(valueOf(2L),10,2));
        scoreBoard.startGame(new StartGameCommand(valueOf(3L), "Germany", "France"));
        scoreBoard.updateScore(new UpdateScoreCommand(valueOf(3L),2,2));
        scoreBoard.startGame(new StartGameCommand(valueOf(4L), "Uruguay", "Italy"));
        scoreBoard.updateScore(new UpdateScoreCommand(valueOf(4L),6,6));
        scoreBoard.startGame(new StartGameCommand(valueOf(5L), "Argentina", "Australia"));
        scoreBoard.updateScore(new UpdateScoreCommand(valueOf(5L),3,1));
        //when
        List<Game> summary = scoreBoard.getSummary();
        //then
        assertEquals(valueOf(4L),summary.get(0).getGameId());
        assertEquals(valueOf(2L),summary.get(1).getGameId());
        assertEquals(valueOf(1L),summary.get(2).getGameId());
        assertEquals(valueOf(5L),summary.get(3).getGameId());
        assertEquals(valueOf(3L),summary.get(4).getGameId());
    }
}