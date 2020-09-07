package models;

import java.util.Observable;

/**
 * Represents a clock that manages the time of the typing test.
 */
public class ClockModel extends Observable {
    private int time; // time in seconds

    public static final int
            RESET_EVENT = 0,
            UPDATE_TIME_EVENT = 1;

    /**
     * Creates a Clock Model with an initial time based on the default
     * typing test style.
     */
    public ClockModel() {
        time = SettingsModel.getInstance().getDuration();
    }

    /**
     * Returns the current time of this clock.
     *
     * @return the current time of this clock
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets the time of this clock.
     *
     * @param time the time of this clock
     */
    public void setTime(int time) {
        this.time = time;
        setChanged();
        notifyObservers(UPDATE_TIME_EVENT);
    }

    /**
     * Resets the current time to the initial time based on the current.
     * typing test style.
     */
    public void reset() {
        time = SettingsModel.getInstance().getDuration();
        setChanged();
        notifyObservers(RESET_EVENT);
    }
}
