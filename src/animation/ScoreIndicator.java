package animation;

import biuoop.DrawSurface;
import geometry.geometryPrimitives.Point;
import interfaces.Sprite;
import utilities.Counter;

import java.awt.Color;

/**
 * The ScoreIndicator class represents a sprite that displays the current score.
 */
public class ScoreIndicator implements Sprite {
    private static final int INDICATOR_WIDTH = 800;
    private static final int INDICATOR_HEIGHT = 15;
    private static final int INDICATOR_X = 0;
    private static final int INDICATOR_Y = 0;
    private static final int TEXT_X = 375;
    private static final int TEXT_Y = 14;
    private static final int TEXT_FONT_SIZE = 15;

    private Counter score;
    private Point point;

    /**
     * Constructs a new ScoreIndicator with the specified score.
     *
     * @param score the score counter
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.point = new Point(TEXT_X, TEXT_Y);
    }

    @Override
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(Color.WHITE);
        drawSurface.fillRectangle(INDICATOR_X, INDICATOR_Y, INDICATOR_WIDTH, INDICATOR_HEIGHT);
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawRectangle(INDICATOR_X, INDICATOR_Y, INDICATOR_WIDTH, INDICATOR_HEIGHT);
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawText((int) point.getX(), (int) point.getY(), "Score: " + score.getValue(), TEXT_FONT_SIZE);
    }

    @Override
    public void timePassed() {

    }
}
