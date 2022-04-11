package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.PuzzleLibrary;
import com.comp301.a08nonograms.controller.ControllerImpl;
import com.comp301.a08nonograms.model.Model;
import com.comp301.a08nonograms.model.ModelImpl;
import com.comp301.a08nonograms.model.ModelObserver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {

  @Override
  public void start(Stage stage) {
    ModelImpl model = new ModelImpl(PuzzleLibrary.create());
    ControllerImpl controller = new ControllerImpl(model);

    View view = new View(controller);
    stage.setScene(new Scene(view.render()));

    model.addObserver(
        (Model m) -> {
          stage.setScene(new Scene(view.render()));
        });

    // Show the stage
    stage.setTitle("Nonograms!");
    stage.show();
  }
}
