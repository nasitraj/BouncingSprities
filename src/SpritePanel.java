/* File Name: SpritePanel.java
 * Author Name: Algonquin College
 * Modified By: Raj Nasit
 * Date: 25 - dec - 2016
 * 	: This class will do all the function of main panel which include drawing circle and sprite, making sprite move and handling sprite functions.
 */



import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SpritePanel extends JPanel{
    ArrayList<Sprite> sprite = new ArrayList<Sprite>();// WIll be use to store sprites
    private Circle circle ; // will use to darw and intereact with circle.
    private SQLiteTest sqLiteTest;


    /**
     * Default Constartor
     * This method have a mouselistener.
     */
    public SpritePanel(){
        addMouseListener(new Mouse());
    }
    /**
     * newSprite method
     * This method will create a new sprite and a thread on calling this method.
     * @param event
     */
    private void newSprite (MouseEvent event){
        Sprite s = new Sprite(this, sqLiteTest);
        new Thread(s).start();
        sprite.add(s);
    }
    /**animate method
     * This method is for displaying the sprite.
     * This method calls repaint() mehtod to do its function.
     *
     */
    public void animate(){
        System.out.format("%15s%15s%15s%15s%15s%n","ID",   "X_Coordinate" ,  "Y_Coordinate",   "X-speed",   "Y-Speed");
        while (true){


            this.repaint();
            //sleep while waiting to display the next frame of the animation
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    /**
     *
     * @author Raj
     * Class Mouse
     * This class will be use to call newSprite(event) method on pressing or clicking mouse buttons.
     *
     */
    private class Mouse extends MouseAdapter {
        @Override
        public void mousePressed( final MouseEvent event ){
            newSprite(event);
            ResultSet rs = sqLiteTest.display();

            try{
            while(rs.next()) {
                    System.out.format("%15s%15s%15s%15s%15s%n", rs.getInt("id"),rs.getInt("x_coordinate"), rs.getInt("y_coordinate"),rs.getInt("x_coordenete_speed"),rs.getInt("y_coordenete_speed"));
                    //System.out.println(rs.getInt("id") + "    " + rs.getInt("x_coordinate") + "           " + rs.getInt("y_coordinate"));

            }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * paintComponent method
     * This method will draw sprite and will also handles different functions for a sprite on base on its location and different sprite location
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        circle = new Circle(this); // will create new cirlce.
        circle.draw(g); // will draw circle

        //This for loop will hande different function for sprite.

        for(int i =0; i<sprite.size();i++){
            int numIn = 0; // will be use to count the number of sprite which is inside the circle.

            if (sprite.get(i) != null){
                // this loop is use for getting number of sprite inside the circle.
                for(int k=0;k<sprite.size();k++){
                    if(sprite.get(k).location()){
                        numIn++;
                    }
                }
                // the distance variable is to calculate the distance the center of sprite and center of circle.
                Double distance = Math.sqrt	(((sprite.get(i).getX() - circle.getX())*(sprite.get(i).getX() - circle.getX()))+ ((sprite.get(i).getY() - circle.getY())*(sprite.get(i).getY() - circle.getY())));

                if(distance < 106 && distance >103){ // will check if ball is on the circle or not
                    if(sprite.get(i).location()){ // if ball is exiting the circle than following code will be run.
                        if(numIn>2 ){ // will check if 2 or more sprite is inside the circle.
                            sprite.get(i).canMove();
                            sprite.get(i).outCircle();
                        }
                        else{
                            sprite.get(i).cannotMove();
                        }
                    }
                    else{ // if sprite is entering the circle, following code will run.
                        if(numIn<=3 ){ // will check if there are 3 or less sprite in inside the circle.
                            sprite.get(i).canMove();
                            sprite.get(i).inCircle();
                        }
                        else{
                            sprite.get(i).cannotMove();
                        }

                    }

                }else if(distance<=103){ // will check if sprite is inside the circle.
                    sprite.get(i).inCircle();
                }else{  // will run if sprite is outside the circle.
                    sprite.get(i).outCircle();
                }

                sprite.get(i).draw(g); // will draw the sprite.
            }
        }
    }

    public void setSqLiteTest(SQLiteTest sqLite){
        sqLiteTest = sqLite;
    }

}
