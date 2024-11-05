package ru.andy.sudocu;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {
    private static int SUDOKU_ITEMS_COUNT = 9;
    private int sudoku_items_iteration = 1;  
    private int[][] sudoku_items = new int[9][9];

    @FXML
    private void calculateSudoku() {
        System.out.println("hello");
        for (int i = 0; i < sudoku_items.length; i++ ) {
            //System.out.println("i:" + i);
            for(int j = 0; j < sudoku_items[0].length; j++ ) {
                //System.out.println("j:" + j);
                sudoku_items[i][j] = i+j+1;
               if(sudoku_items[i][j] > SUDOKU_ITEMS_COUNT){
                sudoku_items[i][j] = sudoku_items[i][j] - SUDOKU_ITEMS_COUNT;
               }
            }
        }
        displayArray(sudoku_items);
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

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
