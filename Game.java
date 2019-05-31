/******************************************************************************
* Bouncing square.
* 
* @author Anthony Rodriguez
* @since 2019-05-30
******************************************************************************/

public class Game {
	public final static double HALF_SCREEN_LENGTH = 1.0;
	private static int bounces = 0;

	/**
	 * Draws objects to the screen.
	 * @param square The bouncing square on the screen.
	 */
	private static void draw(Square square) {
		StdDraw.clear(StdDraw.BLACK);
		StdDraw.filledSquare(square.getX(), square.getY(), square.getHalfLength());
		StdDraw.text(0.0, 0.9, "Bounces: " + bounces);
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
		CollisionDetector cd = new CollisionDetector();
		while(true) {
			square.moveX();
			square.moveY();
			if (cd.borderCollision(square)) { bounces += 1; }
			Game.draw(square);
		}
	}
}