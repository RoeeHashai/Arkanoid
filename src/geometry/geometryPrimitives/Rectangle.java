package geometry.geometryPrimitives;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * The Rectangle class represents a rectangle object on a two-dimensional plane, defined by its upper-left corner
 * point, width, and height.
 */
public class Rectangle {
    private Point upperLeftCorner;
    private double width;
    private double height;

    /**
     * Constructs a new Rectangle object with the specified upper-left corner point, width, and height.
     *
     * @param upperLeftCorner the upper-left corner point of the rectangle
     * @param width           the width of the rectangle
     * @param height          the height of the rectangle
     */
    public Rectangle(Point upperLeftCorner, double width, double height) {
        this.upperLeftCorner = upperLeftCorner;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of intersection points between the rectangle and the specified line.
     *
     * @param line the line to check for intersection points with the rectangle
     * @return a list of intersection points, possibly empty
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        List<Line> bounds = getBounds();
        for (Line bound : bounds) {
            if (bound.intersectionWith(line) != null) {
                intersections.add(bound.intersectionWith(line));
            }
        }
        return intersections;

    }

    /**
     * Draws the rectangle on the DrawSurface with the specified color.
     *
     * @param surface the surface to draw the rectangle on
     * @param color   the color of the rectangle
     */
    public void drawOn(DrawSurface surface, Color color) {
        surface.setColor(color);
        surface.fillRectangle((int) upperLeftCorner.getX(), (int) upperLeftCorner.getY(), (int) width, (int) height);
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left corner point of the rectangle.
     *
     * @return the upper-left corner point of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeftCorner;
    }

    /**
     * Returns is the given point is a corner of the rectangle.
     *
     * @param point to be checked if is a center
     * @return true is the point is a center, false otherwise
     */
    public boolean atCorner(Point point) {
        Point upLeftCorner = getUpperLine().intersectionWith(getLeftLine());
        Point lowLeftCorner = getLowerLine().intersectionWith(getLeftLine());
        Point lowRightCorner = getLowerLine().intersectionWith(getRightLine());
        Point upRightCorner = getUpperLine().intersectionWith(getRightLine());
        return point.equals(upLeftCorner) || point.equals(lowLeftCorner)
                || point.equals(lowRightCorner) || point.equals(upRightCorner);
    }

    /**
     * Returns the x value of the left bound of the rectangle.
     *
     * @return the x value of the left bound of the rectangle
     */
    public double getLBoundX() {
        return upperLeftCorner.getX();
    }

    private Line getUpperLine() {
        return new Line(new Point(upperLeftCorner.getX(), upperLeftCorner.getY()),
                new Point(upperLeftCorner.getX() + width, upperLeftCorner.getY()));
    }

    private Line getLowerLine() {
        return new Line(new Point(upperLeftCorner.getX(), upperLeftCorner.getY() + height),
                new Point(upperLeftCorner.getX() + width, upperLeftCorner.getY() + height));
    }

    private Line getLeftLine() {
        return new Line(new Point(upperLeftCorner.getX(), upperLeftCorner.getY()),
                new Point(upperLeftCorner.getX(), upperLeftCorner.getY() + height));
    }

    private Line getRightLine() {
        return new Line(new Point(upperLeftCorner.getX() + width, upperLeftCorner.getY()),
                new Point(upperLeftCorner.getX() + width, upperLeftCorner.getY() + height));
    }

    private List<Line> getBounds() {
        List<Line> bounds = new ArrayList<Line>();
        bounds.add(getUpperLine());
        bounds.add(getLowerLine());
        bounds.add(getLeftLine());
        bounds.add(getRightLine());
        return bounds;
    }
}
