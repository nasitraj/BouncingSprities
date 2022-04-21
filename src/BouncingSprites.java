/* File Name: BouncingSprite.java
 * Author Name: Algonquin College
 * Modified By: Raj Nasit
 * Date: 25 - dec - 2016
 * Description: This is the main class which will run pritePanel when program is run.
 */



import javax.swing.JFrame;

public class BouncingSprites {

    private JFrame frame;// frame for main panel
    private SpritePanel panel = new SpritePanel(); // SpritePanel object use for main planel.

    /**
     * Default constructor
     * Will set function on frame and will aslo set main panel in frame.
     */
    public BouncingSprites() {
        frame = new JFrame("Bouncing Sprites 2017W"); // tittle on frame
        frame.setSize(500, 500); // setting the size of frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel); // adding the panel in frame.
        frame.setVisible(true);
    }
    /**
     * start method
     * Wi;; run anumate() method form SpritePanel class.
     */
    public void start(){
        panel.animate();  // never returns due to infinite loop in animate method
    }

    public static void main(String[] args) {
        new BouncingSprites().start();
    }
}
