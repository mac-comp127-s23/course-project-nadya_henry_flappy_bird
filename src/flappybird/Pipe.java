package flappybird;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Image;

/**
 * Defines a pipe for a game of Flappy Bird
 */
public class Pipe {
    
    private GraphicsGroup pipeGraphic;
    private double x; 

    public Pipe(double edge, boolean pointingUP, double x) {
        pipeGraphic = new GraphicsGroup();
        this.x = x;

        Image longImg;
        if (pointingUP) longImg = new Image("FixedDownPipe.png");
        else longImg = new Image("FixedUpPipe.png");
        longImg.setScale(3.5);
        longImg.setPosition(x, edge*1.5);
        if (pointingUP) longImg.moveBy(0, -longImg.getImageHeight() * 3.5);
        pipeGraphic.add(longImg);
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

    @Override
    public String toString() {
        String direction = pipeGraphic.getHeight() > pipeGraphic.getWidth() ? "up" : "down";
        String openingPos = direction.equals("up") ? "bottom" : "top";
        return "Pipe at x=" + x + ", opening at " + openingPos + ", pointing " + direction;
    
    }
}
