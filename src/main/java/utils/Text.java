package utils;


import java.awt.*;

// class for rendering text in screen
// similar like Line class
public class Text {
    public String text;
    public Font font;
    public double x, y;
    public double width;    // width of the text
    public double height;   // height of the text
    public Graphics2D g2;   // Graphics2D object for drawing the text
    public Color color = Color.WHITE;   // color object represents the color of the text

    /**
     * @param text = to display what text to be rendered
     * @param font = the font in which the text should be displayed
     * @param x = x coordinate of the text
     * @param y = y coordinate of the text
     * */
    public Text(String text, Font font, double x, double y){
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = text.length() * font.getSize()/2 + font.getSize();
        this.height = font.getSize();
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setColor(this.color);
        g2.setFont(font);
        g2.drawString(text, (float) x, (float) y);
    }

    // method to change the text color
    public void setColor(Color color){
        this.color = color;
    }

    public void update(String updateValue){
        this.text = updateValue;
    }

    public void setFontColor(Color color){
        this.color = color;
    }
}
