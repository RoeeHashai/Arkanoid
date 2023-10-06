package animation;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * PauseScreen class represents an animation that displays a pause screen.
 */
public class PauseScreen implements Animation {
    private static final int X = 120;
    private static final int SIZE = 32;

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(X, d.getHeight() / 2, "paused -- press space to continue", SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
