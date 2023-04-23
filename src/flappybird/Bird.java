package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;

import java.util.List;

/**
 * Defines a bird for a game of Flappy Bird
 */
public class Bird {

    protected static final double BIRD_X = FlappyBird.CANVAS_WIDTH/2;
    protected static final double BIRD_WIDTH = 60;
    private final double FLAP_VELO = 6.9;
    private double y = (BIRD_WIDTH/2) - 5;

    private GraphicsGroup graphics;
    private Image birdPic;
    private double velocity;
    private boolean alive;
    private double time = 0;
    private double amp = 10;  
    private double freq = 1/60.0; 

    public Bird() {
        alive = true;
        graphics = new GraphicsGroup();
        graphics.setCenter(BIRD_X, 350);

        birdPic = new Image("flappyBird.png");
        birdPic.setScale(0.1);
        birdPic.setCenter((BIRD_WIDTH/2) + 5, y);
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
        if (graphics.getY() >= FlappyBird.GROUND_Y - 30) {
            velocity = 0;
            alive = false;
        }
    }

    /*
     * Run when user clicks or presses spacebar.
     */
    public void flap() {
        velocity = FLAP_VELO;
    }

    /*
     * Sets alive to false :(
     */
    protected void kill() {
        alive = false;
    }

    /*
     * Moves the bird down after the bird has died.
     * Return true if the bird is still falling, return false when the bird hits the ground.
     */
    protected boolean animateDeath() {
        velocity = -0.4;
        graphics.moveBy(0, -velocity);
        if (graphics.getY() >= FlappyBird.GROUND_Y - 12 - velocity) return false;
        birdPic.setRotation(75);
        velocity -= 0.69;
        return true;
    }

    /**
	 * Floating bird effect on menu screen
	 */
	protected void birdFloat() { // Add to UML Doc
        double yOffset = amp * Math.sin(2*Math.PI*freq*time);
        graphics.setCenter(BIRD_X, 350 + yOffset);
        time += 1;
 
    }

    public GraphicsGroup getGraphic() {
        return graphics;
    }

    public boolean isAlive() {
        return alive;
    }

    /*
     * Returns a list of points that will be checked against pipe graphics
     * to see if the bird is touching a pipe.
     */
    public List<Point> getCollisionPoints() {
        return List.of(
            new Point(BIRD_X + (BIRD_WIDTH/2), graphics.getCenter().getY() - 0.25*BIRD_WIDTH), // Top
            new Point(BIRD_X + (BIRD_WIDTH/2), graphics.getCenter().getY() + 0.25*BIRD_WIDTH), // Bottom
            new Point(BIRD_X + BIRD_WIDTH, graphics.getCenter().getY()), // Front
            new Point(BIRD_X, graphics.getCenter().getY()), // Back
            new Point(BIRD_X + (BIRD_WIDTH/2) + ((BIRD_WIDTH/2))*Math.cos(Math.toRadians(birdPic.getRotation())), // Bird's beak tip.
                graphics.getCenter().getY() + ((BIRD_WIDTH/2))*Math.sin(Math.toRadians(birdPic.getRotation())))
        );
    }

    public double getX() {
        return graphics.getX();
    }

    public double getY() {
        return graphics.getY();
    }

    /*
     * Private helper function to get the angle of the bird image
     * based on its velocity.
     */
    private double velocityToAngle(double velocity) {
        return (-velocity) / (0.5) - 10;
    }

    @Override
    public String toString() {
        return "Bird at (" + Math.round(BIRD_X + (BIRD_WIDTH/2)) +
            ", " + Math.round(graphics.getCenter().getY()) +
            ") with velocity " + Math.round(velocity);
    }
}
