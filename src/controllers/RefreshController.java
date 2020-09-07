package controllers;

import models.ClockModel;
import models.StatsModel;
import models.TextBoxModel;
import models.UserInputModel;

import javax.swing.*;

/**
 * A controller that modifies models related to clicking the
 * refresh button.
 */
public class RefreshController {

    /**
     * Creates the controller and adds the event to the refresh button.
     *
     * @param clockModel     the clock of the typing test
     * @param textBoxModel   the text box of the typing test
     * @param userInputModel the user input of the typing test
     * @param statsModel     the statistics of the typing test
     * @param refreshButton  the refresh button
     */
    public RefreshController(ClockModel clockModel, TextBoxModel textBoxModel, UserInputModel userInputModel, StatsModel statsModel, JButton refreshButton) {
        refreshButton.addActionListener((e) -> {
            clockModel.reset();
            textBoxModel.reset();
            userInputModel.reset();
            statsModel.reset();
        });
    }
}
