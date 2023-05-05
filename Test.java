public class Test {

    public static void main(String[] args) {
        Minesweeper m = new Minesweeper(10, 10);
        Cell[][] ms = m.gameBoard(8, 15);
        m.countAdjacentMines(ms);
    }
}
