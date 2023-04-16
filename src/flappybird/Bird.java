package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Image;

/**
 * Defines a bird for a game of Flappy Bird
 */
public class Bird {

    protected static final double BIRD_X = 200;
    protected static final double BIRD_WIDTH = 60;
    private final double FLAP_VELO = 6.9;
    
    private GraphicsGroup graphics;
    private Ellipse hitBox;
    private Image birdPic;
    private double velocity;
    private boolean alive;

    public Bird() {
        alive = true;
        graphics = new GraphicsGroup();
        graphics.setCenter(BIRD_X, 350);

        hitBox = new Ellipse(0, 0, BIRD_WIDTH, BIRD_WIDTH);
        hitBox.setStroked(false); // Comment out this line to see hitBox
        graphics.add(hitBox);

        birdPic = new Image("flappyBird.png");
        birdPic.setScale(0.1);
        birdPic.setCenter((BIRD_WIDTH/2) + 5, (BIRD_WIDTH/2) - 5);
        graphics.add(birdPic);
    }

    /*
     * Runs every frame.
     */
    protected void move() {

        birdPic.setRotation(velocityToAngle(velocity));

        graphics.moveBy(0, -velocity);
        velocity -= 0.5;

        // check if the bird hits the ground here.
        if (graphics.getY() >= FlappyBird.GROUND_Y) {
            velocity = 0;
            alive = false;
        }
    }

    /*
     * Run when user clicks or presses spacebar.
     */
    protected void flap() {
        velocity = FLAP_VELO;
    }

    /*
     * Sets alive to false :(
     */
    protected void kill() {
        alive = false;
    }

    public GraphicsGroup getGraphic() {
        return graphics;
    }

    public boolean isAlive() {
        return alive;
    }

    public double getBirdHeight() {
        return graphics.getCenter().getY();
    }

    /*
     * Private helper function to get the angle of the bird image
     * based on its velocity.
     */
    private double velocityToAngle(double velocity) {
        return (-velocity) / (0.5) - 10;
    }

}
