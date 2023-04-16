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

        // TODO modify this function so that it looks at the points on the edge of the hitbox of the bird,
        // rather than just the center of the bird. :)

        return (pipeGraphic.getElementAt(Bird.BIRD_X, bird.getBirdHeight()) != null);
        
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
