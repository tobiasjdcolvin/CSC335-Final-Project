package model;

public class Board {
    private boolean DEBUG = true;

    private final Word solution;
    private Word guess;

    public Board(Word solution) {
        this.solution = solution;
    }
}