package com.comp301.a08nonograms.model;

public class Puzzle {

  private Clues clues;
  private Board board;

  public Puzzle(Clues clues, Board board) {
    this.clues = clues;
    this.board = board;
  }

  public Clues getClues() {
    return clues;
  }

  public Board getBoard() {
    return board;
  }
}
