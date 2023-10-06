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
 * Green3 class represents the information and configuration of the "Green3" level.
 */
public class Green3 implements LevelInformation {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_SPEED = 7;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 25;
    public static final int BALL_SIZE = 5;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballsVelocity = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            ballsVelocity.add(Velocity.fromAngleAndSpeed((340 + ((i * 4) * 10)) % 360, 7));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Rectangle backgroundRec = new Rectangle(new Point(0, 0), SCREEN_WIDTH, SCREEN_HEIGHT + 2 * BALL_SIZE + 1);
        return new Block(backgroundRec, new Color(0, 100, 0));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int mostUpRowHeight = 120;
        int mostRightBlockCoordinate = 720;
        int maxInRow = 12;
        int rowAmount = 5;
        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.blue, Color.WHITE};
        for (int i = 0; i < rowAmount; i++) {
            for (int j = 0; j < maxInRow; j++) {
                Block block = new Block(new Rectangle(new Point(mostRightBlockCoordinate, mostUpRowHeight), BLOCK_WIDTH,
                        BLOCK_HEIGHT), colors[i]);
                blocks.add(block);
                mostRightBlockCoordinate -= BLOCK_WIDTH;
            }
            maxInRow--;
            mostUpRowHeight += BLOCK_HEIGHT;
            mostRightBlockCoordinate = 720;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
