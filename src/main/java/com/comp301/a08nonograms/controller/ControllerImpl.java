package com.comp301.a08nonograms.controller;

import com.comp301.a08nonograms.model.Clues;
import com.comp301.a08nonograms.model.ModelImpl;

public class ControllerImpl implements Controller {

  private ModelImpl model;

  public ControllerImpl(ModelImpl model) {
    if (model != null) {
      this.model = model;
    }
  }

  @Override
  public Clues getClues() {
    return model.getPuzzle().getClues();
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return model.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return model.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    model.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    model.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    if (model.getPuzzleIndex() < (model.getPuzzleCount() - 1)) {
      model.setPuzzleIndex(model.getPuzzleIndex() + 1);
    }
  }

  @Override
  public void prevPuzzle() {
    if (model.getPuzzleIndex() > 0) {
      model.setPuzzleIndex(model.getPuzzleIndex() - 1);
    }
  }

  @Override
  public void randPuzzle() {
    model.setPuzzleIndex(((int) (Math.random() * 100)) % model.getPuzzleCount());
  }

  @Override
  public void clearBoard() {
    model.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return model.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return model.getPuzzleCount();
  }
}
