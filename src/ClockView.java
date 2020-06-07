import javax.swing.*;

/**
 * A viewer that adjusts the GUI related to the clock model.
 */
public class ClockView {
    /**
     * Creates a ClockView and adds an observer to the given ClockModel.
     *
     * @param clockModel      the clock model
     * @param timeMiddleLabel the observer
     */
    public ClockView(ClockModel clockModel, JLabel timeMiddleLabel) {
        clockModel.addObserver((o, arg) -> {
            Integer event = (Integer) arg;
            if (event == ClockModel.RESET_EVENT) {
                timeMiddleLabel.setText(getTimeFormattedString(clockModel.getTime()));
            } else if (event == ClockModel.UPDATE_TIME_EVENT) {
                timeMiddleLabel.setText(getTimeFormattedString(clockModel.getTime()));
            }
        });
    }

    /**
     * Returns the number of seconds as string in the form of m:ss.
     *
     * @param seconds the number of seconds
     * @return the number of seconds as a string in the form of m:ss
     */
    private String getTimeFormattedString(int seconds) {
        return String.format("%d:%02d", seconds / 60, seconds % 60);
    }
}
