package interfaces;

import geometry.geometryPrimitives.Velocity;
import geometry.shapes.Block;

import java.util.List;

/**
 * The LevelInformation interface represents the information of a game level.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * Returns the initial velocities of the balls.
     *
     * @return the initial velocities of the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     *
     * @return the level name.
     */
    String levelName();

    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite.
     */
    Sprite getBackground();

    /**
     * Returns the blocks that make up the level.
     *
     * @return the blocks of the level.
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed
     * before the level is considered to be cleared.
     *
     * @return the number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}
