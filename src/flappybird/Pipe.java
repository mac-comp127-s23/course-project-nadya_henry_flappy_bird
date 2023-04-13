package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Pipe {
    
    private GraphicsGroup pipeGraphic;
    private double x; //TODO add this variable to UML document

    public Pipe(double edge) {
        pipeGraphic = new GraphicsGroup();
        x = PipesHandler.PIPE_INIT_X;

        //TODO make a pipe graphic using 2 rectangles
    }

    /*
     * Run every frame, return true if the given bird is touching the pipe.
     */
    public boolean testHit(Bird bird) {
        return false; //TODO implement this function
    }

    public double moveX(double moveAmount) {
        x -= moveAmount;
        return x;
    }

}
