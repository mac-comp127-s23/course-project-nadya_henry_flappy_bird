package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Bird {

    public static final double BIRD_X = 300; //TODO update these values;
    public static final double BIRD_WIDTH = 120;
    
    private GraphicsGroup graphics;
    private Rectangle hitBox;
    private Image birdPic;
    private double velocity;

    public Bird() {
        graphics = new GraphicsGroup();
        hitBox = new Rectangle(BIRD_X, 400, BIRD_WIDTH, BIRD_WIDTH);
        hitBox.setFilled(true);
        hitBox.setFillColor(new Color(255, 0, 0));
        graphics.add(hitBox);
    }

    /*
     * Runs every frame.
     */
    public void move() {

    }

    /*
     * Run when user clicks or presses spacebar.
     */
    public void flap() {
        
    }

    public GraphicsGroup getGraphic() {
        return graphics;
    }

}
