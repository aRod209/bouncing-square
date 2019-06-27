/******************************************************************************
 * Class used to detect collisions
 *
 * @author Anthony Rodriguez
 * @since 2019-05-30
 *****************************************************************************/
public class CollisionDetector {

    /**
     * Detects a point being scored when square goes off the side of
     * the screen.
     * @param s The bouncing square object
     * @return true if point scored, false otherwise.
     */
    public boolean pointScored(Square s) {
        double screenLen = Pong.HALF_SCREEN_LENGTH;
        /*
        if the absolute value if the square's x-coordinate is more than 3
        units off the screen, a point is scored.
         */
        if (Math.abs(s.getX()) >= screenLen + 3) {
            if (s.getX() > 0) { Pong.opponentPoints += 1; }
            else { Pong.playerPoints += 1; }
            return true;
        }

        // Check if square hit top or bottom of screen.
        if (s.topBorder() + s.getVY() >= screenLen ||
                   s.bottomBorder() + s.getVY() <= -screenLen) {
            s.reverseVY();
            SoundGenerator.borderCollisionSound(s);
        }
        return false;
    }

    /**
     * Determines a collision and controls how the square reacts to the collision.
     * @param s The square.
     * @param p The paddle
     * @return Boolean value indicating a collision.
     */
    public void paddleCollision(Square s, Paddle p) {
        if (headOnCollision(s, p) || clipCollision(s, p)) {
            s.reverseVX();
            s.increaseVX();

            // update vy
            double distance = Math.abs(s.getY() - p.getY());
            double yVel;
            if (distance <= p.getrY()) {
                yVel = (distance / p.getrY()) * s.getVX();
            } else {
                yVel = s.getVX();
            }

            s.setVY(yVel);

            if ((s.getVY() < 0 && s.getY() > p.getY()) ||
                (s.getVY() > 0 && s.getY() < p.getY()))
            { s.reverseVY(); }
            SoundGenerator.paddleCollisionSound();
        }
    }

    /**
     * Detects if square is colliding with a paddle head on.
     * @param s The square.
     * @param p A paddle.
     * @return True if collision detected, false otherwise.
     */
    private boolean headOnCollision(Square s, Paddle p) {
        return (((s.getVX() > 0 && s.getX() < p.getX() && s.rightBorder() >= p.leftBorder()) ||
                (s.getVX() < 0 && s.getX() > p.getX() && s.leftBorder() <= p.rightBorder())) &&
                ((s.bottomBorder() >= p.bottomBorder() && s.topBorder() <= p.topBorder()) ||
                (s.bottomBorder() <= p.topBorder() && s.topBorder() > p.topBorder()) ||
                (s.topBorder() >= p.bottomBorder() && s.bottomBorder() < p.bottomBorder())));
    }

    /**
     * This is not a head-on collision, but a collision where the square
     * clips an upper or lower corner of the paddle.
     * @param s The square.
     * @param p A paddle.
     * @return True if collision detected, false otherwise.
     */
    private boolean clipCollision(Square s, Paddle p) {
        return ((s.bottomBorder() <= p.topBorder() && s.topBorder() > p.topBorder()) ||
               (s.topBorder() >= p.bottomBorder() && s.bottomBorder() < p.bottomBorder())) &&
               ((s.rightBorder() >= p.rightBorder() && s.leftBorder() < p.rightBorder() && s.leftBorder() > p.leftBorder()) ||
               (s.leftBorder() <= p.leftBorder() && s.rightBorder() > p.leftBorder() && s.rightBorder() < p.rightBorder()));
    }
}