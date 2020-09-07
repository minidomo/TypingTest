package models;

import java.util.Observable;

/**
 * Contains statistics about the user during their typing test.
 */
public class StatsModel extends Observable {
    private int correctKeystrokes, wrongKeystrokes;
    private int correctWords, wrongWords;
    private int time;

    /**
     * Creates a new models.StatsModel with all stats set to zero.
     */
    public StatsModel() {
        correctKeystrokes = wrongKeystrokes = correctWords = wrongWords = time = 0;
    }

    /**
     * Returns the number of correct keystrokes.
     *
     * @return the number of correct keystrokes
     */
    public int getCorrectKeystrokes() {
        return correctKeystrokes;
    }

    /**
     * Sets the number of correct keystrokes.
     *
     * @param correctKeystrokes the new number of correct keystrokes
     */
    public void setCorrectKeystrokes(int correctKeystrokes) {
        this.correctKeystrokes = correctKeystrokes;
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the number of wrong keystrokes.
     *
     * @return the number of wrong keystrokes
     */
    public int getWrongKeystrokes() {
        return wrongKeystrokes;
    }

    /**
     * Sets the number of wrong keystrokes.
     *
     * @param wrongKeystrokes the number of wrong keystrokes
     */
    public void setWrongKeystrokes(int wrongKeystrokes) {
        this.wrongKeystrokes = wrongKeystrokes;
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the total number of keystrokes.
     *
     * @return the total number of keystrokes
     */
    public int getTotalKeystrokes() {
        return correctKeystrokes + wrongKeystrokes;
    }

    /**
     * Returns the number of correct words.
     *
     * @return the number of correct words
     */
    public int getCorrectWords() {
        return correctWords;
    }

    /**
     * Sets the number of correct words
     *
     * @param correctWords the number of correct words
     */
    public void setCorrectWords(int correctWords) {
        this.correctWords = correctWords;
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the number of wrong words.
     *
     * @return the number of wrong words
     */
    public int getWrongWords() {
        return wrongWords;
    }

    /**
     * Sets the number of wrong words.
     *
     * @param wrongWords the number of wrong words
     */
    public void setWrongWords(int wrongWords) {
        this.wrongWords = wrongWords;
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the time of the typing test.
     *
     * @return the time of the typing test
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets the time of the typing test.
     *
     * @param time the time of the typing test
     */
    public void setTime(int time) {
        this.time = time;
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the wpm (words per minute).
     *
     * @return the wpm (words per minute)
     */
    public int getWpm() {
        if (time == 0)
            return 0;
        return (int) Math.round((correctKeystrokes - wrongKeystrokes) / 5d / (time / 60d));
    }

    /**
     * Returns the accuracy as a percentage.
     *
     * @return the accuracy as a percentage
     */
    public double getAccuracy() {
        if (correctKeystrokes + wrongKeystrokes == 0)
            return 0;
        return (double) (correctKeystrokes - wrongKeystrokes) / (correctKeystrokes + wrongKeystrokes) * 100;
    }

    /**
     * Resets the statistics of the typing test.
     */
    public void reset() {
        correctKeystrokes = wrongKeystrokes = correctWords = wrongWords = time = 0;
        setChanged();
        notifyObservers();
    }
}
