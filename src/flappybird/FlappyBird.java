package flappybird;

import edu.macalester.graphics.CanvasWindow;

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
        canvas.draw();

    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}
