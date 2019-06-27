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
     * or bottom border.
     */
    public static void borderCollisionSound() {
        StdAudio.play("sounds/Beep2.wav");
    }
}
