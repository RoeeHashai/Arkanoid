package animation;

import biuoop.DrawSurface;
import interfaces.Animation;
import collections.SpriteCollection;

import java.awt.Color;

/**
 * CountdownAnimation class represents an animation that displays a countdown on the screen before every level.
 */
public class CountdownAnimation implements Animation {
    private static final String[] COUNTDOWN_TEXT = {"0", "1", "2", "3"};
    private static final int X_FIX = 20;
    private static final int Y_FIX = 60;
    private static final int FONT = 80;
    private SpriteCollection spriteCollection;
    private boolean stop;
    private long startTime;
    private double numOfSeconds;
    private int countFrom;

    /**
     * Constructs a CountdownAnimation object with the given SpriteCollection.
     *
     * @param numOfSeconds     the number of seconds to count
     * @param countFrom        the number to start counting from
     * @param spriteCollection the SpriteCollection to be displayed during the countdown
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection spriteCollection) {
        this.spriteCollection = spriteCollection;
        this.stop = false;
        this.startTime = System.currentTimeMillis();
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.spriteCollection.drawAllOn(d);

        long elapsedTime = System.currentTimeMillis() - this.startTime;
        double secondsPassed = elapsedTime / 1000.0;

        int currentCount = (int) (secondsPassed / numOfSeconds);
        if (currentCount > countFrom) {
            this.stop = true;
            return;
        }
        d.setColor(Color.BLUE);
        d.drawText((d.getWidth() / 2) - X_FIX, (d.getHeight() / 2) + Y_FIX, COUNTDOWN_TEXT[countFrom - currentCount],
                FONT);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
