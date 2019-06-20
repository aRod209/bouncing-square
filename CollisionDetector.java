/******************************************************************************
 * Class used to detect collisions
 *
 * @author Anthony Rodriguez
 * @since 2019-05-30
 *****************************************************************************/
public class CollisionDetector {

    /**
     * Detects a collision with a game-screen border
     * @param s The bouncing square object
     * @return true if border collision, false otherwise.
     */
    public boolean borderCollision(Square s) {
        double screenLen = Game.HALF_SCREEN_LENGTH;
        if (s.leftBorder() <= -screenLen || s.rightBorder() >= screenLen) {
            s.reverseVX();
            return true;
        } else if (s.topBorder() >= screenLen || s.bottomBorder() <= -screenLen) {
            s.reverseVY();
            return true;
        }
        return false;
    }

    /**
     * Determines a collision and controls how the square reacts to the collision.
     * @param s The square.
     * @param p The paddle
     * @return Boolean value indicating a collision.
     */
    public boolean paddleCollision(Square s, Paddle p) {
        if ( collisionDetected(s, p) ) {
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

            return true;
        }

        return false;
    }

    /**
     * Detects a collision between the square and paddle.
     * @param s  The square.
     * @param p The paddle.
     * @return True if collision, false otherwise.
     */
    private boolean collisionDetected(Square s, Paddle p) {
        return ((s.getVX() > 0 && s.getX() < p.getX() && s.rightBorder() >= p.leftBorder()) ||
                (s.getVX() < 0 && s.getX() > p.getX() && s.leftBorder() <= p.rightBorder())) &&
                ((s.bottomBorder() >= p.bottomBorder() && s.topBorder() <= p.topBorder()) ||
                (s.bottomBorder() <= p.topBorder() && s.topBorder() > p.topBorder()) ||
                (s.topBorder() >= p.bottomBorder() && s.bottomBorder() < p.bottomBorder()));
    }
}