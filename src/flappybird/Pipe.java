package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Pipe {
    
    private GraphicsGroup pipeGraphic;
    private double x; //TODO add this variable to UML document

    public Pipe(double edge, boolean pointingUP) { // TODO Add boolean up variable to the UML document
        pipeGraphic = new GraphicsGroup();
        x = PipesHandler.PIPE_INIT_X;

        if (pointingUP) edge -= 5;
        Rectangle end = new Rectangle(x, edge, 70, 35);
        end.setFillColor(new Color(33, 173, 22));
        pipeGraphic.add(end);

        //TODO make the second rectangle
    }

    /*
     * Run every frame, return true if the given bird is touching the pipe.
     */
    public boolean testHit(Bird bird) {
        return false; //TODO implement this function
    }

    public double moveX(double moveAmount) { // TODO add to UML doc
        x += moveAmount;
        return x;
    }

    public GraphicsGroup getGraphic() { //TODO add to UML doc
        return pipeGraphic;
    }

    public double getX() { //TODO add to uml doc
        return x;
    }

}
