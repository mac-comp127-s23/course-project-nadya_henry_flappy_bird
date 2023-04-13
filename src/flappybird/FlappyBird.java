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
        canvas.onClick(event -> bird.flap());
        canvas.onKeyDown(event -> {
            if (event.getKey().equals(Key.SPACE)) bird.flap();
        });

        Runnable mainGameplayLoop = () -> {
            bird.move();
        };
        canvas.animate(mainGameplayLoop);

    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}
