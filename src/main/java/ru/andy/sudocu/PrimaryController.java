package ru.andy.sudocu;

import java.util.Random;

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
  private VBox vbox;
  private GridPane sudokuGrid;

  private static int SUDOKU_ITEMS_COUNT = 9; 
  private int[][] sudoku_items = new int[SUDOKU_ITEMS_COUNT][SUDOKU_ITEMS_COUNT];
  private int[][] sudoku_random_items = new int[SUDOKU_ITEMS_COUNT][SUDOKU_ITEMS_COUNT];
  private int[][] sudoku_items_with_empty;

  @FXML
  private void calculateSudoku() {
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
  }

  private void randomizeSudokuItems() {
    sudoku_random_items[0] = sudoku_items[2];
    sudoku_random_items[1] = sudoku_items[1];
    sudoku_random_items[2] = sudoku_items[0];
    sudoku_random_items[3] = sudoku_items[5];
    sudoku_random_items[4] = sudoku_items[4];
    sudoku_random_items[5] = sudoku_items[3];
    sudoku_random_items[6] = sudoku_items[8];
    sudoku_random_items[7] = sudoku_items[7];
    sudoku_random_items[8] = sudoku_items[6];
    //displayArray(sudoku_random_items);
    createSudokuItemsWithEmpty();
  }

  private void createSudokuItemsWithEmpty() {
    int emptyItems = 0;
    int maxEmptyItems = 45;

    sudoku_items_with_empty = new int[SUDOKU_ITEMS_COUNT][SUDOKU_ITEMS_COUNT];

    for (int i = 0; i < sudoku_random_items.length; i++ ) {
      int randomEmptyItemsCount = new Random().nextInt((6 - 3) + 1) + 3;
      for(int j = 0; j < sudoku_random_items[0].length; j++ ) {
        int randomEmptyItem = new Random().nextInt(2);
        if (emptyItems <= maxEmptyItems && randomEmptyItem == 1 && randomEmptyItemsCount > 0){
          sudoku_items_with_empty[i][j] = 0;
          randomEmptyItemsCount --;
          emptyItems++;
        } else {
          sudoku_items_with_empty[i][j] = sudoku_random_items[i][j];
        }
      }
    }
    //displayArray(sudoku_items_with_empty);
    //displaySudoku();
    drawLayout();
  }

  // Utility method to display the 2D array
  private static void displayArray(int[][] array) {
    for (int[] row : array) {
      for (int value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
    System.out.println('\n');
  }

  //private void displaySudoku () {
    //drawLayout();
  //}

  private void drawLayout () {
    if(hbox == null) {
      hbox = new HBox();
      hbox.setId("hBox");
      hbox.setPrefSize(400, 600); 
      hbox.setAlignment(Pos.TOP_LEFT);
    }

    if(vbox == null) {
      vbox = new VBox();
      vbox.setId("vBox");
      vbox.setPrefSize(100, 600);
      Button addButton = new Button("Решить");
      vbox.getChildren().add(addButton);

      hbox.getChildren().add(vbox);
      primaryVBox.getChildren().add(hbox);
    }
    drawSudokuGrid();
  }

  private void drawSudokuGrid () {
    if(sudokuGrid == null) {
      sudokuGrid = new GridPane();
      sudokuGrid.setId("sudokuGrid");
      sudokuGrid.setHgap(1);
      sudokuGrid.setVgap(1);
      hbox.getChildren().add(sudokuGrid);
    }

    sudokuGrid.setGridLinesVisible(false);
    sudokuGrid.getColumnConstraints().clear();
    sudokuGrid.getRowConstraints().clear();
    sudokuGrid.getChildren().clear();
    sudokuGrid.setGridLinesVisible(true);
      
    int rowIndex = 0;
    int columnIndex = 0;
    drawSudokuItems(0,1, columnIndex, rowIndex, Color.AQUA);

    rowIndex = 3;
    columnIndex = 0;
    drawSudokuItems(1,2, columnIndex, rowIndex, Color.ORANGE);

    rowIndex = 6;
    columnIndex = 0;
    drawSudokuItems(2,3, columnIndex, rowIndex, Color.AQUA);

    rowIndex = 0;
    columnIndex = 3;
    drawSudokuItems(3,4, columnIndex, rowIndex, Color.ORANGE);

    rowIndex = 3;
    columnIndex = 3;
    drawSudokuItems(4,5, columnIndex, rowIndex, Color.AQUA);

    rowIndex = 6;
    columnIndex = 3;
    drawSudokuItems(5,6, columnIndex, rowIndex, Color.ORANGE);

    rowIndex = 0;
    columnIndex = 6;
    drawSudokuItems(6,7,columnIndex, rowIndex, Color.AQUA);

    rowIndex = 3;
    columnIndex = 6;
    drawSudokuItems(7,8, columnIndex, rowIndex, Color.ORANGE);

    rowIndex = 6;
    columnIndex = 6;
    drawSudokuItems(8,9, columnIndex, rowIndex, Color.AQUA);
  }

  private void drawSudokuItems (int i0, int i1, int columnIndex, int rowIndex, Color bgColor) {
    int initialColumnIndex = columnIndex;

    for (int i = i0; i < i1; i++ ) {
      for(int j = 0; j < SUDOKU_ITEMS_COUNT; j++ ) {
        sudokuGrid.add(getSudocuItem(sudoku_items_with_empty[i][j], bgColor), columnIndex, rowIndex);
        columnIndex++;
        if(j == 2  || j == 5){
          columnIndex = initialColumnIndex;
          rowIndex++;
        }
      }
    }
  }

  private StackPane getSudocuItem(int sudokuValue, Color bgColor) {
    String text = "";
    if(sudokuValue > 0) {
      text  = Integer.toString(sudokuValue);
    }
    Text sudokuItemTextValue = new Text(text);
    Font font = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 24);
    sudokuItemTextValue.setFont(font);

    Rectangle sudokuItemRectangle = new Rectangle(50, 50);
    sudokuItemRectangle.setFill(bgColor);
    sudokuItemRectangle.setStroke(Color.BLACK);
    sudokuItemRectangle.setStrokeWidth(1);

    StackPane sudokuItem = new StackPane();
    sudokuItem.getChildren().addAll(sudokuItemRectangle, sudokuItemTextValue);
    return sudokuItem;
  }
}
