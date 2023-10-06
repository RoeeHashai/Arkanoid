package geometry.geometryPrimitives;

import java.util.List;

/**
 * Line class represents a line in the 2D plane, defined by two points(start and end).
 * The class provides methods for getting the length of the line, its middle point, checking if it intersects with
 * another line, finding the intersection point with another line, and comparing a line to a different line
 * in order to fine equally or the maximin or minimum between the two.
 */
public class Line {
    // Globals
    public static final double THRESHOLD = 0.00001;
    // Attributes
    private Point start; // the start point of the line
    private Point end; // the end point of the line
    private double m; // the slope of the line
    private double b; // the intersection with y-axis and the line(0,b)
    private double xVerticalEquation; // the x vertical equation of the line if vertical to x-axis(x=a)
    private boolean isVertical = false; // a flag that determines if the line is vertical to x-axis
    private boolean isPoint = false; // a flag that determines if the line is a point(start=end)

    /**
     * Constructor for the Line class.
     *
     * @param start the starting point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (start.equals(end)) { // in case the start = end => it's a point
            isPoint = true;
        } else if (doubleCompare(start.getX(), end.getX())) { // in case the line is vertical to x-axis
            xVerticalEquation = start.getX();
            isVertical = true;
        } else { // in case the line is a regular standard line with a slope and with b
            m = (end.getY() - start.getY()) / (end.getX() - start.getX());
            b = start.getY() - (m * start.getX());
        }
    }

    /**
     * Constructor for the Line class.
     * For an initialization with x and y coordinates of starting point and ending point.
     *
     * @param x1 the x coordinate of the starting point of the line
     * @param y1 the y coordinate of the starting point of the line
     * @param x2 the x coordinate of the end point of the line
     * @param y2 the y coordinate of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Constructs a new Line object with a given slope and y-intercept.
     *
     * @param m the slope of the line
     * @param b the y-intercept of the line
     */
    public Line(double m, double b) {
        this.m = m;
        this.b = b;
    }

    /**
     * Calculates and returns the slope of the line vertical to this line.
     *
     * @return the slope of the perpendicular line
     */
    public double getVerticalSlope() {
        return -1 / this.m;
    }

    /**
     * Calculates and returns the y-intercept of a line(B) with the given slope that passes through the given point.
     *
     * @param point the point that the line passes through
     * @param m     the slope of the line
     * @return the y-intercept of the line
     */
    public static double calcB(Point point, double m) {
        return point.getY() - m * point.getX();
    }

    /**
     * Returns the starting point of the line.
     *
     * @return the starting point of the line
     */
    public Point getStart() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Returns the slope of the line(m).
     *
     * @return the slope of the line(m)
     */
    public double getM() {
        return m;
    }

    /**
     * Returns the y-axis intercept of the line(b).
     *
     * @return the y-axis intercept of the line(b)
     */
    public double getB() {
        return b;
    }

    /**
     * Returns the x equation of the line if it is vertical.
     *
     * @return the  x equation of the line if it is vertical
     */
    public double getXVerticalEquation() {
        return xVerticalEquation;
    }

    /**
     * Returns true if the line is vertical. Returns false otherwise.
     *
     * @return true if the line is vertical
     */
    public boolean getIsVertical() {
        return isVertical;
    }

    /**
     * Sets the starting point of the line.
     *
     * @param start the new starting point of the line
     */
    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * Sets the end point of the line.
     *
     * @param end the new end point of the line
     */
    public void setEnd(Point end) {
        this.end = end;
    }

    /**
     * Calculates and returns the y-coordinate of the line at the given x-coordinate.
     *
     * @param x the x-coordinate at which to calculate the y-coordinate
     * @return the y-coordinate of the line at the given x-coordinate
     */
    public double getYSlopeBXCoordinate(double x) {
        return m * x + b;
    }

    /**
     * Calculates and returns the x-coordinate of the line at the given y-coordinate.
     *
     * @param y the y-coordinate at which to calculate the x-coordinate
     * @return the x-coordinate of the line at the given y-coordinate
     */
    public double getXSlopeBYCoordinate(double y) {
        if (!isVertical) {
            return (y - b) / m;
        } else {
            return xVerticalEquation;
        }
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double avgX = (start.getX() + end.getX()) / 2;
        double avgY = (start.getY() + end.getY()) / 2;
        return new Point(avgX, avgY);
    }

    /**
     * Compares between two lines Returns the minimal line between this line and another line.
     *
     * @param otherLine the other line to compare to
     * @return the minimal line between this line and the other line
     */
    public Line minLine(Line otherLine) {
        if (otherLine == null) {
            return null;
        }
        if (length() < otherLine.length()) {
            return this;
        } else {
            return otherLine;
        }
    }

