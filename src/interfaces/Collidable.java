package interfaces;

import geometry.geometryPrimitives.Point;
import geometry.geometryPrimitives.Velocity;
import geometry.geometryPrimitives.Rectangle;
import geometry.shapes.Ball;

/**
 * The Collidable interface represents an object that can collide with other objects.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the Rectangle object that represents the collision shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Returns the new Velocity expected after the hit.
     *
     * @param collisionPoint  the Point object that represents the point of collision.
     * @param currentVelocity the Velocity object that represents the current velocity of the object.
     * @param hitter the ball that hit the object
     * @return the Velocity object that represents the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
