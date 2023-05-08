/*  Assignment: PA4 Minesweeper
    Minesweeper.java
    Name: Genevieve Britten and Amjad Rabaan (group work)
    Due May 8, 2023
*/

import java.util.Scanner;
import java.util.Random;
import java.lang.*;
import java.io.IOException;

public class Minesweeper {

    public int dimX;
    public int dimY;

    public Minesweeper(int x, int y) {

        dimX = x;
        dimY = y;

    }

    public Cell[][] gameBoard(int x, int y) {

        Cell[][] cell = new Cell[dimX][dimY];
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {

                cell[i][j] = new Cell();
            }
        }
        Random rand = new Random();
        int randMines = rand.nextInt(y - x + 1) + x;


        for (int i = 0; i < randMines; i++) {
            boolean check = false;
            while (check == false) {
                int randRows = rand.nextInt(dimX);
                int randCol = rand.nextInt(dimY);
                if (cell[randRows][randCol].getHasMine() == false) {
                    cell[randRows][randCol].setHasMine(true);
                    check = true;
                }
            }

        }
        return cell;
    }

    public void countAdjacentMines(Cell[][] a) {               
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {

                for (int k = (i - 1); k <= (i + 1); k++) {
                    for (int p = (j - 1); p <= (j + 1); p++) {
                        
                        if (k >= 0 && k < dimX) {
                            if (p >= 0 && p < dimY) {
                                if (a[k][p].getHasMine() == true) {
                               
                                    a[i][j].setNumMines(a[i][j].getNumMines() + 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void setAllContents(Cell[][] a) {
       
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                
                if (a[i][j].getIsFlagged() == true) {
                    a[i][j].setContent('F');
                } else if (a[i][j].getIsRevealed() == false) {
                    a[i][j].setContent('#');
                } else if (a[i][j].getHasMine() == true) {
                    a[i][j].setContent('*');
                } else {
                    char numMines = (char) (a[i][j].getNumMines() + '0');   // this should convert the int numMines to a char
                    a[i][j].setContent(numMines);
                }
            }   
        }
    }

    public void printBoard(Cell[][] a, boolean m) {         
        
        setAllContents(a);
        
        if (m == false) {
            for (int i = 0; i < dimX; i++) {

                if (i == 0)
                    System.out.println("  Lines");

                for (int j = 0; j < dimY; j++) {
                    if (j == 0) {
                        System.out.print("\t" + (i + 1) + "\t");
                    }
                    System.out.print(a[i][j].getContent() + "\t");
                }
                System.out.println();
            }
            System.out.print("\t");
            for (int k = 1; k <= dimX; k++) {
                System.out.print("\t" + k);
            }
            System.out.println();

            for (int l = 0; l <= (dimX / 2); l++) {
                System.out.print("\t");
            }
            System.out.print("Columns");
            System.out.println();
        }
        // if the user chose a mine, reveal all mines
        else {
            for (int i = 0; i < dimX; i++) {

                if (i == 0)
                    System.out.println("  Lines");

                for (int j = 0; j < dimY; j++) {
                    if(j == 0){
                        System.out.print("\t" + (i + 1) + "\t");
                    }
                    if (a[i][j].getHasMine() == true){
                        System.out.print("*" + "\t");
                    }
                    else {
                        System.out.print(a[i][j].getContent() + "\t");
                    }
                }
                System.out.println();
            }
            System.out.print("\t");
            for (int k = 1; k <= dimX; k ++) {
                System.out.print("\t" + k);
            }
            System.out.println();

            for (int l = 0; l <= (dimX/2); l++){
                System.out.print("\t");
            }
            System.out.print("Columns");
            System.out.println();
        }
    }

    public boolean checkWinner(Cell[][] a) {
        boolean check = false;

        outerloop:      // this label allows us to break from both loops at once as soon as we have proved there is not a winner
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                if (a[i][j].getIsRevealed() == false && a[i][j].getHasMine() == false) {  // if there's a cell that isn't revealed and isn't a mine, no winner yet
                    check = false;
                    break outerloop;
                } else {
                    check = true;
                }
            }
        }

        return check;
    }

    public void play(Cell[][] a) {
        
        /* Display a game board, see
        Start timer
        Ask the user for row and column number to reveal at
        Starting second round, first ask if user wants to flag a cell, and if so which cell to flag. Then ask the user to select a cell to reveal.
            If they enter an incorrect cell, ask them again
        If they stepped on a mine - the game is over
            Print the board
        If they stepped on somewhere else
            Print the board
        Update the number of steps and number of mines unrevealed               // for the logic below I don't think we need to keep track of unrevealed mines?
        Stop timer when all mines are revealed or the game is lost */
        
        
        Scanner sc = new Scanner(System.in);
        int turn = 1;
        countAdjacentMines(a);
        
        for (; ;) {                                      // this loops indefinitely                           
            long start = System.currentTimeMillis();     // this is the start time
            boolean lost = false;
            int line;
            int column;
            while(true) {
                try {
                    System.out.println("Line: ");
                    line = sc.nextInt() - 1;                      // LATER: keep asking until user puts invalid input
                    System.out.println("Column: ");
                    column = sc.nextInt() - 1;
                    a[line][column].setIsRevealed(true);
                    System.out.println("Turn " + turn);
                    turn++;
                    revealNeighbors(line, column, a);
                    setAllContents(a);
                    break;
                }
                catch (java.util.InputMismatchException e) {
                    System.out.println("invalid input");
                    sc.nextLine();
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (a[line][column].getHasMine() == true) {
                System.out.println("Mine! You lost!");
                printBoard(a, true);
                lost = true;

            }
            while ((checkWinner(a) == false) && (lost == false)) {
                // LATER: optional flagging
                printBoard(a, false);
                while(true) {
                    try {
                        System.out.println("Line: ");
                        line = sc.nextInt() - 1;                  // LATER: keep asking if user puts invalid input
                        System.out.println("Column: ");
                        column = sc.nextInt() - 1;
                        a[line][column].setIsRevealed(true);
                        System.out.println("Turn " + turn);
                        turn++;
                        revealNeighbors(line, column, a);
                        setAllContents(a);
                        break;
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("invalid input");
                        sc.nextLine();
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                }

                if (a[line][column].getHasMine() == true) {
                    System.out.println("Mine! You lost!");
                    printBoard(a, true);
                    lost = true;
                }
            }
            
            if (checkWinner(a) == true) {
                System.out.println("Good job! You won!");
            }
        
            long end = System.currentTimeMillis();       // end time
            double time = end - start;                  // time in milliseconds
            int minutes = (int) (time / 60000);
            int seconds = (int) ((time / 1000) - (minutes * 60));
            System.out.printf("Time: " + minutes + " minutes " + seconds + " seconds");
            System.out.println();
            
            System.out.println("Press any key to continue...");
            String continueKey = sc.next();
            
            // FINISH - new array of cell objects - new game
            a = gameBoard(8, 15);
            countAdjacentMines(a);
            setAllContents(a);
            turn = 1;
            printBoard(a, false);
        }   
    }
    
    public void revealNeighbors(int i, int j, Cell[][] a){
        if (a[i][j].getNumMines() == 0) {
         a[i][j].setIsEmpty(true);
      } 
      if (a[i][j].getIsEmpty() == true) {
         for (int k = (i - 1); k <= (i + 1); k++) {
            for (int p = (j - 1); p <= (j + 1); p++) {
                        
               if (k >= 0 && k < dimX) {
                  if (p >= 0 && p < dimY) { 
                     a[k][p].setIsRevealed(true);
                     setAllContents(a);
                  }
               }
            }
         }
      }
    }


}
