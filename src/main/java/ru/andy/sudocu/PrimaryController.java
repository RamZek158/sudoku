package ru.andy.sudocu;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
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

public class PrimaryController {

  @FXML
  private VBox primaryVBox;
  private HBox hbox;
  private GridPane sudokuGrid;

  private static int SUDOKU_ITEMS_COUNT = 9; 
  private int[][] sudoku_items = new int[SUDOKU_ITEMS_COUNT][SUDOKU_ITEMS_COUNT];
  private int [][] sudoku_random_items = new int[SUDOKU_ITEMS_COUNT][SUDOKU_ITEMS_COUNT];

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
    randomizeSudokuItems();
    // displayArray(sudoku_items);
    displaySudoku();
  }

  private void randomizeSudokuItems() {
    sudoku_random_items[0] = sudoku_items[3];
    sudoku_random_items[1] = sudoku_items[2];
    sudoku_random_items[2] = sudoku_items[4];
    sudoku_random_items[3] = sudoku_items[1];
    sudoku_random_items[4] = sudoku_items[5];
    sudoku_random_items[5] = sudoku_items[8];
    sudoku_random_items[6] = sudoku_items[7];
    sudoku_random_items[7] = sudoku_items[6];
    sudoku_random_items[8] = sudoku_items[0];
    displayArray(sudoku_random_items);
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
      hbox.setPrefSize(500, 600); 
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

      /*for (int i = 0; i < 1; i++ ) {
        for(int j = 0; j < SUDOKU_ITEMS_COUNT; j++ ) {
          sudokuGrid.add(getSudocuItem(sudoku_items[i][j]), columnIndex, rowIndex);
          columnIndex++;
          if(j == 2  || j == 5){
            columnIndex = 0;
            rowIndex++;
          }
        }
      }*/

      drawSudokuItems(0,1, columnIndex, rowIndex);

      rowIndex = 3;
      columnIndex = 0;

      drawSudokuItems(1,2, columnIndex, rowIndex);
      /*for (int i = 1; i < 2; i++ ) {
        for(int j = 0; j < SUDOKU_ITEMS_COUNT; j++ ) {
          sudokuGrid.add(getSudocuItem(sudoku_items[i][j]), columnIndex, rowIndex);
          columnIndex++;
          if(j == 2  || j == 5){
            columnIndex = 0;
            rowIndex++;
          }
        }
      }*/

      rowIndex = 6;
      columnIndex = 0;
      drawSudokuItems(2,3, columnIndex, rowIndex);
      /*for (int i = 2; i < 3; i++ ) {
        for(int j = 0; j < SUDOKU_ITEMS_COUNT; j++ ) {
          sudokuGrid.add(getSudocuItem(sudoku_items[i][j]), columnIndex, rowIndex);
          columnIndex++;
          if(j == 2  || j == 5){
            columnIndex = 0;
            rowIndex++;
          }
        }
      }*/

      rowIndex = 0;
      columnIndex = 3;
      drawSudokuItems(3,4, columnIndex, rowIndex);

      rowIndex = 3;
      columnIndex = 3;
      drawSudokuItems(4,5, columnIndex, rowIndex);

      rowIndex = 6;
      columnIndex = 3;
      drawSudokuItems(5,6, columnIndex, rowIndex);

      rowIndex = 0;
      columnIndex = 6;
      drawSudokuItems(6,7,columnIndex, rowIndex);

      rowIndex = 3;
      columnIndex = 6;
      drawSudokuItems(7,8, columnIndex, rowIndex);

      rowIndex = 6;
      columnIndex = 6;
      drawSudokuItems(8,9, columnIndex, rowIndex);

      hbox.getChildren().add(sudokuGrid);
    }
  }

  private void drawSudokuItems (int i0, int i1, int columnIndex, int rowIndex) {
    int initialColumnIndex = columnIndex;

    for (int i = i0; i < i1; i++ ) {
      for(int j = 0; j < SUDOKU_ITEMS_COUNT; j++ ) {
        sudokuGrid.add(getSudocuItem(sudoku_items[i][j]), columnIndex, rowIndex);
        columnIndex++;
        if(j == 2  || j == 5){
          columnIndex = initialColumnIndex;
          rowIndex++;
        }
      }
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
