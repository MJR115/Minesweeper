/*  Assignment: PA4 Minesweeper
    Cell.java
    Name: Genevieve Britten and Amjad Rabaan (group work)
    Due May 8, 2023
*/
    public class Cell {
        // instance variables
        private boolean hasMine;        // does the cell contain a mine?
        private boolean isEmpty;        // is the cell empty?
        private boolean isFlagged;      // OPTIONAL - is the cell flagged?
        private int numMines;           // how many mines surround the cell?
        private boolean isRevealed;     // has the cell been revealed to the user?
        private char content;           // what to print to the screen for the cell

        // constructors (at least 3): creates a cell and initializes instance variables
        public Cell() {
            hasMine = false;
            isEmpty = false;
            isFlagged = false;
            numMines = 0;
            isRevealed = false;
            content = '#';
        }
        
        // set methods
        public void setHasMine(boolean m) {
            hasMine = m;
        }

        public void setIsEmpty(boolean e) {
            isEmpty = e;
        }

        public void setIsFlagged(boolean f) {
            isFlagged = f;
        }

        public void setNumMines(int m) {
            numMines = m;
        }

        public void setIsRevealed(boolean r) {
            isRevealed = r;
        }

        public void setContent(char c) {
            content = c;
        }

        // get methods
        public boolean getHasMine() {
            return hasMine;
        }

        public boolean getIsEmpty() {
            return isEmpty;
        }

        public boolean getIsFlagged() {
            return isFlagged;
        }

        public int getNumMines() {
            return numMines;
        }

        public boolean getIsRevealed() {
            return isRevealed;
        }

        public char getContent() {
            return content;
        }
    }
