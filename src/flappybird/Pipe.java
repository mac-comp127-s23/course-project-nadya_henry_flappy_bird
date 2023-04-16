package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Pipe {

    public static final Color COLOR = new Color(33, 173, 22);
    
    private GraphicsGroup pipeGraphic;
    private double x; 

    public Pipe(double edge, boolean pointingUP, double x) {
        pipeGraphic = new GraphicsGroup();
        this.x = x;

        // Draw the long part
        Rectangle longRect = new Rectangle(x + 12, edge, 45, 1000);
        longRect.setFillColor(COLOR);
        if (pointingUP) longRect.moveBy(0, -1000);
        pipeGraphic.add(longRect); 

        // Draw the opening
        if (pointingUP) edge -= 5;
        Rectangle opening = new Rectangle(x, edge, 70, 35);
        opening.setFillColor(COLOR);
        pipeGraphic.add(opening);



        //TODO make the second rectangle

    }

    /*
     * Run every frame, return true if the given bird is touching the pipe.
     */
    public boolean testHit(Bird bird) {

        // TODO modify this function so that it looks at the points on the edge of the hitbox of the bird,
        // rather than just the center of the bird. :)

        return (pipeGraphic.getElementAt(Bird.BIRD_X + (Bird.BIRD_WIDTH/2), bird.getBirdHeight()) != null);

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
