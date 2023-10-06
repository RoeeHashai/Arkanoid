package animation;

import biuoop.DrawSurface;
import interfaces.Animation;
import utilities.Counter;

/**
 * EndScreen class represents an animation that displays the end screen of the game.
 */
public class EndScreen implements Animation {
    private static final int X_TEXT = 175;
    private static final int TEXT_SIZE = 32;
    private Counter score;
    private boolean isWinner;

    /**
     * Constructs an EndScreen object with the given score and winning status.
     *
     * @param score    the score achieved in the game
     * @param isWinner indicates whether the player has won the game or not
     */
    public EndScreen(Counter score, boolean isWinner) {
        this.score = score;
        this.isWinner = isWinner;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String toDisplay;
        if (isWinner) {
            toDisplay = "You Win! Your score is " + score.getValue();
        } else {
            toDisplay = "Game Over. Your score is " + score.getValue();
        }
        d.drawText(X_TEXT, d.getHeight() / 2, toDisplay, TEXT_SIZE);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
