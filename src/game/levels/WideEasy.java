package game.levels;

import geometry.geometryPrimitives.Point;
import geometry.geometryPrimitives.Rectangle;
import geometry.geometryPrimitives.Velocity;
import geometry.shapes.Block;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * WideEasy class represents the information and configuration of the "WideEasy" level.
 */
public class WideEasy implements LevelInformation {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 25;
    public static final int PADDLE_WIDTH = 600;
    public static final int PADDLE_SPEED = 8;
    public static final int BALL_SIZE = 5;

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            ballsVelocity.add(Velocity.fromAngleAndSpeed(290 + ((i + 3) * 10), 6));
        }
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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Rectangle backgroundRec = new Rectangle(new Point(0, 0), SCREEN_WIDTH, SCREEN_HEIGHT + 2 * BALL_SIZE + 1);
        return new Block(backgroundRec, Color.WHITE);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int yRow = 250;
        int mostRightBlockX = 720;
        int pauseIndex = mostRightBlockX;
        Color[] colors = {Color.CYAN, Color.pink, Color.blue, Color.yellow, Color.orange, Color.red, Color.green};
        int colorIndex = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                Block block = new Block(new Rectangle(new Point(mostRightBlockX, yRow), BLOCK_WIDTH, BLOCK_HEIGHT),
                        colors[colorIndex]);
                blocks.add(block);
                mostRightBlockX -= BLOCK_WIDTH - 1;
            }
            colorIndex++;
            if (i == 2) {
                pauseIndex = mostRightBlockX;
                mostRightBlockX = (mostRightBlockX - 3 * BLOCK_WIDTH) - 1;
            }
        }
        mostRightBlockX = pauseIndex - 1;
        for (int i = 0; i < 3; i++) {
            Block block =
                    new Block(new Rectangle(new Point(i == 2 ? mostRightBlockX - 2 : mostRightBlockX, yRow),
                            i == 2 ? BLOCK_WIDTH + 2 : BLOCK_WIDTH, BLOCK_HEIGHT), colors[colorIndex]);
            blocks.add(block);
            mostRightBlockX -= BLOCK_WIDTH - 1;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
