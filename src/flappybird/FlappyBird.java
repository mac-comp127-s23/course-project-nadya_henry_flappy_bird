package flappybird;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;

public class FlappyBird {

    public static final int CANVAS_WIDTH = 640;
    public static final int CANVAS_HEIGHT = 980;
    public static final int GROUND_Y = 790;

    private CanvasWindow canvas;
    private int points;
    private GraphicsText pointsText;
    private Rectangle groundRect;
    private Image backgroundImg;

    private Bird bird;
    private PipesHandler pipesHandler;

    public FlappyBird() {
        canvas = new CanvasWindow("Flappy Bird", CANVAS_WIDTH, CANVAS_HEIGHT);
        
        reset();
        canvas.onMouseDown(event -> bird.flap());
        canvas.onKeyDown(event -> {
            if (event.getKey().equals(Key.SPACE)) bird.flap();
        });

        Runnable mainGameplayLoop = () -> {
            bird.move();
            if (pipesHandler.movePipes(bird)) points += 1;
            updatePointsText();
            if (!bird.isAlive()) reset();
        };
        canvas.animate(mainGameplayLoop);

    }

    private void reset() {
        canvas.removeAll();

         // Add background image
         backgroundImg = new Image("flappyBirdBckg2.jpeg");
         // backgroundImg.setPosition(0, 0);
         canvas.add(backgroundImg);
 
 
         // Add ground rectangle
         groundRect = new Rectangle(0, GROUND_Y, CANVAS_WIDTH, CANVAS_HEIGHT - GROUND_Y);
         groundRect.setStroked(false); 
         groundRect.setFilled(false);
         // groundRect.setFillColor(Color.WHITE);
         canvas.add(groundRect);

        points = 0;
        pointsText = new GraphicsText("" + points, 22, 45);
        pointsText.setFontSize(40);
        canvas.add(pointsText);

        bird = new Bird();
        canvas.add(bird.getGraphic());
        pipesHandler = new PipesHandler();
        canvas.add(pipesHandler.getGraphic());
        }

    private void gameOver(boolean isAlive) {
        reset();
    }

    private void updatePointsText() {
        pointsText.setText("" + points);
    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}
