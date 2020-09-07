package models;

import java.util.Observable;

/**
 * This represents the  settings for the Typing Test Application
 * in which there is only a single instance for the Java application.
 */
public class SettingsModel extends Observable {
    private static final SettingsModel SETTINGS_MODEL = new SettingsModel();

    public static final int LIGHT_MODE = 0, DARK_MODE = 1;
    public static final int EASY_DIFFICULTY = 0, HARD_DIFFICULTY = 1;

    private int difficulty, duration;
    private boolean open;

    public static final int
            OPEN_EVENT = 0,
            CLOSE_EVENT = 1;

    /**
     * Prevent instantiation outside of this class. Initialize with default
     * settings.
     */
    private SettingsModel() {
        difficulty = EASY_DIFFICULTY;
        duration = 60;
        open = false;
    }

    /**
     * Returns the models.SettingsModel for this Java application.
     *
     * @return the models.SettingsModel for this Java application
     */
    public static SettingsModel getInstance() {
        return SETTINGS_MODEL;
    }

    /**
     * Returns the current difficulty
     *
     * @return the current difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the current difficulty
     *
     * @param difficulty the new difficulty
     */
    public void setDifficulty(int difficulty) {
        if (this.difficulty != difficulty) {
            this.difficulty = difficulty;
        }
    }

    /**
     * Returns the duration in seconds of the typing test.
     *
     * @return the duration in seconds of the typing test
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration in seconds of the typing test.
     *
     * @param duration the new duration in seconds of the typing test
     */
    public void setDuration(int duration) {
        if (this.duration != duration) {
            this.duration = duration;
        }
    }

    /**
     * Returns true if the settings is open, false otherwise.
     *
     * @return true if the settings is open, false otherwise
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Sets the settings to open or close.
     *
     * @param open open or closes the settings
     */
    public void setOpen(boolean open) {
        if (this.open != open) {
            this.open = open;
            setChanged();
            if (open) {
                notifyObservers(OPEN_EVENT);
            } else {
                notifyObservers(CLOSE_EVENT);
            }
        }
    }
}
