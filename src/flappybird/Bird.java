package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Image;

public class Bird {

    public static final double BIRD_X = 200;
    public static final double BIRD_WIDTH = 60;
    
    private GraphicsGroup graphics;
    private Ellipse hitBox; // TODO reflect type change in UML
    private Image birdPic;
    private double velocity;
    private boolean alive;

    public Bird() {
        alive = true;
        graphics = new GraphicsGroup();

        hitBox = new Ellipse(BIRD_X, 350, BIRD_WIDTH, BIRD_WIDTH);
        hitBox.setStroked(false); // Comment out this line to see hitBox
        graphics.add(hitBox);

        birdPic = new Image("flappyBird.png");
        birdPic.setScale(0.1);
        birdPic.setCenter(BIRD_X + (BIRD_WIDTH/2) + 5, 350 + (BIRD_WIDTH/2) - 5);
        graphics.add(birdPic);
    }

    /*
     * Runs every frame.
     */
    public void move() {

        birdPic.setRotation(velocityToAngle(velocity));

        graphics.moveBy(0, -velocity);
        velocity -= 0.5;

        // check if the bird hits the ground here.
        if ((graphics.getY() + 300) + hitBox.getHeight() >= FlappyBird.GROUND_Y) {
            velocity = 0;
            alive = false;
        }   
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

    public boolean isAlive() {
        return alive;
    }

    /*
     * Private helper function to get the angle of the bird image
     * based on its velocity.
     */
    private double velocityToAngle(double velocity) {
        return (-velocity) / (0.5) - 10;
    }

}