    /**
     * Compares between two lines and Returns the maximal line between this line and another line.
     *
     * @param otherLine the other line to compare to
     * @return the maximal line between this line and the other line
     */
    public Line maxLine(Line otherLine) {
        if (otherLine == null) {
            return null;
        }
        if (length() > otherLine.length()) {
            return this;
        } else {
            return otherLine;
        }
    }

    /**
     * Checks if this line equal to other line.
     * Returns true if this line is equal, false otherwise.
     *
     * @param otherLine the other line to compare to
     * @return true if this line is equal to the other line, false otherwise
     */
    public boolean equals(Line otherLine) {
        if (otherLine == null) {
            return false;
        }
        // checks all variation of equally between the start and end point. Checks all combinations.
        if (start.equals(otherLine.start) && end.equals(otherLine.end)) {
            return true;
        } else if (start.equals(otherLine.end) && end.equals(otherLine.start)) {
            return true;
        } else if (end.equals(otherLine.start) && start.equals(otherLine.end)) {
            return true;
        } else {
            return end.equals(otherLine.end) && start.equals(otherLine.start);
        }
    }

    /**
     * Returns the intersection point if this line intersects with another line, and null otherwise.
     *
     * @param otherLine the other line to check for intersection with
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(Line otherLine) {
        if (otherLine == null) {
            return null;
        }
        // in case it's a point - check intersection of point and line
        if (isPoint) {
            if (inRange(otherLine, start)) {
                return start;
            } else {
                return null;
            }
        } else if (isVertical && otherLine.getIsVertical()) {
            // in case both are vertical line without a slope
            if (!doubleCompare(xVerticalEquation, otherLine.getXVerticalEquation())) {
                return null;
            }
        } else if (isVertical && !otherLine.getIsVertical()) {
            // in case one of them is a vertical line without a slope
            return verticalIntersection(otherLine, this);
        } else if (!isVertical && otherLine.getIsVertical()) {
            // in case one of them is a vertical line without a slope
            return verticalIntersection(this, otherLine);
        } else if (doubleCompare(m, otherLine.getM()) && !doubleCompare(b, otherLine.getB())) {
            // if parallel line with different y-intercept
            return null;
        } else if (!doubleCompare(m, otherLine.getM()) && !doubleCompare(b, otherLine.getB())) {
            // in case of intersecting lines
            return nonVerticalIntersection(this, otherLine);
        }
        if (equals(otherLine)) { // if lines are identical - there is infinite intersection points
            return null;
        }
        // in case the line are coincident, same m and b, and only starts are equal(or any other point start\end)
        // the method needs to return the intersection point.
        Line minLine, maxLine;
        if (!doubleCompare(length(), otherLine.length())) {
            minLine = minLine(otherLine);
            maxLine = maxLine(otherLine);
        } else {
            minLine = this;
            maxLine = otherLine;
        }
        if (maxLine.getStart().equals(minLine.getStart()) || maxLine.getEnd().equals(minLine.getStart())) {
            if (!inRange(maxLine, minLine.getEnd())) {
                return minLine.getStart();
            }
        }
        if (maxLine.getStart().equals(minLine.getEnd()) || maxLine.getEnd().equals(minLine.getEnd())) {
            if (!inRange(maxLine, minLine.getStart())) {
                return minLine.getEnd();
            }
        }
        // if no intersection point has found return null
        return null;
    }

    /**
     * Checks if line intersects with another line.
     *
     * @param otherLine the other line to check for intersection with
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line otherLine) {
        if (otherLine == null) {
            return false;
        }
        if (isPoint) {
            // in case it's a point - check if point is in range of other line
            return inRange(otherLine, start);
        } else if (isVertical && otherLine.getIsVertical()
                && !doubleCompare(xVerticalEquation, otherLine.getXVerticalEquation())) {
            // lines are vertical with different x equation
            return false;
        } else if (isVertical && !otherLine.getIsVertical() && verticalIntersection(otherLine, this) != null) {
            // one is vertical
            return true;
        } else if (!isVertical && otherLine.getIsVertical()
                && verticalIntersection(this, otherLine) != null) { // one is vertical
            return true;
        } else {
            // the line has a slope(m) and a y-intercept(b)
            if (doubleCompare(m, otherLine.getM()) && !doubleCompare(b, otherLine.getB())) { // parallel lines
                return false;
            } else {
                if (!doubleCompare(m, otherLine.getM()) && !doubleCompare(b, otherLine.getB())) { // intersecting lines
                    return intersectionWith(otherLine) != null;
                }
            }
        }
        // check if point is in range of the other line in case that non intersection was found
        return inRange(otherLine, start) || inRange(otherLine, end)
                || inRange(this, otherLine.start) || inRange(this, otherLine.end);
    }

    /**
     * Calculates the intersection point between a non-vertical line and a vertical line.
     *
     * @param line     the non-vertical line
     * @param vertical the vertical line
     * @return the intersection point, or null if the lines don't intersect
     */
    public Point verticalIntersection(Line line, Line vertical) {
        double y = line.getM() * vertical.getXVerticalEquation() + line.getB();
        Point intersection = new Point(vertical.getXVerticalEquation(), y);
        if (inRange(line, intersection) && inRange(vertical, intersection)) {
            return intersection;
        } else {
            return null;
        }
    }

