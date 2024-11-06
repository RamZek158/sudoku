package ru.andy.sudocu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos; 

public class PrimaryController {

  @FXML
  private VBox primaryVBox;
  private HBox hbox;

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
      displayArray(sudoku_items);
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
    drawLayout ();
  }

  private void drawLayout () {
      hbox = new HBox();
      hbox.setId("hBox");
      hbox.setPrefSize(200, 500); 
      hbox.setAlignment(Pos.TOP_LEFT);
      Button addButton = new Button("START GAME");

      hbox.getChildren().add(addButton);
      primaryVBox.getChildren().add(hbox);
  }
}
