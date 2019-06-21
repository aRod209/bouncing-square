import java.awt.event.KeyEvent;

/******************************************************************************
 * Bouncing square.
 *
 * @author Anthony Rodriguez
 * @since 2019-05-30
 ******************************************************************************/

public class Game {
    public final static double HALF_SCREEN_LENGTH = 1.0;
    static int playerPoints = 0;
    static int opponentPoints= 0;

    /**
     * Draws objects to the screen.
     * @param square The bouncing square on the screen.
     */
    private static void draw(Square square, Paddle player, Paddle opponent) {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.filledRectangle(player.getX(), player.getY(), player.getrX(), player.getrY());
        StdDraw.filledRectangle(opponent.getX(), opponent.getY(), opponent.getrX(), opponent.getrY());
        StdDraw.filledSquare(square.getX(), square.getY(), square.getHalfLength());
        StdDraw.text(-0.5, 0.9, Integer.toString(opponentPoints));
        StdDraw.text(0.5, 0.9, Integer.toString(playerPoints));
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
        Paddle player = new Paddle(0.9);
        Paddle opponent = new Paddle(-0.9);
        CollisionDetector cd = new CollisionDetector();
        while(true) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) { player.moveUp(); }
            else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) { player.moveDown(); }
            if (opponent.getY() > square.getY()) { opponent.moveDown(); }
            if (opponent.getY() < square.getY()) { opponent.moveUp(); }
            square.moveX();
            square.moveY();
            cd.borderCollision(square);
            cd.paddleCollision(square, player);
            cd.paddleCollision(square, opponent);
            Game.draw(square, player, opponent);
        }
    }
}