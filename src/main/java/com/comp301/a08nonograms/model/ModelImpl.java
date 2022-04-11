package com.comp301.a08nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private ArrayList<Puzzle> puzzleLib;
  private ArrayList<ModelObserver> observers;
  private int currentPuzzleIdx;

  public ModelImpl(List<Clues> clues) {
    puzzleLib = new ArrayList<Puzzle>();
    for (int i = 0; i < clues.size(); i++) {
      puzzleLib.add(
          new Puzzle(
              clues.get(i), new BoardImpl(clues.get(i).getHeight(), clues.get(i).getWidth())));
    }
    observers = new ArrayList<ModelObserver>();
    currentPuzzleIdx = 0;
  }

  @Override
  public int getPuzzleCount() {
    return puzzleLib.size();
  }

  @Override
  public int getPuzzleIndex() {
    return currentPuzzleIdx;
  }

  @Override
  public void setPuzzleIndex(int index) {
    currentPuzzleIdx = index;
    notifyObservers();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  @Override
  public boolean isSolved() {
    int numShaded, clueSum;
    boolean solved = true;
    int[] clues;

    for (int row = 0; row < getHeight(); row++) {
      numShaded = 0;
      clueSum = 0;
      clues = puzzleLib.get(currentPuzzleIdx).getClues().getRowClues(row);
      for (int clue : clues) {
        clueSum += clue;
      }
      for (int col = 0; col < getWidth(); col++) {
        if (isShaded(row, col)) {
          numShaded++;
        }
      }
      if (numShaded != clueSum) {
        solved = false;
      }
    }

    for (int col = 0; col < getWidth(); col++) {
      numShaded = 0;
      clueSum = 0;
      clues = puzzleLib.get(currentPuzzleIdx).getClues().getColClues(col);
      for (int clue : clues) {
        clueSum += clue;
      }
      for (int row = 0; row < getHeight(); row++) {
        if (isShaded(row, col)) {
          numShaded++;
        }
      }
      if (numShaded != clueSum) {
        solved = false;
      }
    }

    return solved;
  }

  @Override
  public boolean isShaded(int row, int col) {
    checkCoordinates(row, col);
    return puzzleLib.get(currentPuzzleIdx).getBoard().isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    checkCoordinates(row, col);
    return puzzleLib.get(currentPuzzleIdx).getBoard().isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    checkCoordinates(row, col);
    return puzzleLib.get(currentPuzzleIdx).getBoard().isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    checkCoordinates(row, col);
    puzzleLib.get(currentPuzzleIdx).getBoard().toggleCellShaded(row, col);
    notifyObservers();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    checkCoordinates(row, col);
    puzzleLib.get(currentPuzzleIdx).getBoard().toggleCellEliminated(row, col);
    notifyObservers();
  }

  @Override
  public void clear() {
    puzzleLib.get(currentPuzzleIdx).getBoard().clear();
    notifyObservers();
  }

  @Override
  public int getWidth() {
    return puzzleLib.get(currentPuzzleIdx).getClues().getWidth();
  }

  @Override
  public int getHeight() {
    return puzzleLib.get(currentPuzzleIdx).getClues().getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return puzzleLib.get(currentPuzzleIdx).getClues().getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return puzzleLib.get(currentPuzzleIdx).getClues().getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return puzzleLib.get(currentPuzzleIdx).getClues().getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return puzzleLib.get(currentPuzzleIdx).getClues().getColCluesLength();
  }

  public Puzzle getPuzzle() {
    return puzzleLib.get(currentPuzzleIdx);
  }

  private void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  private void checkCoordinates(int row, int col) {
    if (row < 0 || row >= puzzleLib.get(currentPuzzleIdx).getClues().getHeight()) {
      throw new RuntimeException();
    } else if (col < 0 || col >= puzzleLib.get(currentPuzzleIdx).getClues().getWidth()) {
      throw new RuntimeException();
    }
  }
}
