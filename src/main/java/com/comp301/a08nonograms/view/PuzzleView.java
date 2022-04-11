package com.comp301.a08nonograms.view;

import com.comp301.a08nonograms.controller.Controller;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;

public class PuzzleView implements FXComponent {
  private final Controller controller;

  public PuzzleView(Controller controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane puzzleGrid = new GridPane();
    puzzleGrid.setHgap(0.5);
    puzzleGrid.setVgap(0.5);
    puzzleGrid.setGridLinesVisible(true);
    final int puzzleHeight = controller.getClues().getHeight();
    final int puzzleWidth = controller.getClues().getWidth();
    for (int col = 0; col < puzzleWidth; col++) {
      ColumnConstraints newCol = new ColumnConstraints();
      newCol.setPercentWidth(100.0 / puzzleWidth);
      puzzleGrid.getColumnConstraints().add(newCol);
    }
    for (int row = 0; row < puzzleHeight; row++) {
      RowConstraints newRow = new RowConstraints();
      newRow.setPercentHeight(100.0 / puzzleHeight);
      puzzleGrid.getRowConstraints().add(newRow);
    }

    Color blankColor = Color.GHOSTWHITE;

    for (int row = 0; row < puzzleHeight; row++) {
      for (int col = 0; col < puzzleWidth; col++) {
        int colIdx = col;
        int rowIdx = row;
        Rectangle rect = new Rectangle(30, 30);
        StackPane pane = new StackPane();
        GridPane.setVgrow(pane, Priority.ALWAYS);
        GridPane.setHgrow(pane, Priority.ALWAYS);
        pane.getChildren().add(rect);
        Text text = new Text("\u274C");
        text.setVisible(false);
        pane.getChildren().add(text);
        rect.setFill(blankColor);
        rect.setStroke(Color.BLACK);
        rect.setStrokeLineCap((StrokeLineCap.ROUND));

        rect.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                  controller.toggleShaded(rowIdx, colIdx);
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                  controller.toggleEliminated(rowIdx, colIdx);
                }
              }
            });
        text.setOnMouseClicked(rect.getOnMouseClicked());

        if (controller.isShaded(row, col)) {
          text.setVisible(false);
          rect.setFill(Color.BLACK);
        } else if (controller.isEliminated(row, col)) {
          rect.setFill(blankColor);
          text.setVisible(true);
        } else {
          text.setVisible(false);
          rect.setFill(blankColor);
        }
        puzzleGrid.addRow(row, pane);
      }
    }

    puzzleGrid.setMaxSize(300, 300);

    GridPane rowClues = new GridPane();
    rowClues.setHgap(0.5);
    rowClues.setVgap(0.5);
    rowClues.setGridLinesVisible(true);
    for (int row = 0; row < puzzleHeight; row++) {
      rowClues.getRowConstraints().add(puzzleGrid.getRowConstraints().get(row));
      for (int i = 0; i < controller.getClues().getRowCluesLength(); i++) {
        StackPane pane = new StackPane();
        Rectangle rect = new Rectangle(30, 30);
        if (controller.getClues().getRowClues(row)[i] == 0) {
          rect.setFill(Color.LIGHTGRAY);
          rect.setStroke(Color.BLACK);
          rect.setStrokeLineCap(StrokeLineCap.ROUND);
          pane.getChildren().add(rect);
        } else {
          rect.setFill(Color.WHITE);
          rect.setStroke(Color.BLACK);
          rect.setStrokeLineCap(StrokeLineCap.ROUND);
          Text clue = new Text("" + controller.getClues().getRowClues(row)[i]);
          pane.getChildren().addAll(rect, clue);
        }
        rowClues.addRow(row, pane);
      }
    }

    GridPane colClues = new GridPane();
    colClues.setHgap(0.5);
    colClues.setVgap(0.5);
    colClues.setGridLinesVisible(true);
    for (int col = 0; col < puzzleWidth; col++) {
      for (int i = 0; i < controller.getClues().getColCluesLength(); i++) {
        StackPane pane = new StackPane();
        Rectangle rect = new Rectangle(30, 30);
        if (controller.getClues().getColClues(col)[i] == 0) {
          rect.setFill(Color.LIGHTGRAY);
          rect.setStroke(Color.BLACK);
          rect.setStrokeLineCap(StrokeLineCap.ROUND);
          pane.getChildren().add(rect);
        } else {
          rect.setFill(Color.WHITE);
          rect.setStroke(Color.BLACK);
          rect.setStrokeLineCap((StrokeLineCap.ROUND));
          Text clue = new Text("" + controller.getClues().getColClues(col)[i]);
          pane.getChildren().addAll(rect, clue);
        }
        colClues.addColumn(col, pane);
      }
    }
    rowClues.setMaxSize(300, 300);
    colClues.setMaxSize(300, 300);

    HBox rows = new HBox(3.0, rowClues, puzzleGrid);
    rows.setAlignment(Pos.CENTER);
    colClues.setAlignment(Pos.CENTER_RIGHT);

    GridPane parent = new GridPane();
    parent.setVgap(3.0);
    parent.addRow(1, rows);
    parent.addRow(0, colClues);
    GridPane.setConstraints(colClues, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
    parent.setAlignment(Pos.CENTER);
    return parent;
  }
}
