package geometry;

import java.awt.*;

public class Line {
    public int x1;
    public int x2;
    public int y1;
    public int y2;
    public Color color;

    public Line(int x1, int y1, int x2, int y2, Color color){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public void draw(Graphics2D g2){
        g2.setColor(this.color);
        g2.drawLine(x1, y1, x2, y2);
    }
}
