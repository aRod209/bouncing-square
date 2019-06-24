/******************************************************************************
 * The paddle object that will collide with the square.
 *
 * @author Anthony Rodriguez
 * @since 2019-06-01
 *****************************************************************************/
public class Paddle {
    private double x;
    private double y = 0.0; // Coordinates
    private static double rx = 0.03, ry = 0.13; // Radii in the x and y directions;
    private static double v = 0.015;

    Paddle(double x) {
        this.x = x;
    }

    /**
     * Returns the x-coordinate of the center of the paddle.
     * @return x-coordinate
     */
    public double getX() { return x; }

    /**
     * Returns the y-coordinate of the center of the paddle.
     * @return y-coordinate
     */
    public double getY() { return y; }

    /**
     * Returns the half-width of the paddle.
     * @return the paddle's half-width
     */
    public double getrX() { return rx; }

    /**
     * Returns the half-length of the paddle.
     * @return the paddle's half-length
     */
    public double getrY() { return ry; }

    /**
     * Moves the paddle up.
     */
    public void moveUp() {
        if (y + ry < Game.HALF_SCREEN_LENGTH) { y += v; }
    }

    /**
     * Moves the paddle down.
     */
    public void moveDown() {
        if (y - ry > -Game.HALF_SCREEN_LENGTH) { y -= v; }
    }

    public double topBorder() {
        return y + ry;
    }

    public double bottomBorder() {
        return y - ry;
    }

    public double rightBorder() {
        return x + rx;
    }

    public double leftBorder() {
        return x - rx;
    }

}
