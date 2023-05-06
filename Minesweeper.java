/*  Assignment: PA4 Minesweeper
    Minesweeper.java
    Name: Genevieve Britten and Amjad LASTNAME (group work)
    Due May 8, 2023
*/


import java.util.Random;
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

    public void countAdjacentMines(Cell[][] a) {               // how does this method work if it is void?
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {

                for (int k = (i - 1); i <= (i + 1); k++) {
                    for (int p = (j - 1); j <= (j + 1); p++) {
                        
                        if (i >= 0 && i <= dimX - 1) {
                            if (j >= 0 && j <= dimY - 1) {
                                if (a[k][p].getHasMine() == true) {
                                    a[k][p].setNumMines(a[k][p].getNumMines() + 1);
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
    }

    public void printBoard(Cell[][] a, boolean m) {         // later on we will need to separate each cell with a tab and label lines and columns
        
        if (m == false) {
            for (int i = 0; i < dimX; i++) {
                for (int j = 0; j < dimY; j++) {
                    
                    System.out.print(a[i][j].getContent());
                }
                System.out.println();
            }
        
        // if the user chose a mine, reveal all mines
        } else {
            for (int i = 0; i < dimX; i++) {
                for (int j = 0; j < dimY; j++) {
                    if (a[i][j].getHasMine() == true) {
                        a[i][j].setIsRevealed(true);
                    }
                    System.out.print(a[i][j].getContent());
                }
                System.out.println();
            }
        }
    }

    public boolean checkWinner(Cell[][] a) {
        boolean check = false;

        outerloop:      // this label should allow us to break from both loops at once as soon as we have proved there is not a winner
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                if (a[i][j].getIsRevealed() == false && a[i][j].getHasMine == false) {  // if there's a cell that isn't revealed and isn't a mine, no winner yet
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
        // PROCEDURE below
        
        // ask the user for row and column number
        // if they enter an incorrect cell, ask them again
    
        // if they stepped on a mine - the game is over 
        // print the board
    
        // if they stepped on somewhere else
        // print the board
        
        /* Display a game board, see
        Start timer
        Ask the user for row and column number to reveal at
        Starting second round, first ask if user wants to flag a cell, and if so which cell to flag. Then ask the user to select a cell to reveal.
        If they enter an incorrect cell, ask them again
        If they stepped on a mine - the game is over
            Print the board
        If they stepped on somewhere else
            Print the board
        Update the number of steps and number of mines unrevealed
        Stop timer when all mines are revealed or the game is lost */
    }


}
