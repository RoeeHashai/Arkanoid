package listeners;

import utilities.Counter;
import game.GameLevel;
import geometry.shapes.Ball;
import geometry.shapes.Block;
import interfaces.HitListener;

/**
 * The BallRemover class is responsible for removing balls from the game when they hit a block.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructs a new BallRemover.
     *
     * @param game           the game to remove balls from
     * @param remainingBalls the counter to track the remaining balls in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Manages the hit event when a block is hit by a ball.
     * Removes the ball from the game and decreases the remaining balls count.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}