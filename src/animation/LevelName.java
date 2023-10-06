package animation;

import biuoop.DrawSurface;
import geometry.geometryPrimitives.Point;
import interfaces.Sprite;

/**
 * LevelName class represents the display of the level name as a sprite in the game.
 */
public class LevelName implements Sprite {
    private String levelName;
    private Point point;
    private static final int TEXT_X = 600;
    private static final int TEXT_Y = 14;
    private static final int TEXT_FONT_SIZE = 15;

    /**
     * Constructs a new LevelName with the specified level name.
     *
     * @param levelName the level name
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
        point = new Point(TEXT_X, TEXT_Y);
    }

    /**
     * Draws the level name on the given DrawSurface.
     *
     * @param drawSurface the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.drawText((int) point.getX(), (int) point.getY(), "Level Name: " + levelName, TEXT_FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }
}
