package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Pipe {
    
    private GraphicsGroup pipeGraphic;
    private double x; 


    public Pipe(double edge, boolean pointingUP, double x) {
        pipeGraphic = new GraphicsGroup();
        this.x = x;

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

    public double moveX(double moveAmount) { 
        x += moveAmount;
        return x;
    }

    public GraphicsGroup getGraphic() { 
        return pipeGraphic;
    }

    public double getX() { 
        return x;
    }

}
