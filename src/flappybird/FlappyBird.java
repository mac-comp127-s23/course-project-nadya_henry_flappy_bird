package flappybird;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;

public class FlappyBird {

    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private int points;

    private Bird bird;
    private PipesHandler pipesHandler;

    public FlappyBird() {
        canvas = new CanvasWindow("Flappy Bird", CANVAS_WIDTH, CANVAS_HEIGHT);

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
            pipesHandler.movePipes(bird);
        };
        canvas.animate(mainGameplayLoop);

    }

    //TODO make a reset game function and think about what that needs to do

    public static void main(String[] args) {
        new FlappyBird();
    }
}
