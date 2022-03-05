package window;

import game.GameBoard;
import geometry.Line;
import listeners.ML;
import utils.Text;
import utils.Time;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {

    public Graphics2D g2;
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    public boolean isRunning = true;
    private Line[] lines;
    private ML mouseListener = new ML();
    private GameBoard gameBoard;

    public Window(){
        // ========= Setting up the JFrame =================
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Tic tac toe");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        // =================================================

        // ============= Getting the graphics from JFrame ========
        this.g2 = (Graphics2D) this.getGraphics();
        // =======================================================

        // ============ Creating array of lines ===========
        this.lines = new Line[10];
        // ================================================

        // initializing the game
        this.init();
    }

    private void init(){
        createBoard();
    }

    private void createBoard() {
        int x = WIDTH/3;
        int y = HEIGHT/3;
        // Creating vertical lines
        for(int i=1; i<3; i++){
            lines[i] = new Line(i*x, 0, i*x, HEIGHT, Color.WHITE);
        }
        int count = 0;
        // creating horizontal lines
        for (int i = 3; i < 6; i++) {
            count++;
            lines[i] = new Line(0, count*y, WIDTH, count*y, Color.WHITE);
        }

        // creating instance of gameboard
        this.gameBoard = new GameBoard();
    }

    //===== Fill board with X and O ========
    public void fillBoard(Graphics2D g2){
        //=============================================
        //          INSTANCE of board
        //=============================================
        String[][] board = this.gameBoard.getBoard();
        //=============================================

        //============ Declaring the font for the text =====================
        Font font = new Font("Monospace Regular", Font.BOLD, 50);
        //==================================================================

        //=======================================================
        // For drawing the text in right positions in the board
        // Explanation:
        //      for row 0 col 0 the position should be
        //          xpos = 0.4f*offsetX    here, offsetX = total width of screen / 3
        //          xpos = 0.6f*offsetY    here, offsetY = total height of screen / 3
        //
        //      for row 1 col 1 the position should be
        //          xpos = 1.4f*offsetX    here, offsetX = total width of screen / 3
        //          xpos = 1.6f*offsetY    here, offsetY = total height of screen / 3
        //
        //
        //      Like this.........
        //=======================================================
        float x = 0.4f;
        float y = 0.6f;
        float offsetX = WIDTH/3;
        float offsetY = HEIGHT/3;
        for(String row[] : board){
            for(String p : row){
                // making the text in that position
                Text text = new Text(p, font, x*offsetX, y*offsetY);
                // drawing the text
                text.draw(g2);
                x++;
            }
            y++;
            // new row hence reset the col
            x = 0.4f;
        }
    }

    //====== method for drawing lines =======
    private void drawBoard(Graphics2D g2) {
        for(int i=1; i<3; i++){
            lines[i].draw(g2);
        }
        for (int i = 3; i < 6; i++) {
            lines[i].draw(g2);
        }
    }
    //========================================

    private void update(double dt) {
        // ================================================
        //    Double buffering for smooth gameplay
        // ================================================
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);
        //==================================================

        //=============== Checking for mouse events ==============
        fillBoardAccordingToMouse();
        //========================================================
    }

    private void fillBoardAccordingToMouse() {
        int mouseX = (int) mouseListener.getMouseX();
        int mouseY = (int) mouseListener.getMouseY();

        int shellWidth = this.WIDTH/3;
        int shellHeight = this.HEIGHT/3;

        int row = mouseX/shellWidth;
        int col = mouseY/shellHeight;

        if(mouseListener.isMousePressed() && this.gameBoard.getBoard()[col][row] == " "){
            this.gameBoard.placeMarker(col, row);
            this.gameBoard.switchPlayer();
        }
    }

    private void draw(Graphics g) {
        // casting Graphics to Graphics2D object
        Graphics2D g2N = (Graphics2D) g;
        // =====================================

        // creating a big rect of screen size to change the background color :(XD LOL)
        g2N.setColor(Color.BLACK);
        g2N.fillRect(0, 0, WIDTH, HEIGHT);
        //===========================================================================

        // =======================
        this.drawBoard(g2N);
        this.fillBoard(g2N);
        // =======================
    }

    @Override
    public void run() {
        double lastTime = 0.0f;
        while(this.isRunning){
            double time = Time.getTime();
            double deltaTime = time - lastTime;
            lastTime = time;
            System.out.println("FPS : "+1/deltaTime);
            update(deltaTime);
        }
    }
}
