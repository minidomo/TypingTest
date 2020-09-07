package models;

import java.util.Observable;

/**
 * Represents the user's input in the input field.
 */
public class UserInputModel extends Observable {
    private String currentInput;
    private boolean currentlyAcceptingInput;

    /**
     * Creates a new models.UserInputModel with an empty input and is
     * currently accepting input.
     */
    public UserInputModel() {
        currentInput = "";
        currentlyAcceptingInput = true;
    }

    /**
     * Resets the models.UserInputModel to having an empty input and is
     * currently accepting input.
     */
    public void reset() {
        currentInput = "";
        currentlyAcceptingInput = true;
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the current input from the user.
     *
     * @return the current input from the user
     */
    public String getCurrentInput() {
        return currentInput;
    }

    /**
     * Sets the current input of the user.
     *
     * @param currentInput the current input of the user
     */
    public void setCurrentInput(String currentInput) {
        this.currentInput = currentInput;
    }

    /**
     * Returns true if the typing test is currently accepting input,
     * false otherwise.
     *
     * @return true if the typing test is currently accepting input,
     * false otherwise.
     */
    public boolean isCurrentlyAcceptingInput() {
        return currentlyAcceptingInput;
    }

    /**
     * Sets the value to true if the typing test is currently accepting
     * input, false otherwise.
     *
     * @param currentlyAcceptingInput true if the typing test is currently
     *                                accepting input, false otherwise
     */
    public void setCurrentlyAcceptingInput(boolean currentlyAcceptingInput) {
        this.currentlyAcceptingInput = currentlyAcceptingInput;
        setChanged();
        notifyObservers();
    }
}
