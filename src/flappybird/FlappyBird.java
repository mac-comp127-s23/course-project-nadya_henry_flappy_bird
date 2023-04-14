package flappybird;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.events.Key;

public class FlappyBird {

    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private int points;
    private GraphicsText pointsText; //TODO add to UML doc

    private Bird bird;
    private PipesHandler pipesHandler;

    public FlappyBird() {
        canvas = new CanvasWindow("Flappy Bird", CANVAS_WIDTH, CANVAS_HEIGHT);

        //TODO most of the code below should be moved to a Reset Game function. :)

        points = 0;
        pointsText = new GraphicsText("" + points, 22, 45);
        pointsText.setFontSize(40);
        canvas.add(pointsText);

        bird = new Bird();
        canvas.add(bird.getGraphic());
        canvas.onMouseDown(event -> bird.flap());
        canvas.onKeyDown(event -> {
            if (event.getKey().equals(Key.SPACE)) bird.flap();
        });

        pipesHandler = new PipesHandler();
        canvas.add(pipesHandler.getGraphic());


        Runnable mainGameplayLoop = () -> {
            bird.move();
            if (pipesHandler.movePipes(bird)) points += 1;
            updatePointsText();
        };
        canvas.animate(mainGameplayLoop);

    }

    //TODO make a reset game function and think about what that needs to do

    private void updatePointsText() { //TODO add to uml
        pointsText.setText("" + points);
    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}
