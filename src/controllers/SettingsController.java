package controllers;

import models.SettingsModel;
import windows.SettingsWindow;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A controller that modifies models related to clicking the
 * settings button.
 */
public class SettingsController {
    /**
     * Creates the controller and adds the event to the settings button.
     *
     * @param settingsModel  the settings model
     * @param settingsButton the settings button
     * @param settingsWindow the settings window
     */
    public SettingsController(SettingsModel settingsModel, JButton settingsButton, SettingsWindow settingsWindow) {
        settingsButton.addActionListener((e) -> {
            settingsModel.setOpen(true);
        });
        settingsWindow.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                settingsModel.setOpen(false);
            }
        });
    }
}
