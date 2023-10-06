package listeners;

import geometry.shapes.Ball;
import geometry.shapes.Block;
import interfaces.HitListener;
import utilities.Counter;

/**
 * The ScoreTrackingListener class is responsible for tracking the score when a block is hit by a ball.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener.
     *
     * @param scoreCounter the counter to track the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Handles the hit event when a block is hit by a ball.
     * Increases the current score.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}