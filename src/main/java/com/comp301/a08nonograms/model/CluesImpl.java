package com.comp301.a08nonograms.model;

public class CluesImpl implements Clues {

  private int[][] _rowClues;
  private int[][] _colClues;
  private int _puzzleHeight;
  private int _puzzleWidth;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    if (rowClues == null || colClues == null) {
      throw new IllegalArgumentException();
    }
    _rowClues = rowClues;
    _colClues = colClues;
    _puzzleHeight = rowClues.length;
    _puzzleWidth = colClues.length;
  }

  @Override
  public int getWidth() {
    return _puzzleWidth;
  }

  @Override
  public int getHeight() {
    return _puzzleHeight;
  }

  @Override
  public int[] getRowClues(int index) {
    return _rowClues[index];
  }

  @Override
  public int[] getColClues(int index) {
    return _colClues[index];
  }

  @Override
  public int getRowCluesLength() {
    return _rowClues[0].length;
  }

  @Override
  public int getColCluesLength() {
    return _colClues[0].length;
  }
}
