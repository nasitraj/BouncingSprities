/* File Name: Sprite.java
 * Author Name: Algonquin College
 * Modified By: Raj Nasit
 * Date: 25 - dec - 2016
 * Description: This class is for creating sprites and getting it locations.
 */



import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Sprite implements Runnable{

    public final static Random random = new Random(); // will be use for creating random number for location and speed for sprite.

    final static int SIZE = 10; // size of sprite
    final static int MAX_SPEED = 5; // max speed for sprite
    int framRate = 40; // the frame rate at will the sprite will display or moving
    SpritePanel panel; // the main panel

    private int x; // x coordenete
    private int y; // y coordenete

    private int dx; // x coordenete speed
    private int dy; // y coordenete speed
    float r = random.nextFloat();
    float g = random.nextFloat();
    float b = random.nextFloat();
    private Color color = new Color(r,g,b); // color of sprite


    private boolean canMove = true; // condition to move a sprite
    private boolean inCircle = false; // location for sprite

    /**
     * Inicatial construncor
     * This constructor will get SpritePanel object as a parrameter
     * And that object will be use to get witdh and hight if main panel for creating location for new sprite.
     * @param panel
     */
    public Sprite (SpritePanel panel, SQLiteTest sqLiteTest)
    {

        this.panel = panel;
        x = random.nextInt(panel.getWidth()); // will generete a random x coordenete
        y = random.nextInt(panel.getHeight()); // will generete a random y coordenete
        dx = random.nextInt(2*MAX_SPEED) - MAX_SPEED;
        dy = random.nextInt(2*MAX_SPEED) - MAX_SPEED;
        this.addData(sqLiteTest);
    }

    /**
     * Draw method
     * This Method will draw sprite with spicific size and will fill it with color.
     * @param graphics
     */
    public void draw(Graphics graphics){


        graphics.setColor(color);
        graphics.fillOval(x, y, SIZE, SIZE);
    }

    /**
     * Move Method
     * This method will move the sprite in a given speed.
     * And will also check the condication for moving a sprite (True/false).
     */
    public void move(){

        // check for bounce and make the ball bounce if necessary
        //
        synchronized(this){
            while(!canMove){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (x < 0 && dx < 0){
                //bounce off the left wall
                x = 0;
                dx = -dx;
            }
            if (y < 0 && dy < 0){
                //bounce off the top wall
                y = 0;
                dy = -dy;
            }
            if (x > panel.getWidth() - SIZE && dx > 0){
                //bounce off the right wall
                x = panel.getWidth() - SIZE;
                dx = - dx;
            }
            if (y > panel.getHeight() - SIZE && dy > 0){
                //bounce off the bottom wall
                y = panel.getHeight() - SIZE;
                dy = -dy;
            }

            //make the ball move
            x += dx;
            y += dy;
            this.notifyAll();
        }


    }
    /**
     * Run method
     * This method will call move method and will also set framerate to display it.
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            this.move();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    /**
     * getX method
     * This method will return x coordnate of sprite.
     * @return x
     */
    public int getX(){
        return x;
    }

    /**
     * getY method
     * This method will return y coordnate of sprite.
     * @return y
     */
    public int getY(){
        return y;
    }
    /**
     * onCircle method
     * This method will be use to setLocation for sprite.
     */
    public void onCircle(){
        synchronized(this){
            canMove = false;
            this.notifyAll();
        }

    }
    /**
     * getcanMove method
     * This method will return the current moving state for sprite.
     * @return
     */
    public boolean getcanMove(){
        return canMove;
    }

    /**
     * cannotMove method
     * This method will change the state of sprite to false.
     */
    public void cannotMove(){
        synchronized(this){
            canMove = false;
            this.notifyAll();
        }
    }
    /**
     * canMove method
     * This method will change the state of sprite to ture.
     */
    public void canMove(){

        synchronized(this){
            canMove = true;
            this.notifyAll();
        }
    }
    /**
     * inCircle method
     * This method will be use to setLocation for sprite.
     */
    public void inCircle(){
        synchronized(this){
            inCircle = true;
            this.notifyAll();
        }

    }
    /**
     * inCircle method
     * This method will be use to setLocation for sprite.
     */
    public void outCircle(){
        synchronized(this){
            inCircle = false;
            this.notifyAll();
        }
    }
    /**
     * location method
     * this method will return the current location of sprite.
     * @return
     */
    public boolean location(){
        return inCircle;
    }

    public void addData(SQLiteTest sqLiteTest){
        sqLiteTest.addsprite(x,y,dx,dy);
    }
}

