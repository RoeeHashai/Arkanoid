package game;

import animation.CountdownAnimation;
import animation.AnimationRunner;
import animation.ScoreIndicator;
import animation.LevelName;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collections.SpriteCollection;
import geometry.shapes.Block;
import geometry.geometryPrimitives.Rectangle;
import geometry.geometryPrimitives.Point;
import geometry.shapes.Ball;
import geometry.shapes.Paddle;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import utilities.Counter;

import java.awt.Color;

/**
 * The Game class is responsible for managing the game environment, sprites, and GUI, as well as running the game loop.
 */
public class GameLevel implements Animation {
    // Globals
    public static final double LEFT_BOUND = 30;
    public static final int BALL_SIZE = 5;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final double BOUND_BLOCK_SIZE = 30;
    public static final int PADDLE_HEIGHT = 20;
    public static final int BACKGROUND_COLOR = 130;

    // Attributes
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboard;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;

    /**
     * Constructs a new Game.
     *
     * @param levelInformation the level information
     * @param score            the score of the player
     * @param ks               the KeyboardSensor
     * @param ar               the animation runner
     * @param gui              the gui
     */
    public GameLevel(LevelInformation levelInformation, Counter score, KeyboardSensor ks, AnimationRunner ar, GUI gui) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockCounter = new Counter();
        ballCounter = new Counter();
        this.score = score;
        this.gui = gui;
        runner = ar;
        this.keyboard = ks;
        this.levelInformation = levelInformation;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param collidable the collidable object to add.
     */
    public void addCollidable(Collidable collidable) {
        environment.addCollidable(collidable);
    }

    /**
     * Adds a sprite object to the game environment.
     *
     * @param sprite the sprite object to add.
     */
    public void addSprite(Sprite sprite) {
        sprites.addSprite(sprite);
    }

    /**
     * Returns the list of sprites currently in the game environment.
     *
     * @return the list of sprites currently in the game environment.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Returns the game environment object.
     *
     * @return the game environment object.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Initializes the game by creating the GUI and adding the game objects to the environment.
     */
    public void initialize() {
        addBackground();
        addBalls();
        addBounds();
        addBlocks();
        addPaddle(gui);
        addScoreIndicator();
        addLevelName();
    }


    /**
     * The main game loop. This method runs continuously until the game is exited.
     * In each iteration of the loop, it draws the background, sprites, and updates their states.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(0.5, 3, sprites));
        this.running = true;
        this.runner.run(this);
    }

    private void addBackground() {
        sprites.addSprite(levelInformation.getBackground());
    }

    private void addBounds() {
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        Color darkBlue = new Color(10, 60, BACKGROUND_COLOR);
        Block upperBoundBlock = new Block(new Rectangle(new Point(0, 0),
                SCREEN_WIDTH, BOUND_BLOCK_SIZE), Color.gray);
        Block rightBoundBlock = new Block(new Rectangle(new Point(SCREEN_WIDTH - BOUND_BLOCK_SIZE,
                BOUND_BLOCK_SIZE), BOUND_BLOCK_SIZE, SCREEN_HEIGHT), Color.gray);
        Block leftBoundBlock = new Block(new Rectangle(new Point(0, BOUND_BLOCK_SIZE),
                BOUND_BLOCK_SIZE, SCREEN_HEIGHT), Color.gray);
        Block deathRegionBlock = new Block(new Rectangle(new Point(BOUND_BLOCK_SIZE, SCREEN_HEIGHT + 2 * BALL_SIZE),
                SCREEN_WIDTH - 2 * BOUND_BLOCK_SIZE, 1), darkBlue);
        deathRegionBlock.addHitListener(ballRemover);
        upperBoundBlock.addToGame(this);
        deathRegionBlock.addToGame(this);
        rightBoundBlock.addToGame(this);
        leftBoundBlock.addToGame(this);
    }

    private void addBlocks() {
        blockCounter.increase(levelInformation.blocks().size());
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        ScoreTrackingListener stl = new ScoreTrackingListener(score);
        for (Block b : levelInformation.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(stl);
        }
    }

    private void addBalls() {
        double xStart = (double) SCREEN_WIDTH / 2;
        double yStart = SCREEN_HEIGHT - 2 * PADDLE_HEIGHT - 2 * BALL_SIZE;
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(xStart, yStart), BALL_SIZE, Color.white);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
            ballCounter.increase(1);
        }
    }

    private void addPaddle(GUI gui) {
        Point upperLeftCorner = new Point((double) SCREEN_WIDTH / 2 - ((double) levelInformation.paddleWidth() / 2),
                SCREEN_HEIGHT - 2 * PADDLE_HEIGHT);
        Paddle paddle = new Paddle(new Block(new Rectangle(upperLeftCorner, levelInformation.paddleWidth(),
                PADDLE_HEIGHT), Color.orange), levelInformation.paddleSpeed(),
                LEFT_BOUND, SCREEN_WIDTH - BOUND_BLOCK_SIZE, gui);
        paddle.addToGame(this);
    }

    private void addScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        sprites.addSprite(scoreIndicator);
    }

    private void addLevelName() {
        LevelName levelName = new LevelName(levelInformation.levelName());
        sprites.addSprite(levelName);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        environment.getCollidables().remove(c);
    }

    /**
     * Removes a sprite object from the sprite's collection.
     *
     * @param s the sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.getSprites().remove(s);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        sprites.drawAllOn(d);
        sprites.notifyAllTimePassed();
        if (blockCounter.getValue() == 0) {
            score.increase(100);
        }
        if (blockCounter.getValue() == 0 || ballCounter.getValue() == 0) {
            this.running = false;

        }

    }

    /**
     * Returns the counter for the balls in the game.
     *
     * @return the counter for the balls.
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    /**
     * Returns the counter for the blocks in the game.
     *
     * @return the counter for the blocks.
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

}