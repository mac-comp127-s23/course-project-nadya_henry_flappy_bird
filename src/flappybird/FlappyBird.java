package flappybird;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.Image;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/*
 * An object that creates and handles a game of Flappy Bird
 *  by Nadya Konadu, Kynan Desouza-Chen, and Henry Heyden
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
    private Image titleImg;
    private Image startButtonImg;

    private Bird bird;
    private PipesHandler pipesHandler;

    public FlappyBird() {
        canvas = new CanvasWindow("Flappy Bird", CANVAS_WIDTH, CANVAS_HEIGHT);
        popupIcon = new ImageIcon("res/deadBird.png", "Flappy Bird Icon");
        running = false;
        reset();

        canvas.animate(() -> {
            moveGround();
            if (running) mainGameplayLoop();
            else bird.birdFloat();
        });
        canvas.onMouseDown(event -> control(true));
        canvas.onKeyDown(event -> {
            control(event.getKey().equals(Key.SPACE));
        });
    }

    /*
     * Runs every frame, handles four distinct tasks:
     * 1. Tell the bird to update its position
     * 2. Tell the PipesHandler to update the frame (checking for death and points)
     * 3. Update the points text to the correct amount of points
     * 4. Run gameOver() if the bird has died.
     */
    private void mainGameplayLoop() {
        bird.move(); 
        if (pipesHandler.movePipes(bird)) points += 1;
        updatePointsText();
        if (!bird.isAlive()) gameOver();
    }

    /*
     * Draw the start menu on the canvas
     */
    private void drawMenu () {
		// Title
		titleImg = new Image("logo.png");
        titleImg.setCenter(CANVAS_WIDTH / 2, CANVAS_WIDTH / 4);
        titleImg.setScale(1.5);
        canvas.add(titleImg);
        // Add start button image
        startButtonImg = new Image("Start.png");
        startButtonImg.setPosition(CANVAS_WIDTH/2 - startButtonImg.getWidth()/2, 685);
        canvas.add(startButtonImg);
        // Hide pointsText
        pointsText.setFilled(false);
        pointsText.setStroked(false);
        canvas.draw();
    }

    /*
     * Runs every frame, moves the groundImg
     * to create the illusion that the bird is moving forward.
     */
    public void moveGround() {
        double groundX = groundImg.getX();
        groundX -= PipesHandler.PIPE_VELOCITY;
        if (groundX < -groundImg.getWidth()/2) groundX = 0;
        groundImg.setX(groundX);
    }

    /*
     * Runs when user presses the space bar or clicks the canvas.
     * Tells the bird to flap
     * Also, if this is the first user input, removes the start menu from the canvas
     * and begins the game.
     */
    private void control(boolean flapped){
        if (!running) {
            canvas.remove(titleImg);
            canvas.remove(startButtonImg);
            pointsText.setFilled(true);
            pointsText.setStroked(true);
            running = true;
        }
        if (flapped) bird.flap();
    }

    /*
     * Initializes important instance variables and draws them to the canvas.
     */
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
        pointsText = new GraphicsText("" + points, 22, 69);
        pointsText.setFontSize(69);
        pointsText.setStrokeWidth(3);
        pointsText.setFillColor(Color.WHITE);
        pointsText.setStrokeColor(Color.BLACK);
        canvas.add(pointsText);
    
        // Add ground 
        groundImg = new Image("ground.png");
        groundImg.setPosition(0,GROUND_Y + 40);
        canvas.add(groundImg);
        // Add bird
        bird = new Bird();
        canvas.add(bird.getGraphic());

        drawMenu();
    }

    /*
     * Runs when the bird touches the ground or a pipe
     * Handles the death animation, the death popup screen, and restarting the game
     */
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

    /*
     * Change the text in the top left to the number of points the player has
     */
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



