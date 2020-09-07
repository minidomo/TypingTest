package views;

import models.SettingsModel;
import windows.SettingsWindow;

import javax.swing.*;
import java.awt.*;

/**
 * A viewer that adjusts the GUI related to the settings model.
 */
public class SettingsView {
    /**
     * Creates a views.SettingsView and adds an observer to the given models.SettingsModel.
     *
     * @param settingsModel   the settings model
     * @param settingsWindow  an observer
     * @param typingTestFrame an observer
     */
    public SettingsView(SettingsModel settingsModel, SettingsWindow settingsWindow, JFrame typingTestFrame) {
        settingsModel.addObserver((o, arg) -> {
            Integer event = (Integer) arg;
            if (event == SettingsModel.OPEN_EVENT) {
                typingTestFrame.setEnabled(false);
                settingsWindow.getFrame().setState(Frame.NORMAL);
                settingsWindow.getFrame().toFront();
                settingsWindow.getFrame().setVisible(true);
                settingsWindow.getFrame().requestFocus();
                if (settingsModel.getDifficulty() == SettingsModel.EASY_DIFFICULTY) {
                    settingsWindow.getDifficultyComboBox().setSelectedItem("Easy");
                } else {
                    settingsWindow.getDifficultyComboBox().setSelectedItem("Hard");
                }
                settingsWindow.getDurationField().setText(String.format("%d:%02d", settingsModel.getDuration() / 60, settingsModel.getDuration() % 60));
            } else if (event == SettingsModel.CLOSE_EVENT) {
                settingsWindow.getFrame().setVisible(false);
                typingTestFrame.setEnabled(true);
                typingTestFrame.requestFocus();
            }
        });
    }
}
