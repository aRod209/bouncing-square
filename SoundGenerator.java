public class SoundGenerator {

    public static void paddleCollisionSound() {
        StdAudio.play("Beep1.wav");
    }

    public static void borderCollisionSound() {
        StdAudio.play("Beep2.wav");
    }

    public static void main(String[] args) {
        paddleCollisionSound();
    }
}
