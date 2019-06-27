import java.awt.Font;
import java.awt.event.KeyEvent;
/******************************************************************************
 * Bouncing square.
 *
 * @author Anthony Rodriguez
 * @since 2019-05-30
 ******************************************************************************/
public class Game {
    final static double HALF_SCREEN_LENGTH = 1.0;
    private final static int GAME_WINNING_POINTS = 10;
    private static boolean game_over = false;
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
        if (!game_over) { StdDraw.filledSquare(square.getX(), square.getY(), square.getHalfLength()); }
        drawCenterDivider();
        StdDraw.text(-0.5, 0.85, Integer.toString(opponentPoints));
        StdDraw.text(0.5, 0.85, Integer.toString(playerPoints));
        if (game_over) { drawWinnerMessage(); }
        StdDraw.show();
        StdDraw.pause(7);
    }

    /**
     * Draws the center divider on the screen
     */
    private static void drawCenterDivider() {
        double dividerWidth = 0.005;
        double dividerHeight = 0.02;
        double y = -HALF_SCREEN_LENGTH;
        while (y < HALF_SCREEN_LENGTH) {
            StdDraw.filledRectangle(0.0, y, dividerWidth, dividerHeight);
            y += 0.1;
        }
    }

    /**
     * Determines whether or not the game is over.
     * @return True if game is over, false otherwise.
     */
    private static boolean isGameOver() {
        return playerPoints == GAME_WINNING_POINTS ||
               opponentPoints == GAME_WINNING_POINTS;
    }

    /**
     * Shows message "Player Wins" on winning side of screen.
     */
    private static void drawWinnerMessage() {
        if (playerPoints == GAME_WINNING_POINTS) {
            StdDraw.text(0.5, 0.65, "Player Wins");
        } else {
            StdDraw.text(-0.5, 0.65, "Player Wins");
        }
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
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 45));
        Paddle player = new Paddle(0.9);
        Paddle opponent = new Paddle(-0.9);
        Square square = new Square();
        CollisionDetector cd = new CollisionDetector();
        while(true) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP) ||
                StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                player.moveUp();
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) ||
                       StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                player.moveDown();
            }
            if (!game_over) {
                // Move the AI opponent
                if (opponent.getY() > square.getY()) { opponent.moveDown(); }
                if (opponent.getY() < square.getY()) { opponent.moveUp(); }
                // Move the square
                square.moveX();
                square.moveY();

                /*
                If the square is in the left side of the screen, check if it
                hits the paddle on the left.
                If the square is in the right side of the scree, check if it
                hits the paddle on the right.
                The middle of the screen's x-value is 0.
                */
                if (square.getX() > 0) { cd.paddleCollision(square, player); }
                else if (square.getX() < 0) { cd.paddleCollision(square, opponent); }

                // Check if the game is over
                game_over = isGameOver();

                /*
                If a point is scored and the game is not over then create a
                new square, otherwise don't.
                 */
                if (cd.pointScored(square)  && !game_over) {
                    square = new Square();
                } else if (game_over) {
                    square = null;
                }
            }
            Game.draw(square, player, opponent);
        }
    }
}