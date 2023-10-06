package game;

import geometry.geometryPrimitives.Line;
import geometry.geometryPrimitives.Point;
import interfaces.Collidable;
import utilities.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class is responsible for managing a collection of collidable objects
 * and providing methods to check for collisions with a given trajectory.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructs a new GameEnvironment object with an empty list of collidables.
     */
    public GameEnvironment() {
        collidables = new ArrayList<>();
    }

    /**
     * Adds a collidable object to the collection of collidables.
     *
     * @param collidable the collidable object to add
     */
    public void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    /**
     * Returns the list of collidable objects managed by this GameEnvironment object.
     *
     * @return the list of collidable objects
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Returns the closest collision point and collidable object to a given trajectory.
     *
     * @param trajectory the trajectory to check for collisions with
     * @return the CollisionInfo object containing the closest collision point and collidable object,
     * or null if there are no collisions
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> collisionPoints = new ArrayList<>();
        Point collisionPoint;
        if (collidables == null) {
            return null;
        }
        for (Collidable collidableObj : collidables) {
            collisionPoint = trajectory.closestIntersectionToStartOfLine(collidableObj.getCollisionRectangle());
            if (collisionPoint != null) {
                collisionPoints.add(new CollisionInfo(collisionPoint, collidableObj));
            }
        }
        if (collisionPoints.size() == 0) {
            return null;
        } else {
            // Check if all the collision points are the same as the start point of the trajectory.
            boolean allEqual = true;
            for (CollisionInfo ci : collisionPoints) {
                if (!ci.collisionPoint().equals(trajectory.getStart())) {
                    allEqual = false;
                }
            }
            // If they are all equal.
            if (allEqual) {
                CollisionInfo toReturn = collisionPoints.get(0);
                for (CollisionInfo ci : collisionPoints) {
                    if (ci.collisionObject().getCollisionRectangle().atCorner(trajectory.getStart())) {
                        toReturn = ci;
                    }
                }
                return toReturn;
            }
        }
        for (int i = collisionPoints.size() - 1; i >= 0; i--) {
            CollisionInfo element = collisionPoints.get(i);
            if (element.collisionPoint().equals(trajectory.getStart())) {
                collisionPoints.remove(i);
            }
        }
        if (collisionPoints.size() == 0) {
            return null;
        }
        if (collisionPoints.size() == 1) {
            return collisionPoints.get(0);
        }
        // Find the closest collision point to the start point of the trajectory.
        CollisionInfo closestCollision = collisionPoints.get(0);
        for (CollisionInfo collisionInfo : collisionPoints.subList(1, collisionPoints.size())) {
            if (collisionInfo.collisionPoint().distance(trajectory.getStart())
                    < closestCollision.collisionPoint().distance(trajectory.getStart())) {
                closestCollision = collisionInfo;
            }
        }
        return closestCollision;
    }
}
