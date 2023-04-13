package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Pipe {
    
    private GraphicsGroup pipeGraphic;

    public Pipe(double edge) {
        pipeGraphic = new GraphicsGroup();

        //TODO make a pipe graphic using 2 rectangles
    }

    /*
     * Run every frame, return true if the given bird is touching the pipe.
     */
    public boolean testHit(Bird bird) {
        return true; //TODO implement this function
    }

}
