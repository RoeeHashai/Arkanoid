package geometry.geometryPrimitives;


/**
 * The Point class represents a point in the plane.
 */
public class Point {
    // Globals
    public static final double THRESHOLD = 0.00001;
    // Attributes
    private double x; // the x coordinate of the point
    private double y; // the y coordinate of the point

    /**
     * Constructor that creates a new Point object with the given x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of this point.
     *
     * @return the x coordinate of this point
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y coordinate of this point.
     *
     * @return the y coordinate of this point
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x coordinate of this point.
     *
     * @param x the new x coordinate of this point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate of this point.
     *
     * @param y the new y coordinate of this point
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Distance returns the distance between this point and the other point.
     *
     * @param otherPoint the other point to calculate the distance
     * @return the distance between this point and the other point
     */
    public double distance(Point otherPoint) {
        return Math.sqrt(Math.pow(x - otherPoint.getX(), 2) + Math.pow(y - otherPoint.getY(), 2));
    }

    /**
     * Returns true if this point is equal to the other point, false otherwise.
     *
     * @param otherPoint the other point to compare
     * @return true if this point is equal to the other point, false otherwise
     */
    public boolean equals(Point otherPoint) {
        if (otherPoint == null) {
            return false;
        }
        return doubleCompare(x, otherPoint.getX()) && doubleCompare(y, otherPoint.getY());
    }

    // Compares two double values for equality within a threshold.
    private boolean doubleCompare(double num1, double num2) {
        return Math.abs(num1 - num2) < THRESHOLD;
    }

    /**
     * Returns a string representation of this object.
     * Overrides the default {@code toString()} method
     * to return a formatted string of the object's coordinates.
     *
     * @return a string representation of the object in the format "(x, y)"
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
