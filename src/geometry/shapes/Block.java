package geometry.shapes;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.geometryPrimitives.Line;
import geometry.geometryPrimitives.Point;
import geometry.geometryPrimitives.Rectangle;
import geometry.geometryPrimitives.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represents a block that can be collided with other objects and drawn on the screen.
 * The block has a rectangle shape and can be of a given color.
 * The block implements the Collidable and Sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    public static final double THRESHOLD = 0.00001;
    private Rectangle blockRectangle;
    private Line upperBound;
    private Line lowerBound;
    private Line leftBound;
    private Line rightBound;
    private Point upLeftCorner;
    private Point lowLeftCorner;
    private Point upRightCorner;
    private Point lowRightCorner;
    private Color color;
    private List<HitListener> hitListeners;


    /**
     * Constructs a block with the given rectangle shape and color.
     * Initializes the block's bounds.
     *
     * @param rectangle the rectangle shape of the block
     * @param color     the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        blockRectangle = rectangle;
        initBounds();
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructs a black block with the given rectangle shape. The default color will be set to Black
     *
     * @param rectangle the rectangle shape of the block
     */
    public Block(Rectangle rectangle) {
        this(rectangle, Color.BLACK);
    }

    /**
     * Initializes the bounds of the block.
     */
    public void initBounds() {
        upperBound = new Line(blockRectangle.getUpperLeft(),
                new Point(blockRectangle.getUpperLeft().getX() + blockRectangle.getWidth(),
                        blockRectangle.getUpperLeft().getY()));
        lowerBound = new Line(new Point(blockRectangle.getUpperLeft().getX(),
                blockRectangle.getUpperLeft().getY() + blockRectangle.getHeight()),
                new Point(blockRectangle.getUpperLeft().getX() + blockRectangle.getWidth(),
                        blockRectangle.getUpperLeft().getY() + blockRectangle.getHeight()));
        leftBound = new Line(upperBound.getEnd(), lowerBound.getEnd());
        rightBound = new Line(upperBound.getStart(), lowerBound.getStart());
        upLeftCorner = upperBound.intersectionWith(leftBound);
        lowLeftCorner = lowerBound.intersectionWith(leftBound);
        lowRightCorner = lowerBound.intersectionWith(rightBound);
        upRightCorner = upperBound.intersectionWith(rightBound);
    }

    /**
     * Returns the rectangle shape of the block.
     *
     * @return the rectangle shape of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return blockRectangle;
    }

    /**
     * Calculates and returns the new velocity after the collision with the block.
     * The collision behavior of the ball with the block depends on which side of the block the collision occurs.
     *
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the current velocity of the object colliding with the block
     * @return the new velocity after the collision with the block
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Calculate the point where the ball would have been before hitting the block
        if (collisionPoint.equals(upLeftCorner) || collisionPoint.equals(lowLeftCorner)
                || collisionPoint.equals(upRightCorner) || collisionPoint.equals(lowRightCorner)) {
            this.notifyHit(hitter);
            return cornerHandler(collisionPoint, currentVelocity);
        }
        if (rightBound.inRange(collisionPoint) || leftBound.inRange(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
            this.notifyHit(hitter);
            return currentVelocity;
        }
        if (upperBound.inRange(collisionPoint) || lowerBound.inRange(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    private Velocity cornerHandler(Point collisionPoint, Velocity currentVelocity) {
        double angle;
        angle = currentVelocity.calculateAngle();
        if (collisionPoint.equals(upLeftCorner)) {
            if (angle > 90 && angle < 180) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else if (angle > 270 && angle < 360) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else if ((angle > 0 || doubleCompare(angle, 0)) && (angle < 90 || doubleCompare(angle, 90))) {
                currentVelocity.setDx(-currentVelocity.getDx());
                currentVelocity.setDy(-currentVelocity.getDy());
            }
        } else if (collisionPoint.equals(lowLeftCorner)) {
            if (angle > 0 && angle < 90) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else if (angle > 180 && angle < 270) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else if (((angle > 270 || doubleCompare(angle, 270)) && (angle < 360))
                    || doubleCompare(angle, 0)) {
                currentVelocity.setDx(-currentVelocity.getDx());
                currentVelocity.setDy(-currentVelocity.getDy());
            }
        } else if (collisionPoint.equals(lowRightCorner)) {
            if (angle > 90 && angle < 180) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else if (angle > 0 && angle < 270) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else if ((angle > 180 || doubleCompare(angle, 180)) && (angle < 270 || doubleCompare(angle, 270))) {
                currentVelocity.setDx(-currentVelocity.getDx());
                currentVelocity.setDy(-currentVelocity.getDy());
            }
        } else if (collisionPoint.equals(upRightCorner)) {
            if (angle > 0 && angle < 90) {
                currentVelocity.setDy(-currentVelocity.getDy());
            } else if (angle > 180 && angle < 270) {
                currentVelocity.setDx(-currentVelocity.getDx());
            } else if ((angle > 90 || doubleCompare(angle, 90)) && (angle < 180 || doubleCompare(angle, 180))) {
                currentVelocity.setDx(-currentVelocity.getDx());
                currentVelocity.setDy(-currentVelocity.getDy());
            }
        }
        return currentVelocity;
    }


    /**
     * Draws the ball on the given DrawSurface.
     * sets the stroke color to black, and then draws the outline of the rectangle.
     *
     * @param drawSurface the DrawSurface to draw the ball on
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(color);
        drawSurface.fillRectangle((int) blockRectangle.getUpperLeft().getX(),
                (int) blockRectangle.getUpperLeft().getY(),
                (int) blockRectangle.getWidth(), (int) blockRectangle.getHeight());
        drawSurface.setColor(Color.BLACK); // set the color of the stroke to black
        drawSurface.drawRectangle((int) blockRectangle.getUpperLeft().getX(),
                (int) blockRectangle.getUpperLeft().getY(),
                (int) blockRectangle.getWidth(), (int) blockRectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    /**
     * This method adds the block to the given game. It adds the block to the game's list of collidables
     * and list of sprites.
     *
     * @param game the game to which the block should be added
     */
    public void addToGame(GameLevel game) {
        game.getEnvironment().addCollidable(this);
        game.getSprites().addSprite(this);
    }

    /**
     * This method updates the bounds of the block. It is called when the position of the block
     * is changed.
     */
    public void updateBounds() {
        initBounds();
    }

    // Compares two double values for equality within a threshold.
    private boolean doubleCompare(double num1, double num2) {
        return Math.abs(num1 - num2) < THRESHOLD;
    }

    /**
     * Removes the block from the game.
     *
     * @param game the game to remove the block from
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

}
