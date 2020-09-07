package views;

import models.UserInputModel;

import javax.swing.*;

/**
 * A viewer that adjusts the GUI related to the user input model.
 */
public class UserInputView {
    /**
     * Creates a views.UserInputView and adds an observer to the given models.UserInputModel.
     *
     * @param userInputModel the user input model
     * @param inputField     the observer
     */
    public UserInputView(UserInputModel userInputModel, JTextField inputField) {
        userInputModel.addObserver((o, arg) -> {
            inputField.setText(null);
            if (userInputModel.isCurrentlyAcceptingInput()) {
                inputField.setEnabled(true);
            } else {
                inputField.setEnabled(false);
            }
            inputField.requestFocusInWindow();
        });
    }
}
