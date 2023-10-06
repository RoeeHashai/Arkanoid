package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import interfaces.Animation;
import interfaces.LevelInformation;
import utilities.Counter;

import java.util.List;

/**
 * The GameFlow class represents the main flow of the game.
 * It handles running the levels, updating the score, and displaying the end screen.
 */
public class GameFlow {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    private GUI gui;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * Creates a new GameFlow instance.
     */
    public GameFlow() {
        gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
        this.keyboardSensor = gui.getKeyboardSensor();
        animationRunner = new AnimationRunner(gui, new Sleeper());
        score = new Counter();
    }

    /**
     * Runs the given list of levels.
     *
     * @param levels the list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean isWinner = false;
        int lastLevelIndex = levels.size() - 1;

        for (int i = 0; i < levels.size(); i++) {
            LevelInformation levelInfo = levels.get(i);

            GameLevel level = new GameLevel(levelInfo, score, keyboardSensor, animationRunner, gui);
            level.initialize();

            while (level.getBallCounter().getValue() > 0 && level.getBlockCounter().getValue() > 0) {
                level.run();
            }

            if (i == lastLevelIndex && level.getBlockCounter().getValue() == 0) {
                isWinner = true;
            }

            if (level.getBallCounter().getValue() == 0) {
                break;
            }
        }
        Animation endScreen = new KeyPressStoppableAnimation(keyboardSensor, keyboardSensor.SPACE_KEY,
                new EndScreen(score, isWinner));
        animationRunner.run(endScreen);
        gui.close();
    }
}
