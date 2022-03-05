package game;

import java.util.Arrays;
import java.util.Objects;

public class GameBoard {
    private String[][] board;

    private String playerX = "X";
    private String playerO = "O";

    private String currentPlayer;

    public GameBoard(){
        this.board = new String[3][3];
        for (String[] strings : board) Arrays.fill(strings, " ");
        this.currentPlayer = playerO;
    }

    public void switchPlayer(){
        this.currentPlayer = (Objects.equals(this.currentPlayer, this.playerO))? this.playerX : this.playerO;
    }

    public void placeMarker(int row, int col){
        this.board[row][col] = this.currentPlayer;
    }

    public boolean gameFinished(){
        //TODO: work here later
        return false;
    }

    public String winner(){
        return (this.gameFinished())? this.currentPlayer : "";
    }

    public String[][] getBoard(){
        return board;
    }
}
