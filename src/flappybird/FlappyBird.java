package flappybird;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class FlappyBird {

    public static final int CANVAS_WIDTH = 640;
    public static final int CANVAS_HEIGHT = 980;
    public static final int GROUND_Y = 750;

    private CanvasWindow canvas;
    private int points;
    private GraphicsText pointsText;
    private Rectangle groundRect;
    private Image backgroundImg;
    private ImageIcon popupIcon; // TODO add to uml
    private boolean running; // TODO add to uml

    private Bird bird;
    private PipesHandler pipesHandler;

    public FlappyBird() {
        canvas = new CanvasWindow("Flappy Bird", CANVAS_WIDTH, CANVAS_HEIGHT);
        popupIcon = new ImageIcon("res/deadBird.png", "Flappy Bird Icon");
        running = false;
        
        reset();
        canvas.onMouseDown(event -> {
            if (!running) running = true;
            bird.flap();
        } );
        canvas.onKeyDown(event -> {
            if (event.getKey().equals(Key.SPACE)) {
                if (!running) running = true;
                bird.flap();
            }
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

    private void reset() {
        points = 0;
        canvas.removeAll();


        // Draw things in the order they should be drawn

        // Add background image
        backgroundImg = new Image("flappyBirdBckg2.jpeg");
        canvas.add(backgroundImg);
        // Add pipes handler
        pipesHandler = new PipesHandler();
        canvas.add(pipesHandler.getGraphic());
        // Add points text
        pointsText = new GraphicsText("" + points, 22, 45);
        pointsText.setFontSize(40);
        canvas.add(pointsText);
        // Add ground rectangle
        groundRect = new Rectangle(0, GROUND_Y + 40, CANVAS_WIDTH, CANVAS_HEIGHT - GROUND_Y);
        groundRect.setStroked(false); 
        groundRect.setFillColor(Pipe.COLOR);
        canvas.add(groundRect);
        // Add bird
        bird = new Bird();
        canvas.add(bird.getGraphic());
    }

    private void gameOver() {
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
    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}
