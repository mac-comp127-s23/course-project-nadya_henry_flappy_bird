package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;

import java.util.List;

/**
 * Defines a bird for a game of Flappy Bird
 */
public class Bird {

    protected static final double BIRD_X = 200;
    protected static final double BIRD_WIDTH = 60;
    private final double FLAP_VELO = 6.9;
    
    private GraphicsGroup graphics;
    private Image birdPic;
    private double velocity;
    private boolean alive;

    public Bird() {
        alive = true;
        graphics = new GraphicsGroup();
        graphics.setCenter(BIRD_X, 350);

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
        if (graphics.getY() >= FlappyBird.GROUND_Y - 30) {
            velocity = 0;
            alive = false;
        }
    }

    /*
     * Run when user clicks or presses spacebar.
     */
    public void flap() { //TODO change to public on uml
        velocity = FLAP_VELO;
    }

    /*
     * Sets alive to false and makes the bird fall to the ground :(
     */
    protected void kill() {
        alive = false;
    }

    /*
     * Moves the bird down after the bird has died.
     * Return true if the bird is still falling, return false when the bird hits the ground.
     */
    protected boolean animateDeath() { // TODO add to uml
        birdPic.setRotation(75);
        double birdY = graphics.getY();
        double groundY = FlappyBird.GROUND_Y - 15;
        velocity = -0.4;

        if (birdY < groundY) {
            graphics.moveBy(0, -velocity);
            velocity -= 0.69;
            return true;
        }
        else return false;
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
            new Point(BIRD_X + (BIRD_WIDTH/4), graphics.getCenter().getY() - 0.125*BIRD_WIDTH), // Upper middle
            new Point(BIRD_X + (BIRD_WIDTH/4), graphics.getCenter().getY() + 0.125*BIRD_WIDTH), // Lower middle
            new Point(BIRD_X + (3*BIRD_WIDTH/4), graphics.getCenter().getY() - 0.125*BIRD_WIDTH), // Upper middle
            new Point(BIRD_X + (3*BIRD_WIDTH/4), graphics.getCenter().getY() + 0.125*BIRD_WIDTH) // Lower middle
        
            //TODO maybe add a point that uses velocityToAngle() to follow the bird's beak?
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
