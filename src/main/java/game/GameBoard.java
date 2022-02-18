package game;

public class GameBoard {
    private static String[][] board = {{"X","O","X"},{"O","X","O"},{"X","O","X"}};

    public GameBoard(){}

    public String[][] getBoard(){
        return board;
    }
}
