package flappybird;

import edu.macalester.graphics.GraphicsGroup;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Manages the pipes for a game of Flappy Bird
 */
public class PipesHandler {

    private final int PIPE_GAP = 50;
    private final int PIPE_INIT_X = 700;
    public final static int PIPE_VELOCITY = 5;
    private final int PIPE_LIMIT[] = {250, 550}; // TODO note change to array from point on uml

    private Random random;
    private Queue<Pipe> pipes;
    private GraphicsGroup pipeGraphics;
    private int lastPairCenter; 

    public PipesHandler() {
        random = new Random();
        pipes = new LinkedList<Pipe>();
        pipeGraphics = new GraphicsGroup();
        pipeGraphics.moveBy(-9, 0); // This offsets the visuals from the hitboxes, but only by a little tiny bit.
        lastPairCenter = FlappyBird.GROUND_Y/2;
        generatePipes(randomRange(lastPairCenter), PIPE_INIT_X);
        generatePipes(randomRange(lastPairCenter), PIPE_INIT_X + (0.5)*(FlappyBird.CANVAS_WIDTH + 80));
    }

    /*
     * Runs every frame.
     * Return true if a point should be awarded.
     */
    protected boolean movePipes(Bird bird) {
        for (Pipe pipe : pipes) {
            pipe.getGraphic().moveBy(-PIPE_VELOCITY, 0); // Move the graphics of the pipes
            pipe.moveX(-PIPE_VELOCITY); // Tell the pipes that they've been moved (update their x coordinates)
            if (pipe.testHit(bird)) bird.kill(); // Test to see if the bird hit a pipe
        }
        //Check if a pipe has gone off screen, if so remove those pipes from the list and generate new pipes!
        if (pipes.peek().getX() <= -80) {
            pipes.remove();
            pipes.remove();
            generatePipes(randomRange(lastPairCenter), PIPE_INIT_X);
        }
        //Check if a pipe just passed the bird, and return true if it did (to reward a point):
        for (Pipe pipe : pipes) if (pipe.getX() == Bird.BIRD_X) return true;
        return false;
    }

    /*
     * Create two new pipes at the given x coordinate and add them to pipes.
     */
    private void generatePipes(int rangeCenter, double x) {
        Pipe upPipe = new Pipe(rangeCenter - PIPE_GAP, true, x);
        pipes.add(upPipe);
        pipeGraphics.add(upPipe.getGraphic());
        Pipe downPipe = new Pipe(rangeCenter + PIPE_GAP, false, x);
        pipes.add(downPipe);
        pipeGraphics.add(downPipe.getGraphic());
    }

    public GraphicsGroup getGraphic() {
        return pipeGraphics;
    }

    /*
     * Return a random integer which will be the y value
     * directly between two new pipes.
     */
    private int randomRange(int prevCenter) {
        int returnVal = random.nextInt(
            Math.max(lastPairCenter - 150, PIPE_LIMIT[0]),
            Math.min(lastPairCenter + 150, PIPE_LIMIT[1]));
        lastPairCenter = returnVal;
        return returnVal;
    }

    @Override
    public String toString() {
        return "A Pipes Handler for a game of Flappy Bird containing the following Pipes: " + pipes.toString();
    }
}
