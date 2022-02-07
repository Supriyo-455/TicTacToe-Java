package window;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {

    public Graphics2D g2;
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    public boolean isRunning = true;

    public Window(){
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Tic tac toe");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.g2 = (Graphics2D) this.getGraphics();
    }

    private void init(){

    }

    @Override
    public void run() {

    }
}
