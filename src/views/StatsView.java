package views;

import models.StatsModel;

import javax.swing.*;

/**
 * A viewer that adjusts the GUI related to the stats model.
 */
public class StatsView {
    /**
     * Creates a views.StatsView and adds an observer to the given models.StatsModel.
     *
     * @param statsModel              the stats model
     * @param wpmLabel                an observer
     * @param keystrokesResultsLabel  an observer
     * @param accuracyPercentageLabel an observer
     * @param correctNumberLabel      an observer
     * @param wrongNumberLabel        an observer
     * @param timeTakenLabel          an observer
     */
    public StatsView(StatsModel statsModel, JLabel wpmLabel, JLabel keystrokesResultsLabel, JLabel accuracyPercentageLabel, JLabel correctNumberLabel, JLabel wrongNumberLabel, JLabel timeTakenLabel) {
        statsModel.addObserver((o, arg) -> {
            wpmLabel.setText(statsModel.getWpm() + " WPM");
            keystrokesResultsLabel.setText(String.format("<html><font color=#333333>(<font color=#527A1E>%d</font> | <font color=red>%d</font>) &nbsp; %d</font></html>", statsModel.getCorrectKeystrokes(), statsModel.getWrongKeystrokes(), statsModel.getCorrectKeystrokes() + statsModel.getWrongKeystrokes()));
            accuracyPercentageLabel.setText(String.format("%.2f%%", statsModel.getAccuracy()));
            correctNumberLabel.setText(String.valueOf(statsModel.getCorrectWords()));
            wrongNumberLabel.setText(String.valueOf(statsModel.getWrongWords()));
            timeTakenLabel.setText(String.format("%d:%02d", statsModel.getTime() / 60, statsModel.getTime() % 60));
        });
    }
}
