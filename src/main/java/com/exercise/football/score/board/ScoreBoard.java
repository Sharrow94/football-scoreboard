package com.exercise.football.score.board;

import com.exercise.football.score.board.command.BaseCommand;
import com.exercise.football.score.board.command.FinishGameCommand;
import com.exercise.football.score.board.command.StartGameCommand;
import com.exercise.football.score.board.command.UpdateScoreCommand;
import com.exercise.football.score.board.exception.ScoreboardException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.lang3.ObjectUtils.allNotNull;

public class ScoreBoard implements ScoreBoardService {

    private final List<Game> games = new ArrayList<>();

    @Override
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

    @Override
    public void updateScore(UpdateScoreCommand command) {
        updateCommandValidation(command);
        Game game = findGameInProgressValidation(command);
        game.getHomeTeam().setScore(command.homeTeam());
        game.getAwayTeam().setScore(command.awayTeam());
    }

    @Override
    public void finishGame(FinishGameCommand finishGameCommand) {
        Game game = findGameInProgressValidation(finishGameCommand);
        game.setStatus(GameStatus.FINISHED);
    }

    public List<Game> getGames() {
        return this.games;
    }

    public List<Game> getSummary() {
        return games.stream().filter(game -> game.getStatus().equals(GameStatus.STARTED)).sorted().collect(Collectors.toList());
    }

    private void updateCommandValidation(UpdateScoreCommand command) {
        Integer homeTeam = command.homeTeam();
        Integer awayTeam = command.awayTeam();
        if (!(homeTeam != null && homeTeam >= 0 && awayTeam != null && awayTeam >= 0)) {
            throw new ScoreboardException("UpdateCommand in incorrect state");
        }
    }

    private Game findGameInProgressValidation(BaseCommand command) {
        return games.stream()
                .filter(game -> game.getGameId().equals(command.getGameId()))
                .findFirst()
                .filter(game -> game.getStatus().equals(GameStatus.STARTED))
                .orElseThrow(() -> new ScoreboardException("Can not find game in progress with id: " + command.getGameId()));
    }
}
