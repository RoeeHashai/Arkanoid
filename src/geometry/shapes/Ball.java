package geometry.shapes;

import game.GameLevel;
import game.GameEnvironment;
import geometry.geometryPrimitives.Line;
import geometry.geometryPrimitives.Point;
import biuoop.DrawSurface;

import java.awt.Color;

import geometry.geometryPrimitives.Velocity;
import interfaces.Sprite;
import utilities.CollisionInfo;

/**
 * The Ball class represents a ball in 2D plane, defined by its center point, radius, color and velocity.
 * It provides methods to access and modify the ball's properties, and methods to draw the ball on a DrawSurface
 * and move the ball within its unique limits.
 */
public class Ball implements Sprite {
    // Attributes
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Creates a new Ball object with a center point, radius and color.
     *
     * @param center          the center point of the ball
     * @param radius          the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int radius, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Creates a new Ball object with a center point, radius and color.
     *
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this(center, radius, color, null);
    }

    /**
     * Creates a new Ball object with a center point (by x and y coordinates), radius and color.
     *
     * @param x      the x coordinate of the center point
     * @param y      the y coordinate of the center point
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        this(new Point(x, y), radius, color);
    }

    /**
     * Returns the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return radius;
    }

    /**
     * Returns the x coordinate of the center.
     *
     * @return the x coordinate of the center
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Returns the y coordinate of the center.
     *
     * @return the y coordinate of the center
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Returns the velocity of the ball(velocity has the dx, and dy values).
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param velocity the velocity of the ball
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * Sets the velocity of the ball. By given values of dx and dy.
     *
     * @param dx the dx value of the ball
     * @param dy the dy value of the ball
     */
    public void setVelocity(double dx, double dy) {
        velocity = new Velocity(dx, dy);
    }

    /**
     * Sets the radius of the ball.
     *
     * @param radius the radius of the ball
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the center of the ball.
     *
     * @param center the center of the ball
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Sets the game environment of the ball.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Adds the ball to the given game as a sprite.
     *
     * @param game the game to add the ball to
     */
    public void addToGame(GameLevel game) {
        game.getSprites().addSprite(this);
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param drawSurface the DrawSurface to draw the ball on
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawCircle((int) center.getX(), (int) center.getY(), radius);
        drawSurface.setColor(color);
        drawSurface.fillCircle((int) center.getX(), (int) center.getY(), radius);
        // Draw a red dot in the center of the ball
        drawSurface.setColor(Color.RED);
        int dotSize = 2;
        drawSurface.fillCircle((int) center.getX(), (int) center.getY(), dotSize);
    }

    /**
     * Moves the ball one step and updates its state.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Moves the ball one step according to its velocity and the game environment.
     */
    public void moveOneStep() {
        Point nextStepCenter = velocity.applyToPoint(center);
        Line trajectory = new Line(center, nextStepCenter);
        CollisionInfo closestCollision = gameEnvironment.getClosestCollision(trajectory);
        if (closestCollision == null || closestCollision.collisionPoint() == null) {
            center = nextStepCenter;
        } else {
            center = new Point(closestCollision.collisionPoint().getX() - velocity.getDx(),
                    closestCollision.collisionPoint().getY() - velocity.getDy());
            velocity = closestCollision.collisionObject().hit(this, closestCollision.collisionPoint(), velocity);
        }
    }

    /**
     * Removes the ball from the game.
     *
     * @param game the game to remove the ball from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}