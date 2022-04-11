package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class View implements FXComponent {
  private final Controller controller;

  public View(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane view = new GridPane();

    // Puzzle grid and clues
    PuzzleView puzzleView = new PuzzleView(controller);
    view.getChildren().add(puzzleView.render());

    // Puzzle controls
    PuzzleControls puzzleControls = new PuzzleControls(controller);
    view.add(puzzleControls.render(), 0, 1);

    // Success message upon completion
    MessageView messageView = new MessageView(controller);
    view.add(messageView.render(), 1, 0);

    view.setVgap(7.0);
    view.setHgap(7.0);
    view.setAlignment(Pos.CENTER);
    return view;
  }
}
