package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Stack;

public class MessageView implements FXComponent {
  private final Controller controller;

  public MessageView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane messages = new GridPane();

    StackPane gameTitle = new StackPane();
    Rectangle rect = new Rectangle(160, 60);
    rect.setFill(Color.LIGHTBLUE);
    rect.setStroke(Color.BLACK);
    Text title = new Text("Nonograms!");
    title.setFont(Font.font("verdana", 25));
    gameTitle.getChildren().addAll(rect, title);
    messages.add(gameTitle, 0, 0);

    StackPane puzzleNumber = new StackPane();
    Text puzzleCount = new Text("Puzzle " + (controller.getPuzzleIndex() + 1) + " of 5");
    puzzleCount.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
    puzzleNumber.getChildren().add(puzzleCount);
    messages.add(puzzleNumber, 0, 1);

    StackPane wonMessage = new StackPane();
    Text success = new Text("Success!");
    success.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
    success.setFill(Color.GREEN);
    if (controller.isSolved()) {
      success.setVisible(true);
    } else {
      success.setVisible(false);
    }
    wonMessage.getChildren().add(success);
    messages.add(wonMessage, 0, 2);

    messages.setAlignment(Pos.CENTER);
    messages.setVgap(3.0);

    return messages;
  }
}
