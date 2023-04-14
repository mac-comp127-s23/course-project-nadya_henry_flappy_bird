package flappybird;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.GraphicsGroup;

public class PipesHandler {
    
    private static final int PIPE_GAP = 80;
    public static final int PIPE_INIT_X = 650; //TODO adjust these constants!
    public static final int PIPE_VELOCITY = 5; //TODO add this to UML


    private static Random random;
    private List<Pipe> pipes;
    private GraphicsGroup pipeGraphics;

    public PipesHandler() {
        random = new Random();
        pipes = new ArrayList<>();
        pipeGraphics = new GraphicsGroup();

        generatePipes(randomRange());
    }

    /*
     * Runs every frame.
     * @return true if a point should be awarded.
     */
    public boolean movePipes(Bird bird) { // TODO note that this function needs bird parameter on UML doc

        for (Pipe pipe : pipes) {
            
            pipe.getGraphic().moveBy(-PIPE_VELOCITY, 0); // Move the graphics of the pipes
            pipe.moveX(-PIPE_VELOCITY); // Tell the pipes that they've been moved (update their x coordinates)
            
            if (pipe.testHit(bird)) { // TODO figure out death logic and add that here!
                return false;
            }
            
        }

        //TODO remove offscreen pipes from the canvas BEFORE removing them from the list.

        //Check if a pipe has gone off screen, if so remove those pipes from the list and generate new pipes!
        List<Pipe> onScreenPipes = new ArrayList<>();
        onScreenPipes.addAll(pipes.stream()
                                .filter(pipe -> (pipe.getX() >= -80))
                                .toList());
        
        System.out.println(onScreenPipes);
        if (pipes.size() != onScreenPipes.size()) {
            pipes = onScreenPipes;
            generatePipes(randomRange());
        }

        //Check if a pipe just passed the bird, and return true if it did (to reward a point):
        for (Pipe pipe : pipes) if (pipe.getX() == Bird.BIRD_X) return true;
        return false;
    }

    /*
     * Create two new pipes and add them to pipes.
     */
    public void generatePipes(int rangeCenter) {// TODO add parameter to UML document

        Pipe upPipe = new Pipe(rangeCenter - PIPE_GAP, true);
        pipes.add(upPipe);
        pipeGraphics.add(upPipe.getGraphic());
        Pipe downPipe = new Pipe(rangeCenter + PIPE_GAP, false);
        pipes.add(downPipe);
        pipeGraphics.add(downPipe.getGraphic());

    }

    public GraphicsGroup getGraphic() { //TODO add to UML doc
        return pipeGraphics;
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
        return random.nextInt(200, 500);
    }
}
