import java.util.Scanner; // DEL

public class Test {
  
  public static void main(String[] args){
    Scanner kb = new Scanner(System.in); // DEL
      Minesweeper m = new Minesweeper(5, 5); // Minesweeper instance: board dimension 10x10
      Cell[][] ms = m.gameBoard(10, 13); // initialize the board with number of mines between 8 and 15
    m.setAllContents(ms); // set contents of each cell
    m.printBoard(ms, false); // print the initial board
    m.play(ms); // play the game

  }
}
