package utilities;

/**
 * The Counter class represents a counter.
 */
public class Counter {
    private int counter;

    /**
     * Constructs a new Counter and initializes it.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Increases the counter by the specified number.
     *
     * @param number the number to increase the counter by
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number the number to decrease the counter by
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the current value of the counter
     */
    public int getValue() {
        return counter;
    }
}