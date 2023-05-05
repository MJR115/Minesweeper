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
                int randCol = rand.nextInt(dimX);
                int randRows = rand.nextInt(dimY);
                if (cell[randRows][randCol].getHasMine() == false) {
                    cell[randRows][randCol].setHasMine(true);
                    check = true;
                }
            }

        }
        return cell;
    }

    public void countAdjacentMines(Cell[][] a) {
        int numMines = 0;
        int colum = 0;
        int row = 0;
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {

                for (int k = (i - 1); i <= (i + 1); k++) {
                    for (int p = (j - 1); j <= (j + 1); p++) {
                        if (i >= 0 && i <= dimX - 1) {
                            if (j >= 0 && j <= dimY - 1) {
                                if (a[k][p].getHasMine() == true)
                                    a[k][p].setNumMines(a[k][p].getNumMines() + 1);
                            }
                        }
                    }
                }
            }
        }

    }

    public void setAllContents(Cell[][] a) {
    }

    public void printBoard(Cell[][] a, boolean m) {

        if (m == false) {
            for (int i = 0; i < dimX; i++) {
                for (int j = 0; j < dimY; j++) {

                    if (a[i][j].getIsFlagged() == true) {
                        System.out.print("F");
                    } else if (a[i][j].getIsRevealed() == false) {
                        System.out.print("#");
                    } else {
                        System.out.print(a[i][j].getNumMines());
                    }
                }
            }
        } else {
            for (int i = 0; i < dimX; i++) {
                for (int j = 0; j < dimY; j++) {
                    if (a[i][j].getHasMine() == true) {
                        System.out.print("*");
                    } else {
                        System.out.print(a[i][j].getNumMines());
                    }
                }
            }
        }
    }

    public boolean checkWinner(Cell[][] a) {
        boolean check = false;



        return check;
    }


}
