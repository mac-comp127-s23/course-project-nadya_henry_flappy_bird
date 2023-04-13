package flappybird;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.GraphicsGroup;

public class PipesHandler {
    
    private static final int PIPE_GAP = 50;
    public static final int PIPE_INIT_X = 1000; //TODO decide on these constants!
    public static final int PIPE_VELOCITY = 10; //TODO add this to UML


    private static Random random;
    private List<Pipe> pipes;
    private GraphicsGroup pipeGraphics;

    public PipesHandler() {
        random = new Random();
        pipes = new ArrayList<>();
    }

    /*
     * Runs every frame.
     * @return true if a point should be awarded.
     */
    public boolean movePipes(Bird bird) { // TODO note that this function needs bird parameter on UML

        pipeGraphics.moveBy(-PIPE_VELOCITY, 0); // Move the graphics

        for (Pipe pipe : pipes) {
            
            if (pipe.testHit(bird)) { // TODO figure out death logic and add that here!
                return false;
            }

            pipe.moveX(-PIPE_VELOCITY); // Tell the pipes that they've been moved

        }

        //TODO check if a pipe just passed the bird, and return true if it did (to reward a point)
        return false;
    }

    /*
     * Create two new pipes and add them to pipes.
     */
    public void generatePipes() {
        //TODO generate two pipes and add them to pipes

        //TODO add the graphics of those pipes to pipesGraphics
    }

    /*
     * Returns true if pipes have gone off of the screen.
     */
    private boolean removePipe() {
        return true;
    }

    /*
     * Return a random integer which will be the y value
     * directly between two new pipes.
     */
    private int randomRange() {
        return random.nextInt(10, 50); //TODO figure out this range actually :)
    }
}
