/**
 * Class used for game audio
 *
 * @author Anthony Rodriguez
 * @since 2019-06-26
 */
public class SoundGenerator {

    /**
     * Generates sound when the square collides with a paddle.
     */
    public static void paddleCollisionSound() {
        StdAudio.play("sounds/Beep1.wav");
    }

    /**
     * Generates sound when the square collides with the top
     * or bottom border on the screen.
     * @param s
     */
    public static void borderCollisionSound(Square s) {
        if (Math.abs(s.getX()) <1) { StdAudio.play("sounds/Beep2.wav"); }
    }
}
