/**
 * A controller that modifies models related to clicking the
 * apply button.
 */
public class ApplyController {
    /**
     * Creates the controller and adds the event to the apply button.
     *
     * @param settingsModel  the settings model
     * @param settingsWindow the settings window
     */
    public ApplyController(SettingsModel settingsModel, SettingsWindow settingsWindow) {
        settingsWindow.getApplyButton().addActionListener((e) -> {
            if (settingsWindow.getDifficultyComboBox().getSelectedItem().equals("Easy")) {
                settingsModel.setDifficulty(SettingsModel.EASY_DIFFICULTY);
            } else {
                settingsModel.setDifficulty(SettingsModel.HARD_DIFFICULTY);
            }
            String durationText = settingsWindow.getDurationField().getText();
            if (durationText.matches("\\d:[0-5]\\d") && !durationText.equals("0:00")) {
                String[] part = durationText.split(":");
                settingsModel.setDuration(Integer.parseInt(part[0]) * 60 + Integer.parseInt(part[1]));
            } else {
                settingsWindow.getDurationField().setText(String.format("%d:%02d", settingsModel.getDuration() / 60, settingsModel.getDuration() % 60));
            }
        });
    }
}
