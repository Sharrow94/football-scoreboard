package com.exercise.football.score.board;

import com.exercise.football.score.board.command.FinishGameCommand;
import com.exercise.football.score.board.command.StartGameCommand;
import com.exercise.football.score.board.command.UpdateScoreCommand;
import com.exercise.football.score.board.exception.ScoreboardException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.lang3.ObjectUtils.allNotNull;

public class ScoreBoard {

    private final List<Game> games = new ArrayList<>();

    public void startGame(StartGameCommand startGameCommand) {
        String gameId = startGameCommand.gameId();
        String homeTeam = startGameCommand.homeTeam();
        String awayTeam = startGameCommand.awayTeam();
        if (allNotNull(gameId, homeTeam, awayTeam)) {
            games.add(new Game(gameId, homeTeam, awayTeam));
        } else {
            throw new ScoreboardException(format("Can not start game with parameters: id=%s ,homeTeam=%s ,awayTeam=%s", gameId, homeTeam, awayTeam));
        }

    }

    public void updateScore(UpdateScoreCommand updateScoreCommand) {
        String gameId = updateScoreCommand.gameId();
        Game gameToUpdate = games.stream()
                .filter(game -> game.getGameId().equals(gameId))
                .findFirst().orElseThrow(() -> new ScoreboardException(format("Can not find game to update with id=%s", gameId)));
        gameToUpdate.getHomeTeam().setScore(updateScoreCommand.homeTeam());
        gameToUpdate.getAwayTeam().setScore(updateScoreCommand.awayTeam());
    }

    public void finishGame(FinishGameCommand finishGameCommand) {
        games.removeIf(game->game.getGameId().equals(finishGameCommand.gameId()));
    }

    public List<Game> getGames() {
        return this.games;
    }
}