    /**
     * Calculates the intersection point between two non-vertical lines.
     *
     * @param line1 the first non-vertical line
     * @param line2 the second non-vertical line
     * @return the intersection point, or null if the lines don't intersect
     */
    public Point nonVerticalIntersection(Line line1, Line line2) {
        double x = (line1.getB() - line2.getB()) / (line2.getM() - line1.getM());
        double y = line1.getM() * x + line1.getB();
        Point intersection = new Point(x, y);
        if (inRange(line1, intersection) && inRange(line2, intersection)) {
            return intersection;
        } else {
            return null;
        }
    }

    /**
     * Checks if a point is in range of a line,
     * by the x and y coordinates of the starting and ending points of the line.
     *
     * @param line  the line to check against
     * @param point the point to check
     * @return true if the point is in range of the line, false otherwise
     */
    public boolean inRange(Line line, Point point) {
        // checks all the possibles combinations
        if ((point.getX() > line.getStart().getX() || doubleCompare(point.getX(), line.getStart().getX()))
                && (point.getX() < line.getEnd().getX() || doubleCompare(point.getX(), line.getEnd().getX()))
                && (point.getY() > line.getStart().getY() || doubleCompare(point.getY(), line.getStart().getY()))
                && (point.getY() < line.getEnd().getY() || doubleCompare(point.getY(), line.getEnd().getY()))) {
            return true;
        } else if ((point.getX() > line.getEnd().getX() || doubleCompare(point.getX(), line.getEnd().getX()))
                && (point.getX() < line.getStart().getX() || doubleCompare(point.getX(), line.getStart().getX()))
                && (point.getY() > line.getEnd().getY() || doubleCompare(point.getY(), line.getEnd().getY()))
                && (point.getY() < line.getStart().getY() || doubleCompare(point.getY(), line.getStart().getY()))) {
            return true;
        } else if ((point.getX() > line.getStart().getX() || doubleCompare(point.getX(), line.getStart().getX()))
                && (point.getX() < line.getEnd().getX() || doubleCompare(point.getX(), line.getEnd().getX()))
                && (point.getY() < line.getStart().getY() || doubleCompare(point.getY(), line.getStart().getY()))
                && (point.getY() > line.getEnd().getY() || doubleCompare(point.getY(), line.getEnd().getY()))) {
            return true;
        } else {
            return ((point.getX() > line.getEnd().getX() || doubleCompare(point.getX(), line.getEnd().getX()))
                    && (point.getX() < line.getStart().getX() || doubleCompare(point.getX(), line.getStart().getX()))
                    && (point.getY() < line.getEnd().getY() || doubleCompare(point.getY(), line.getEnd().getY()))
                    && (point.getY() > line.getStart().getY() || doubleCompare(point.getY(), line.getStart().getY())));
        }
    }

    /**
     * Checks if a point is in range of a line,
     * by the x and y coordinates of the starting and ending points of the line.
     *
     * @param point the point to check
     * @return true if the point is in range of the line, false otherwise
     */
    public boolean inRange(Point point) {
        return inRange(this, point);
    }

    /**
     * Returns the closest intersection point to the start of the line with the given rectangle.
     * If this line does not intersect with the rectangle, return null.
     *
     * @param rectangle the rectangle to check for intersection with the line
     * @return the closest intersection point to the start of the line, or null if there is no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rectangle) {
        List<Point> intersections = rectangle.intersectionPoints(this);
        if (intersections.size() == 0) {
            return null;
        }
        Point mostClose = intersections.get(0);
        if (intersections.size() > 1) {
            for (Point point : intersections.subList(1, intersections.size())) {
                if (point.distance(start) < mostClose.distance(start)) {
                    mostClose = point;
                }
            }
        }
        return mostClose;
    }

    // Compares two double values for equality within a threshold.
    private boolean doubleCompare(double num1, double num2) {
        return Math.abs(num1 - num2) < THRESHOLD;
    }
}

