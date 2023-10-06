package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * KeyPressStoppableAnimation class represents an animation that can be stopped by pressing a specified key.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed = true;

    /**
     * Constructs a KeyPressStoppableAnimation object with the given keyboard sensor, key, and animation.
     *
     * @param sensor    the KeyboardSensor used to detect key presses
     * @param key       the key that should stop the animation when pressed
     * @param animation the animation to be played
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed("p")) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
