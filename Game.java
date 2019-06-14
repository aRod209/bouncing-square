import java.awt.event.KeyEvent;

/******************************************************************************
 * Bouncing square.
 *
 * @author Anthony Rodriguez
 * @since 2019-05-30
 ******************************************************************************/

public class Game {
    public final static double HALF_SCREEN_LENGTH = 1.0;
    private static int bounces = 0;
    private static int collsions = 0;

    /**
     * Draws objects to the screen.
     * @param square The bouncing square on the screen.
     */
    private static void draw(Square square, Paddle paddle) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.filledRectangle(paddle.getX(), paddle.getY(), paddle.getrX(), paddle.getrY());
        StdDraw.filledSquare(square.getX(), square.getY(), square.getHalfLength());
        StdDraw.text(0.0, 0.9, "Border Collsions: " + bounces);
        StdDraw.text(0.0, 0.8, "Paddle Collisions: " + collsions);
        StdDraw.show();
        StdDraw.pause(7);
    }

    /**
     * Where the main game loop runs.
     * @param args args is not used.
     */
    public static void main(String[] args) {
        StdDraw.setXscale(-HALF_SCREEN_LENGTH, HALF_SCREEN_LENGTH);
        StdDraw.setYscale(-HALF_SCREEN_LENGTH, HALF_SCREEN_LENGTH);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.WHITE);
        Square square = new Square();
        Paddle paddle = new Paddle();
        CollisionDetector cd = new CollisionDetector();
        while(true) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) { paddle.moveUp(); }
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) { paddle.moveDown(); }
            square.moveX();
            square.moveY();
            if (cd.borderCollision(square)) { bounces += 1; }
            if(cd.paddleCollision(square, paddle)) { collsions += 1; }
            Game.draw(square, paddle);
        }
    }
}