import game.GameFlow;
import utilities.InputHandler;

/**
 * The Main class initializes and runs the game.
 */
public class Arkanoid {
    /**
     * The main method initializes the game, and runs it.
     *
     * @param args command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler(args);
        GameFlow gameFlow = new GameFlow();
        gameFlow.runLevels(inputHandler.getLevels());
    }

}