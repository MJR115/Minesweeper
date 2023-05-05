import java.util.Random;

public class Minesweeper {

    public int dimX;
    public int dimY;


    public char[ ][ ] gameBoard(int x, int y) {

        char[][] cell = new char[8][8];

        Random rand = new Random();
        int randMines = rand.nextInt(y - x + 1) + x;


        for (int i = 0; i < randMines; i++) {
            boolean check = false;
            while(check == false) {
                int randCol = rand.nextInt(8);
                int randRows = rand.nextInt(8);
                if(cell[randRows][randCol] != '*') {
                    cell[randRows][randCol] = '*';
                    check = true;
                }
            }

        }
        return cell;
    }

    public void countAdjacentMines(char[][] a) {
        int numMines = 0;
        int colum = 0;
        int row = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++){
                if (a[i][j] == '+'){
                    row = i;
                    colum = j;
                }
            }
        }

        for (int i = (row - 1); i <= (row + 1); i ++) {
            for (int j = (colum - 1); j <= (colum + 1); j ++){
                if (i >= 0 && i <= 7 ){
                    if (j >= 0 && j <= 7) {
                        if (a[i][j] == '*')
                            numMines += 1;
                    }
                }
            }
        }




    }

    public void setAllContents(char[][] a) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; i < 8; j++) {

                //if(a[i][j] == '')
            }
        }
    }




}
