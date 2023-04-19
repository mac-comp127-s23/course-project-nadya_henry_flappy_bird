package flappybird;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.Image;
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
    private Image groundImg;
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

        Runnable mainGameplayLoop = () -> { //TODO add an idle animation before the user clicks or presses?
            if (running) {
                bird.move();
                if (pipesHandler.movePipes(bird)) points += 1;
                updatePointsText();
                if (!bird.isAlive()) gameOver();
            } // TODO animate the ground
        };
        canvas.animate(mainGameplayLoop);

    }

    /*
     * Runs when user presses the space bar or clicks the canvas.
     */
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
        // Add ground 
        groundImg = new Image("testGround.png"); //TODO clean up res folder (delete unused and rename used images)
        groundImg.setPosition(0,GROUND_Y + 40);
        canvas.add(groundImg);

        // Add bird
        bird = new Bird();
        canvas.add(bird.getGraphic());
    }

    private void gameOver() {
        while (bird.animateDeath()) { // Have bird fall to ground.
            canvas.draw();
        }
        canvas.draw();

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

    @Override
    public String toString() {
        return "A Game of Flappy Bird with score " + points;
    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}
