package interfaces;

import biuoop.DrawSurface;

/**
 * The Animation interface represents an animation in the game.
 * An animation consists of multiple frames that are displayed on the screen.
 */
public interface Animation {
    /**
     * Perform one frame of the animation.
     *
     * @param d the DrawSurface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}
