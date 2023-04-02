package com.exercise.football.score.board;

import com.exercise.football.score.board.command.FinishGameCommand;
import com.exercise.football.score.board.command.StartGameCommand;
import com.exercise.football.score.board.command.UpdateScoreCommand;

import java.util.List;

public interface ScoreBoardService {
    void startGame(StartGameCommand startGameCommand);

    void updateScore(UpdateScoreCommand command);

    void finishGame(FinishGameCommand finishGameCommand);

    List<Game> getGames();

    List<Game> getSummary();
}
