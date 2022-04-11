package com.comp301.a08nonograms.model;

public class Cell {

  private boolean isShaded, isEliminated, isSpace;

  public Cell() {
    isShaded = false;
    isEliminated = false;
    isSpace = true;
  }

  public boolean isShaded() {
    return isShaded;
  }

  public boolean isEliminated() {
    return isEliminated;
  }

  public boolean isSpace() {
    return isSpace;
  }

  public void shade() {
    isShaded = !isShaded;
    isEliminated = false;
    isSpace = !isShaded;
  }

  public void eliminate() {
    isEliminated = !isEliminated;
    isShaded = false;
    isSpace = !isEliminated;
  }

  public void clearCell() {
    isShaded = false;
    isEliminated = false;
    isSpace = true;
  }
}
