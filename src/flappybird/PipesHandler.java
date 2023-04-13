package flappybird;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.GraphicsGroup;

public class PipesHandler {
    
    private static final int PIPE_GAP = 20;
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
    public boolean movePipes() {
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
