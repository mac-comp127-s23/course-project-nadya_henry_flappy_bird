package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Image;

import java.awt.Color;

/**
 * Defines a pipe for a game of Flappy Bird
 */
public class Pipe {

    protected static final Color COLOR = new Color(33, 173, 22);
    
    private GraphicsGroup pipeGraphic;
    private double x; 

    public Pipe(double edge, boolean pointingUP, double x) {
        pipeGraphic = new GraphicsGroup();
        this.x = x;
 

        //  // Draw the long part
         Image longImg;
         if (pointingUP) {
            longImg = new Image("Pipes1.png");
            longImg.setPosition(x + 12, FlappyBird.CANVAS_HEIGHT + edge);
             
         } else {
             longImg = new Image("Pipes2.png");
             longImg.setPosition(x + 12, edge);
        }
         
         longImg.setScale(3.5);
         pipeGraphic.add(longImg);



        // Draw the long part
        // Rectangle longRect = new Rectangle(x + 12, edge, 45, 1000);
        // longRect.setFillColor(COLOR);
        // if (pointingUP) longRect.moveBy(0, -1000);
        // pipeGraphic.add(longRect); 

        // // Draw the opening
        // if (pointingUP) edge -= 5;
        // Rectangle opening = new Rectangle(x, edge, 70, 35);
        // opening.setFillColor(COLOR);
        // pipeGraphic.add(opening);
          
      
    }

    /*
     * Run every frame, return true if the given bird is touching the pipe.
     */
    public boolean testHit(Bird bird) {
        for (Point cPoint : bird.getCollisionPoints()) {
            if (pipeGraphic.testHit(cPoint.getX(), cPoint.getY())) return true;
        }
        return false;
    }

    protected double moveX(double moveAmount) { 
        x += moveAmount;
        return x;
    }

    public GraphicsGroup getGraphic() { 
        return pipeGraphic;
    }

    public double getX() { 
        return x;
    }

    //TODO: make toString() better
    @Override
    public String toString() {
        String direction = pipeGraphic.getHeight() > pipeGraphic.getWidth() ? "up" : "down";
        String openingPos = direction.equals("up") ? "bottom" : "top";
        return "Pipe at x=" + x + ", opening at " + openingPos + ", pointing " + direction;
    
    }
}
