package rocks.zipcodewilmington.tictactoe;

/**
 * @author leon on 6/22/18.
 */
public class Board {

    private Character[][] matrix;
    private int rowLength = 3;

    public Board(Character[][] matrix) {
        this.matrix = matrix;
    }

    public Boolean checkGame(char currentPlayer){
        return checkRows(currentPlayer) || checkDiagonal(currentPlayer) || checkColumns(currentPlayer);
    }

    public Boolean checkRows(char currentPlayer){
        boolean running = false;
        for(Character[] line : matrix){
           running = running || checkRow(currentPlayer, line);
        }
        return running;
    }

    public Boolean checkRow(char currentPlayer, Character[] line){
        for(Character space: line){
            if(!space.equals(currentPlayer)){
                return false;
            }
        }
        return true;
    }

    public Boolean checkDiagonal(char currentPlayer){

        int j = 2;
        Character[] topRightBottomLeft = new Character[3];
        Character[] topLeftBottomRight = new Character[3];
        for(int i = 0; i<rowLength; i++){
            topLeftBottomRight[i] = this.matrix[i][i];
            topRightBottomLeft[i] = this.matrix[i][j--];
        }
        return checkRow(currentPlayer, topLeftBottomRight) || checkRow(currentPlayer, topRightBottomLeft);
    }

    public Boolean checkColumns(char currentPlayer){

        int j = 0;
        Character[] left = new Character[3];
        Character[] middle = new Character[3];
        Character[] right = new Character[3];

        for(int i = 0; i<rowLength; i++){
            left[i] = this.matrix[i][j++];
            middle[i] = this.matrix[i][j++];
            right[i] = this.matrix[i][j];
            j=0;
        }
        return checkRow(currentPlayer, left) || checkRow(currentPlayer, middle) || checkRow(currentPlayer, right);
    }

    public Boolean isInFavorOfX() {
        return checkGame('X');
    }

    public Boolean isInFavorOfO() {
        return checkGame('O');
    }

    public Boolean isTie() {
        return !checkGame('X') && !checkGame('O');
    }

    public String getWinner() {
        return isInFavorOfO() ? "O": isInFavorOfX() ? "X" : "";
    }

}
