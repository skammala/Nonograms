package com.comp301.a08nonograms.model;

public class BoardImpl implements Board {

  private Cell[][] puzzleBoard;

  public BoardImpl(int puzzleHeight, int puzzleWidth) {
    puzzleBoard = new Cell[puzzleHeight][puzzleWidth];
    for (int row = 0; row < puzzleBoard.length; row++) {
      for (int col = 0; col < puzzleBoard[0].length; col++) {
        puzzleBoard[row][col] = new Cell();
      }
    }
  }

  @Override
  public boolean isShaded(int row, int col) {
    return puzzleBoard[row][col].isShaded();
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return puzzleBoard[row][col].isEliminated();
  }

  @Override
  public boolean isSpace(int row, int col) {
    return puzzleBoard[row][col].isSpace();
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    puzzleBoard[row][col].shade();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    puzzleBoard[row][col].eliminate();
  }

  @Override
  public void clear() {
    for (Cell[] row : puzzleBoard) {
      for (Cell c : row) {
        c.clearCell();
      }
    }
  }
}
