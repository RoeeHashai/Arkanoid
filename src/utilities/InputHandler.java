package utilities;

import game.levels.DirectHit;
import game.levels.Green3;
import game.levels.WideEasy;
import interfaces.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * The InputHandler class is responsible for handling the input arguments
 * and providing the corresponding levels to be played in the game.
 */
public class InputHandler {
    private String[] args;

    /**
     * Constructs an InputHandler with the given input arguments.
     *
     * @param args the input arguments.
     */
    public InputHandler(String[] args) {
        this.args = args;
    }

    /**
     * Retrieves the levels based on the input arguments.
     * If no valid input is provided, it returns the default levels.
     *
     * @return the list of level information.
     */
    public List<LevelInformation> getLevels() {
        List<Integer> validInputs = clearInvalidInputs();
        if (validInputs.size() == 0) {
            return buildDefaultLevels();
        } else {
            List<LevelInformation> levels = new ArrayList<>();
            for (Integer i : validInputs) {
                switch (i) {
                    case 1 -> levels.add(new DirectHit());
                    case 2 -> levels.add(new WideEasy());
                    case 3 -> levels.add(new Green3());
                    default -> {
                    }
                }
            }
            if (levels.size() == 0) {
                return buildDefaultLevels();
            }
            return levels;
        }
    }


    private List<Integer> clearInvalidInputs() {
        int tryParse;
        List<Integer> validInputs = new ArrayList<>();
        for (String s : args) {
            try {
                tryParse = Integer.parseInt(s);
                validInputs.add(tryParse);
            } catch (Exception e) {

            }
        }
        return validInputs;
    }

    private List<LevelInformation> buildDefaultLevels() {
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        return levels;
    }
}
