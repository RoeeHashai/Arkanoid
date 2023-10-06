package game.levels;

import geometry.geometryPrimitives.Point;
import geometry.geometryPrimitives.Velocity;
import geometry.shapes.Block;
import geometry.geometryPrimitives.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * DirectHit class represents the information and configuration of the "Direct Hit" level.
 */
public class DirectHit implements LevelInformation {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_SPEED = 7;
    public static final int X = 385;
    public static final int Y = 100;
    public static final int BLOCK_WIDTH = 35;
    public static final int BLOCK_HEIGHT = 35;
    public static final int BALL_SIZE = 5;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        ballsVelocity.add(Velocity.fromAngleAndSpeed(0, 7));
        return ballsVelocity;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Rectangle backgroundRec = new Rectangle(new Point(0, 0), SCREEN_WIDTH, SCREEN_HEIGHT + 2 * BALL_SIZE + 1);
        return new Block(backgroundRec, Color.BLACK);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(X, Y), BLOCK_WIDTH, BLOCK_HEIGHT), Color.RED);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
