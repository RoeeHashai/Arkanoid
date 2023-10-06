package interfaces;

import biuoop.DrawSurface;

/**
 * The Sprite interface represents an object that can be drawn on a GUI environment and can change over time.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param drawSurface the DrawSurface object to draw the sprite on.
     */
    void drawOn(DrawSurface drawSurface);

    /**
     * Notifies the sprite that a unit of time has passed.
     */
    void timePassed();
}