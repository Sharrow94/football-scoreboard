package com.exercise.football.score.board.exception;

import java.io.Serial;

public class ScoreboardException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1534479601697446899L;

    public ScoreboardException(String message) {
        super(message);
    }

    public ScoreboardException(String message, Throwable cause) {
        super(message, cause);
    }
}
