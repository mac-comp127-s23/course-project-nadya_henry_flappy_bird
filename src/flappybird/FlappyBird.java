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
        running = true;
        
        reset();
        canvas.onMouseDown(event -> bird.flap());
        canvas.onKeyDown(event -> {
            if (event.getKey().equals(Key.SPACE)) bird.flap();
        });

        Runnable mainGameplayLoop = () -> {
            bird.move();
            if (pipesHandler.movePipes(bird)) points += 1;
            updatePointsText();
            if (!bird.isAlive() && running) gameOver();
        };
        canvas.animate(mainGameplayLoop);
    }

    private void reset() {
        points = 0;
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

        // Add pipes handler
        pipesHandler = new PipesHandler();
        canvas.add(pipesHandler.getGraphic());
        // Add bird
        bird = new Bird();
        canvas.add(bird.getGraphic());
        // Add points text
        pointsText = new GraphicsText("" + points, 22, 45);
        pointsText.setFontSize(40);
        canvas.add(pointsText);
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
