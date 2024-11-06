package ru.andy.sudocu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.Pos; 

public class PrimaryController {

  @FXML
  private VBox primaryVBox;
  private HBox hbox;
  private GridPane sudokuGrid;

  private static int SUDOKU_ITEMS_COUNT = 9; 
  private int[][] sudoku_items = new int[SUDOKU_ITEMS_COUNT][SUDOKU_ITEMS_COUNT];

  @FXML
  private void calculateSudoku() {
    System.out.println("hello");
    for (int i = 0; i < sudoku_items.length; i++ ) {
      for(int j = 0; j < sudoku_items[0].length; j++ ) {
        sudoku_items[i][j] = i+j+1;
        if(sudoku_items[i][j] > SUDOKU_ITEMS_COUNT){
          sudoku_items[i][j] = sudoku_items[i][j] - SUDOKU_ITEMS_COUNT;
        }
      }
    }
    // displayArray(sudoku_items);
    displaySudoku();
  }

  // Utility method to display the 2D array
  private static void displayArray(int[][] array) {
    for (int[] row : array) {
      for (int value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
  }

  private void displaySudoku () {
    if(hbox == null){
      drawLayout ();
    }
  }

  private void drawLayout () {
      hbox = new HBox();
      hbox.setId("hBox");
      hbox.setPrefSize(200, 500); 
      hbox.setAlignment(Pos.TOP_LEFT);
      Button addButton = new Button("START GAME");

      hbox.getChildren().add(addButton);
      primaryVBox.getChildren().add(hbox);
      drawSudokuGrid();
  }

  private void drawSudokuGrid () {
    if(sudokuGrid == null) {
      sudokuGrid = new GridPane();
      sudokuGrid.setGridLinesVisible(true);
      sudokuGrid.setId("sudokuGrid");
      sudokuGrid.setHgap(1);
      sudokuGrid.setVgap(1);

      int rowIndex = 0;
      int columnIndex = 0;

      for (int i = 0; i < 3; i++ ) {
        for(int j = 0; j < 3; j++ ) {
          sudokuGrid.add(getSudocuItem(sudoku_items[i][j]), rowIndex, columnIndex);
          rowIndex++;
        }
      }

      rowIndex = 0;
      columnIndex = 1;
      for (int i = 0; i < 3; i++ ) {
        for(int j = 3; j < 6; j++ ) {
          sudokuGrid.add(getSudocuItem(sudoku_items[i][j]), rowIndex, columnIndex);
          rowIndex++;
        }
      }

      hbox.getChildren().add(sudokuGrid);
    }
  }


  private StackPane getSudocuItem(int sudokuValue) {
    Text sudokuItemTextValue = new Text(Integer.toString(sudokuValue));
    Font font = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 24);
    sudokuItemTextValue.setFont(font);

    Rectangle sudokuItemRectangle = new Rectangle(50, 50);
    sudokuItemRectangle.setFill(Color.AQUA);
    sudokuItemRectangle.setStroke(Color.BLACK);
    sudokuItemRectangle.setStrokeWidth(1);

    StackPane sudokuItem = new StackPane();
    sudokuItem.getChildren().addAll(sudokuItemRectangle, sudokuItemTextValue);
    return sudokuItem;
  }
}
