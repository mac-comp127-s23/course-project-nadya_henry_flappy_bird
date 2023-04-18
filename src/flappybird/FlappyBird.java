package flappybird;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


/**
 * Manages a game of Flappy Bird
 */
public class FlappyBird {

    protected static final int CANVAS_WIDTH = 640;
    protected static final int GROUND_Y = 750;
    protected static final int CANVAS_HEIGHT = 980;
    

    private CanvasWindow canvas;
    private int points;
    private GraphicsText pointsText;
    private Rectangle groundRect;
    private Image backgroundImg;
    private ImageIcon popupIcon;
    private boolean running;

    private Bird bird;
    private PipesHandler pipesHandler;

    public FlappyBird() {
        canvas = new CanvasWindow("Flappy Bird", CANVAS_WIDTH, CANVAS_HEIGHT);
        popupIcon = new ImageIcon("res/deadBird.png", "Flappy Bird Icon");
        running = false;
        
        reset();
        canvas.onMouseDown(event -> {
            control(true);
        });
        canvas.onKeyDown(event -> {
            control(event.getKey().equals(Key.SPACE));
        });

        Runnable mainGameplayLoop = () -> {
            if (running) {
                bird.move();
                if (pipesHandler.movePipes(bird)) points += 1;
                updatePointsText();
                if (!bird.isAlive()) gameOver();
            }
        };
        canvas.animate(mainGameplayLoop);
    }

    private void control(boolean flapped){
        if (flapped) {
            if (!running) running = true;
            bird.flap();
        }
    }

    private void reset() {
        points = 0;
        running = false;
        canvas.removeAll();

        // Draw things in the order they should be drawn

        // Add background image
        backgroundImg = new Image("background.jpeg");
        canvas.add(backgroundImg);
        // Add pipes handler
        pipesHandler = new PipesHandler();
        canvas.add(pipesHandler.getGraphic());
        // Add points text
        pointsText = new GraphicsText("" + points, 22, 45);
        pointsText.setFillColor(Color.WHITE);
        pointsText.setStrokeColor(Color.BLACK);
        pointsText.setStrokeWidth(2);
        pointsText.setFontSize(50);
        canvas.add(pointsText);
        // Add ground rectangle
        groundRect = new Rectangle(0, GROUND_Y + 40, CANVAS_WIDTH, CANVAS_HEIGHT - GROUND_Y);
        groundRect.setStroked(false); 
        canvas.add(groundRect);
        // Add bird
        bird = new Bird();
        canvas.add(bird.getGraphic());
    }

    private void gameOver() { // TODO add falling bird animation after hitting a pipe :)
        updatePointsText();
        boolean playAgain = (JOptionPane.showConfirmDialog(
            null,
            "Game Over! You earned " + points + " points!\nPlay again?",
            "Game Over!", JOptionPane.YES_NO_OPTION, 0, popupIcon) == 0);
        if (playAgain) reset();
        else {
            running = false;
            canvas.closeWindow();
        }
    }

    private void updatePointsText() {
        pointsText.setText("" + points);
        canvas.draw();
    }

    //TODO: make toString() better
    @Override
    public String toString() {
        return canvas.toString();
    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}
