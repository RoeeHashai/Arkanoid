package interfaces;

/**
 * HitNotifier interface represents a hit notifier for objects that can trigger hit events.
 */
public interface HitNotifier {
    /**
     * Adds a hit listener to the list of listeners for hit events.
     *
     * @param hl the hit listener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a hit listener from the list of listeners for hit events.
     *
     * @param hl the hit listener to be removed
     */
    void removeHitListener(HitListener hl);
}
