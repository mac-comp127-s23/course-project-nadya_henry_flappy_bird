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
    private Image titleImg; // TODO: Add to UML
    private Image startButtonImg; // TODO: Add to UML
    private boolean pointsVisible = false; // TODO: Add to UML
    


    private Bird bird;
    private PipesHandler pipesHandler;

    public FlappyBird() {

        canvas = new CanvasWindow("Flappy Bird", CANVAS_WIDTH, CANVAS_HEIGHT);
        popupIcon = new ImageIcon("res/deadBird.png", "Flappy Bird Icon");
        running = false;
        reset();
        drawMenu();
        togglePointsVisibility(false);

        canvas.animate(() -> {
            birdFloatAnimationLoop();
            groundAnimationLoop();
            mainGameplayLoop();
        });
        canvas.onMouseDown(event -> control(true));
        canvas.onKeyDown(event -> {
            control(event.getKey().equals(Key.SPACE));
        });
    }

    private void birdFloatAnimationLoop() { // TODO: Add to UML
        if (!running) {
            bird.birdFloat();
        }
    }

    private void groundAnimationLoop() { // TODO: Add to UML
        moveGround();
    }

    private void mainGameplayLoop() {   // TODO: Add to UML
        if (running) {
            bird.move(); 
            if (pipesHandler.movePipes(bird)) points += 1;
            updatePointsText();
            if (!bird.isAlive()) gameOver();
        }
    }

    private void togglePointsVisibility(boolean visible) { // TODO: Add to UML
        if (visible && !pointsVisible) {
            canvas.add(pointsText);
            pointsVisible = true;
        } else if (!visible && pointsVisible) {
            canvas.remove(pointsText);
            pointsVisible = false;
        }
    }

    private void drawMenu () { // TODO: Add to UML
		// Title
		titleImg = new Image("logo.png");
        titleImg.setCenter(CANVAS_WIDTH / 2, CANVAS_WIDTH / 4);
        titleImg.setScale(1.5);
        canvas.add(titleImg);
        // Add start button image
        startButtonImg = new Image("Start.png");
        startButtonImg.setPosition(CANVAS_WIDTH/2 - startButtonImg.getWidth()/2, 685);
        canvas.add(startButtonImg);
    }

    public void moveGround() { // TODO: Add to UML
        double groundX = groundImg.getX(); 
        double decrement = PipesHandler.PIPE_VELOCITY;
        groundX -= decrement;
        if (groundX < -groundImg.getWidth()/2) {
            groundX = 0;
            }
        groundImg.setX(groundX);
    }

    /*
     * Runs when user presses the space bar or clicks the canvas.
     */
    private void control(boolean flapped){
        if (!running) {
            canvas.remove(titleImg);
            canvas.remove(startButtonImg);
            togglePointsVisibility(true);
            running = true;
        }
        if (flapped) bird.flap();
    }

    private void reset() { // TODO: Let points appear when the user clicks to replay the game.
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
        pointsText = new GraphicsText("" + points, 22, 50);
        pointsText.setStrokeWidth(2);
        pointsText.setFontSize(60);
        pointsText.setFillColor(Color.WHITE);
        // canvas.add(pointsText);
        togglePointsVisibility(true);
    
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
        if (playAgain) {
            reset();
        }
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



