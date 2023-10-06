package listeners;

import utilities.Counter;
import game.GameLevel;
import geometry.shapes.Ball;
import geometry.shapes.Block;
import interfaces.HitListener;

/**
 * The BlockRemover class is responsible for removing blocks from the game when they are hit by a ball.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructs a new BlockRemover.
     *
     * @param game            the game to remove blocks from
     * @param removedBlocks the counter to track the remaining blocks in the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Handles the hit event when a block is hit by a ball.
     * Removes the block from the game, removes this listener from the block,
     * and decreases the count of remaining blocks.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
    }
}
