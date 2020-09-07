package structs;

import java.awt.*;

/**
 * Represents a word in the typing test.
 */
public class Word {
    public static final Color
            FOREGROUND_CORRECT = Color.decode("#008000"),
            FOREGROUND_WRONG = Color.red,
            FOREGROUND_DEFAULT = Color.black,
            BACKGROUND_CURRENT = Color.decode("#DDDDDD"),
            BACKGROUND_WRONG = Color.red,
            BACKGROUND_DEFAULT = Color.white;

    private String word;
    private Color foreground, background;

    /**
     * Creates a new structs.Word with a given word.
     *
     * @param word the word
     */
    public Word(String word) {
        this.word = word;
        foreground = FOREGROUND_DEFAULT;
        background = BACKGROUND_DEFAULT;
    }

    /**
     * Returns the String representation of this word.
     *
     * @return the String representation of this word
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns the foreground color of this word.
     *
     * @return the foreground color of this word
     */
    public Color getForeground() {
        return foreground;
    }

    /**
     * Sets the foreground color of this word.
     *
     * @param foreground the new foreground color of this word
     */
    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    /**
     * Returns the background color of this word.
     *
     * @return the background color of this word
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Sets the background color of this word.
     *
     * @param background the new background color of this word
     */
    public void setBackground(Color background) {
        this.background = background;
    }
}
