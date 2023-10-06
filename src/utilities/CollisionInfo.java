package utilities;

import geometry.geometryPrimitives.Point;
import interfaces.Collidable;


/**
 * The CollisionInfo class represents information about a collision between two objects.
 * It contains the point at which the collision occurred and the Collidable object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor for the CollisionInfo class.
     *
     * @param collisionPoint  the point at which the collision occurred.
     * @param collisionObject the Collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the collision point which the collision occurred.
     *
     * @return the point at which the collision occurred.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the point at which the collision occurred.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }

}