package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PuzzleControls implements FXComponent {
  private final Controller controller;

  public PuzzleControls(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox controls = new HBox();
    Button nextPuzzle = new Button("Next Puzzle \u21DB");
    nextPuzzle.setOnAction(
        (ActionEvent event) -> {
          controller.nextPuzzle();
        });
    nextPuzzle.setAlignment(Pos.CENTER_RIGHT);

    Button prevPuzzle = new Button("\u21DA Previous Puzzle");
    prevPuzzle.setOnAction(
        (ActionEvent event) -> {
          controller.prevPuzzle();
        });
    prevPuzzle.setAlignment(Pos.CENTER_LEFT);

    Button randomize = new Button("Randomize");
    randomize.setOnAction(
        (ActionEvent event) -> {
          controller.randPuzzle();
        });
    randomize.setAlignment(Pos.CENTER);

    Button reset = new Button("Reset");
    reset.setOnAction(
        (ActionEvent event) -> {
          controller.clearBoard();
        });

    controls.setSpacing(18.0);
    controls.setAlignment(Pos.BASELINE_CENTER);
    controls.getChildren().addAll(prevPuzzle, randomize, nextPuzzle, reset);
    HBox.setMargin(prevPuzzle, new Insets(5));
    HBox.setMargin(randomize, new Insets(5));
    HBox.setMargin(nextPuzzle, new Insets(5));
    return controls;
  }
}
