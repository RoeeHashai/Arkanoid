package collections;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class represents a collection of Sprites.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Creates an empty collection of sprites.
     */
    public SpriteCollection() {
        sprites = new ArrayList<>();
    }

    /**
     * Returns the list of sprites in the collection.
     *
     * @return the list of sprites in the collection.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Adds the given sprite to the collection.
     *
     * @param sprite the sprite to add to the collection.
     */
    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    /**
     * Notifies all sprites in the collection that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw the sprites on.
     */
    public void drawAllOn(DrawSurface surface) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(surface);
        }
    }
}