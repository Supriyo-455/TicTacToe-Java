package window;

import game.GameBoard;
import geometry.Line;
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

    public Window(){
        // ========= Setting up the JFrame =================
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Tic tac toe");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // =================================================

        // ============= Getting the graphics from JFrame ========
        this.g2 = (Graphics2D) this.getGraphics();
        // =======================================================

        // ============ Creating array of lines ===========
        this.lines = new Line[10];
        // ================================================

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
    }

    //===== Fill board with X and O ========
    public void fillBoard(Graphics2D g2){
        String[][] board = new GameBoard().getBoard();
        Font font = new Font("Monospace Regular", Font.BOLD, 50);
        int x = 0;
        int y = 1;
        int offsetX = WIDTH/3;
        int offsetY = HEIGHT/3;
        for(String row[] : board){
            for(String p : row){
                Text text;
                if(p == "X"){
                    text = new Text(p, font, x*offsetX, y*offsetY);
                    text.draw(g2);
                }
                else if(p == "O"){
                    text = new Text(p, font, x*offsetX, y*offsetY);
                    text.draw(g2);
                }
                x++;
            }
            y++;
            x = 0;
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
