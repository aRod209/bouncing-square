/******************************************************************************
 * The Square object that bounces on the screen
 *
 * @author Anthony Rodriguez
 * @since 2019-05-30
 *****************************************************************************/
public class Square {
    private double x = 0.0, y = 0.0;
    private double vx = 0.014, vy = 0.005;
    private double MAX_VX = 0.020;
    // The half-length is the length from the middle of the
    // square to the middle of one of the sides.
    private static double halfLength = 0.03;

    /**
     * Constructor that randomly chooses the initial direction of the square.
     */
    Square() {
        if (Math.random() < 0.5) { vx = -vx; }
        if (Math.random() < 0.5) { vy = -vy; }
        SoundGenerator.servingSound();
    }

    /**
     * Returns the x-coordinate of the square.
     * @return the square's x-coordinate.
     */
    public double getX() { return x; }

    /**
     * Returns the y-coordinate of the square.
     * @return the square's y-coordinate.
     */
    public double getY() { return y; }

    /**
     * Returns the velocity in the x-direction.
     * @return vx x-direction velocity.
     */
    public double getVX() { return vx; }

    /**
     * Returns the velocity in the y-direction.
     * @return vy y-direction velocity.
     */
    public double getVY() { return vy; }

    public void increaseVX() {
        if (Math.abs(vx) < MAX_VX) {
            if (vx < 0) { vx -= 0.001; }
            else { vx += 0.001; }
        }
    }

    /**
     * Returns the half-length of the square.
     * @return the length from the middle of the square to the middle of a side.
     */
    public double getHalfLength() { return halfLength; }

    /**
     * Increases speed of square in x-direction.
     */
    public void moveX() { x += vx; }

    /**
     * Increases speed of square in y-direction.
     */
    public void moveY() { y += vy; }

    /**
     * Reverses the x-velocity
     */
    public void reverseVX() { vx = -vx; }

    /**
     * Reverses the y-velocity.
     */
    public void reverseVY() { vy = -vy; }

    /**
     * Sets VY to formal parameter.
     * @param yVel the new y-direction velocity.
     */
    public void setVY(double yVel) { vy = yVel; }

    /**
     * Returns the y-coordinate of the square's top-border.
     * @return y-coordinate of the top-border.
     */
    public double topBorder() { return y + halfLength; }

    /**
     * Returns the y-coordinate of the square's bottom-border.
     * @return y-coordinate of the bottom-border.
     */
    public double bottomBorder() { return y - halfLength; }

    /**
     * Returns the x-coordinate of the square's left-border.
     * @return x-coordinate of the left-border.
     */
    public double leftBorder() { return x - halfLength; }

    /**
     * Returns the x-coordinate of the square's right-border.
     * @return x-coordinate of the right-border.
     */
    public double rightBorder() { return x + halfLength; }
}