package controllers;

import filters.UserInputFilter;
import models.ClockModel;
import models.StatsModel;
import models.TextBoxModel;
import models.UserInputModel;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

/**
 * A controller that modifies models related to typing in the
 * input field.
 */
public class UserInputController {
    /**
     * Creates the controller and adds the the filter to the given text field.
     *
     * @param userInputModel the user input model
     * @param textBoxModel   the text box model
     * @param clockModel     the clock model
     * @param statsModel     the stats model
     * @param inputField     the input field
     */
    public UserInputController(UserInputModel userInputModel, TextBoxModel textBoxModel, ClockModel clockModel, StatsModel statsModel, JTextField inputField) {
        ((AbstractDocument) inputField.getDocument()).setDocumentFilter(new UserInputFilter(userInputModel, textBoxModel, clockModel, statsModel));
    }
}
