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
	}