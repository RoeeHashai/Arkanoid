package interfaces;

import geometry.shapes.Ball;
import geometry.shapes.Block;

/**
 * HitListener interface represents a hit listener for objects that can be hit.
 */
public interface HitListener {
    /**
     * Called when the object being hit is hit by a ball.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that is hitting the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
