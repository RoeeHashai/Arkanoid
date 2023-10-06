package geometry.shapes;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.geometryPrimitives.Line;
import geometry.geometryPrimitives.Point;
import geometry.geometryPrimitives.Rectangle;
import geometry.geometryPrimitives.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The Paddle class represents the game paddle, which is a rectangle that can move horizontally on the screen.
 * The paddle can be controlled using the keyboard arrow keys.
 */
public class Paddle implements Sprite, Collidable {
    // Globals
    public static final double THRESHOLD = 0.00001;
    // Attributes
    private KeyboardSensor keyboard;
    private Block blockPaddle;
    private double leftBound;
    private double rightBound;

    private int speed;

    /**
     * Constructor for the Paddle class.
     *
     * @param blockPaddle the block representing the paddle
     * @param speed the speed of the block
     * @param leftBound   the left bound of the paddle movement range(value of the x-axis)
     * @param rightBound  the right bound of the paddle movement range(value of the x-axis)
     * @param gui         the GUI object used to get the keyboard sensor
     */
    public Paddle(Block blockPaddle, int speed, double leftBound, double rightBound, GUI gui) {
        this.blockPaddle = blockPaddle;
        this.speed = speed;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Moves the paddle left.
     */
    public void moveLeft() {
        double currentX = blockPaddle.getCollisionRectangle().getUpperLeft().getX();
        if (currentX > leftBound + 2 * speed
                || doubleCompare(currentX, leftBound + 2 * speed)) {
            blockPaddle.getCollisionRectangle().getUpperLeft().setX(currentX - speed);
        } else if (currentX < leftBound + 2 * speed && currentX > leftBound) {
            blockPaddle.getCollisionRectangle().getUpperLeft().setX(currentX - (currentX - leftBound));
        }
        blockPaddle.updateBounds();

    }

    /**
     * Moves the paddle right.
     */
    public void moveRight() {
        double currentX = blockPaddle.getCollisionRectangle().getUpperLeft().getX();
        double xWithWidth = currentX + blockPaddle.getCollisionRectangle().getWidth();
        if (xWithWidth < rightBound - 2 * speed
                || doubleCompare(xWithWidth, rightBound - 2 * speed)) {
            blockPaddle.getCollisionRectangle().getUpperLeft().setX(currentX + speed);
        } else if (xWithWidth > rightBound - 2 * speed && xWithWidth < rightBound) {
            blockPaddle.getCollisionRectangle().getUpperLeft().setX(currentX + (rightBound - xWithWidth));
        }
        blockPaddle.updateBounds();

    }

    /**
     * Implements the timePassed method of the Sprite interface.
     * Moves the paddle left or right based on the state of the keyboard arrow keys.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given draw surface.
     *
     * @param drawSurface the draw surface on which to draw the paddle
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(Color.ORANGE);
        drawSurface.fillRectangle((int) blockPaddle.getCollisionRectangle().getUpperLeft().getX(),
                (int) blockPaddle.getCollisionRectangle().getUpperLeft().getY(),
                (int) blockPaddle.getCollisionRectangle().getWidth(),
                (int) blockPaddle.getCollisionRectangle().getHeight());
        drawSurface.setColor(Color.BLACK); // set the color of the stroke to black
        drawSurface.drawRectangle((int) blockPaddle.getCollisionRectangle().getUpperLeft().getX(),
                (int) blockPaddle.getCollisionRectangle().getUpperLeft().getY(),
                (int) blockPaddle.getCollisionRectangle().getWidth(),
                (int) blockPaddle.getCollisionRectangle().getHeight());
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return blockPaddle.getCollisionRectangle();
    }

    /**
     * Calculates the new velocity of the ball after hitting the paddle.
     *
     * @param collisionPoint  the point of collision between the ball and the paddle
     * @param currentVelocity the current velocity of the ball
     * @param hitter          the ball that hit the paddle
     * @return the new velocity of the ball after hitting the paddle
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int regionNum = getRegionNumber(collisionPoint);
        double currentSpeed = currentVelocity.calculateSpeed();
        return switch (regionNum) {
            case 1 -> Velocity.fromAngleAndSpeed(300, currentSpeed);
            case 2 -> Velocity.fromAngleAndSpeed(330, currentSpeed);
            case 4 -> Velocity.fromAngleAndSpeed(30, currentSpeed);
            case 5 -> Velocity.fromAngleAndSpeed(60, currentSpeed);
            default -> blockPaddle.hit(hitter, collisionPoint, currentVelocity);
        };
    }

    /**
     * Returns the region number of the paddle that the point of collision belongs to.
     *
     * @param collisionPoint the point of collision between the ball and the paddle
     * @return the region number of the paddle that the given point of collision belongs to
     */
    private int getRegionNumber(Point collisionPoint) {
        Point end = new Point(blockPaddle.getCollisionRectangle().getUpperLeft().getX()
                + blockPaddle.getCollisionRectangle().getWidth(),
                blockPaddle.getCollisionRectangle().getUpperLeft().getY());
        Line line = new Line(blockPaddle.getCollisionRectangle().getUpperLeft(), end);
        double oneRegionSize = line.length() / 5;
        for (int i = 0; i < 5; i++) {
            if (pointXCoordinateInRange(i * oneRegionSize + blockPaddle.getCollisionRectangle().getLBoundX(),
                    (i + 1) * oneRegionSize + blockPaddle.getCollisionRectangle().getLBoundX(), collisionPoint)) {
                if (doubleCompare(collisionPoint.getY(), blockPaddle.getCollisionRectangle().getUpperLeft().getY())) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    /**
     * Adds the paddle to the game.
     *
     * @param game the game object to add the paddle to
     */
    public void addToGame(GameLevel game) {
        game.getEnvironment().addCollidable(this);
        game.getSprites().addSprite(this);
    }

    // Compares two double values for equality within a threshold.
    private boolean doubleCompare(double num1, double num2) {
        return Math.abs(num1 - num2) < THRESHOLD;
    }

    private Boolean pointXCoordinateInRange(double leftPoint, double rightPoint, Point check) {
        return (check.getX() > leftPoint || doubleCompare(check.getX(), leftPoint))
                && (check.getX() < rightPoint || doubleCompare(check.getX(), rightPoint));
    }
}