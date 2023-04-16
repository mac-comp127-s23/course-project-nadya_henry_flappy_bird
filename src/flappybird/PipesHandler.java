package flappybird;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.GraphicsGroup;

public class PipesHandler {
    
    private static final int PIPE_GAP = 80;
    public static final int PIPE_INIT_X = 650;
    public static final int PIPE_VELOCITY = 5;

    private static Random random;
    private List<Pipe> pipes; //TODO change to a queue. That's Kynan's job though.
    private GraphicsGroup pipeGraphics;

    public PipesHandler() { //TODO remove "removePipe" from uml (deleted method)
        random = new Random();
        pipes = new ArrayList<>();
        pipeGraphics = new GraphicsGroup();

        generatePipes(randomRange(), PIPE_INIT_X);
        generatePipes(randomRange(), PIPE_INIT_X + (FlappyBird.CANVAS_WIDTH + 80) / 2);
    }

    /*
     * Runs every frame.
     * @return true if a point should be awarded.
     */
    public boolean movePipes(Bird bird) {

        for (Pipe pipe : pipes) {
            
            pipe.getGraphic().moveBy(-PIPE_VELOCITY, 0); // Move the graphics of the pipes
            pipe.moveX(-PIPE_VELOCITY); // Tell the pipes that they've been moved (update their x coordinates)
            
            if (pipe.testHit(bird) && bird.isAlive()) { // TODO figure out death logic and add that here!
                bird.kill();
            }
            
        }

        //Check if a pipe has gone off screen, if so remove those pipes from the list and generate new pipes!
        if (pipes.get(0).getX() <= -80) {
            pipes.remove(0);
            pipes.remove(0);
            generatePipes(randomRange(), PIPE_INIT_X);
        }

        //Check if a pipe just passed the bird, and return true if it did (to reward a point):
        for (Pipe pipe : pipes) if (pipe.getX() == Bird.BIRD_X) return true;
        return false;
    }

    /*
     * Create two new pipes at the given x coordinate and add them to pipes.
     */
    public void generatePipes(int rangeCenter, double x) { //TODO add new parameter to UML doc
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
    private int randomRange() {
        return random.nextInt(100, 650);
    }
}
