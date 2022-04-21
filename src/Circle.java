/* File Name: Circle.java
 * Author Name: Raj Nasit
 * Date: 25 - dec - 2016
 * Description: This class will be use to darw a circle.
 */
import java.awt.Color;
import java.awt.Graphics;

public class Circle {
    private Color color = Color.black; // color of the bountery of circle.
    private static int SIZE = 200; // size of circle.
    private int x; // x corrdanete
    private int y;// y cordanete
    SpritePanel panel; // panel of main window

    /**
     * Defaul constructor
     * This constructor will get witdh and hight of main window from its parameter.
     * @param panel
     */
    public Circle(SpritePanel panel){
        this.panel = panel;
        x = panel.getWidth();
        y = panel.getHeight();
    }
    /**
     * draw method
     * This method will draw the circle.
     * @param g
     */
    public void draw(Graphics g){
        g.setColor(color);
        g.drawOval(x/2-100, y/2-100, SIZE, SIZE);
    }
    /**
     * getX methosd
     * will return central x coordaneter
     * @return x/2
     */
    public int getX(){
        return x/2;
    }
    /**
     * getY methosd
     * will return central y coordaneter
     * @return y/2
     */
    public int getY(){
        return y/2;
    }
}