package geometry.geometryPrimitives;

/**
 * The Velocity class represents velocity in terms of its change in position on the x and y axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Creates a new Velocity object with the specified x and y components. Initializes its dx and its dy.
     *
     * @param dx the change in position in terms of the x-axis
     * @param dy the change in position in terms of the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the change in position on the x-axis(dx).
     *
     * @return the change in position on the x-axis(dx)
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the change in position on the y-axis(dy).
     *
     * @return the change in position on the y-axis(dy)
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets the change in position on the x-axis(dx).
     *
     * @param dx the new change in position on the x-axis(dx)
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the change in position on the y-axis(dy).
     *
     * @param dy the new change in position on the y-axis(dy)
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Constructs a new Velocity object from an angle and speed.
     *
     * @param angle the angle in degrees
     * @param speed the speed
     * @return a new Velocity object with the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * -Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Applies the changes to the point and returns a new point with the updated position.
     *
     * @param point the point to apply the velocity to
     * @return a new Point object with the updated position
     */
    public Point applyToPoint(Point point) {
        return new Point(point.getX() + dx, point.getY() + dy);
    }

    /**
     * Calculates the speed of the object based on its x and y components.
     * The speed is calculated using the formula:
     * speed = square root of (dx^2 + dy^2)
     *
     * @return the speed of the object
     */
    public double calculateSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Calculates the angle of the object's velocity in degrees.
     *
     * @return the angle of the object's velocity in degrees
     */
    public double calculateAngle() {
        double angle = Math.toDegrees(Math.atan2(dy, dx));
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

}