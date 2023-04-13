package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Bird {

    public static final double BIRD_X = 200; //TODO update these values;
    public static final double BIRD_WIDTH = 60;
    
    private GraphicsGroup graphics;
    private Rectangle hitBox;
    private Image birdPic;
    private double velocity;

    public Bird() {
        graphics = new GraphicsGroup();

        hitBox = new Rectangle(BIRD_X, 350, BIRD_WIDTH, BIRD_WIDTH);
        // hitBox.setStroked(false); // Comment out for testing
        hitBox.setFilled(false);
        graphics.add(hitBox);

        
    }

    /*
     * Runs every frame.
     */
    public void move() {
        graphics.moveBy(0, -velocity);
        velocity -= 0.5;
    }

    /*
     * Run when user clicks or presses spacebar.
     */
    public void flap() {
        velocity = 6.9;
    }

    public GraphicsGroup getGraphic() {
        return graphics;
    }

}
