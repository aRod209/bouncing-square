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
        double screenLen = Game.HALF_SCREEN_LENGTH;
        if (s.getX() <= -screenLen-3) {
            Game.playerPoints += 1;
            return true;
        } else if (s.getX() >= screenLen+3) {
            Game.opponentPoints += 1;
            return true;
        } else if (s.topBorder() + s.getVY() >= screenLen ||
                   s.bottomBorder() + s.getVY() <= -screenLen) {
            s.reverseVY();
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