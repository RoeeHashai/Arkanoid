package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * AnimationRunner class is responsible for running animations.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructs an AnimationRunner object.
     *
     * @param gui     the GUI object used to display animations
     * @param sleeper the Sleeper object used to control the frame rate
     */
    public AnimationRunner(GUI gui, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = sleeper;
    }

    /**
     * Runs the animation.
     *
     * @param animation the Animation object to be run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
