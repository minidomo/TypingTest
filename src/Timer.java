import java.util.TimerTask;

/**
 * A timer that handles decreasing the time of the typing test.
 */
public class Timer {
    private ClockModel clockModel;
    private UserInputModel userInputModel;
    private TextBoxModel textBoxModel;
    private StatsModel statsModel;
    private java.util.Timer timer;
    private TimerTask timerTask;
    private boolean active;

    /**
     * Creates a new timer that will decrease the time of a typing test when it starts
     * and stop when the time is zero.
     *
     * @param clockModel     the clock model
     * @param userInputModel the user input model
     * @param textBoxModel   the text box model
     * @param statsModel     the stats model
     */
    public Timer(ClockModel clockModel, UserInputModel userInputModel, TextBoxModel textBoxModel, StatsModel statsModel) {
        this.clockModel = clockModel;
        this.userInputModel = userInputModel;
        this.textBoxModel = textBoxModel;
        this.statsModel = statsModel;
        timer = new java.util.Timer();
        active = false;
        clockModel.addObserver((o, arg) -> {
            Integer event = (Integer) arg;
            if (event == ClockModel.RESET_EVENT)
                stop();
        });
    }

    /**
     * Returns true if the timer is active (counting down), false otherwise.
     *
     * @return true if the timer is active (counting down), false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the activity of the timer.
     *
     * @param active true if the timer is active, false otherwise
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Starts the timer. Automatically calls {@link Timer#stop()} when the timer is done.
     */
    public void start() {
        int delay = 1000;
        timerTask = new TimerTask() {
            public void run() {
                statsModel.setTime(statsModel.getTime() + 1);
                clockModel.setTime(clockModel.getTime() - 1);
                if (clockModel.getTime() <= 0) {
                    stop();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, delay, delay);
    }

    /**
     * Stops the timer and is automatically called within {@link Timer#start()}
     * once the timer is done.
     */
    public void stop() {
        if (timerTask != null)
            timerTask.cancel();
        active = false;
        textBoxModel.clear();
        if (userInputModel.isCurrentlyAcceptingInput())
            userInputModel.setCurrentlyAcceptingInput(false);
    }
}
