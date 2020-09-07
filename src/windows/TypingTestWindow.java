package windows;

import controllers.ApplyController;
import controllers.RefreshController;
import controllers.SettingsController;
import controllers.UserInputController;
import models.*;
import views.*;

import javax.swing.*;
import java.awt.*;

/**
 * The window for the typing test.
 */
public class TypingTestWindow {
    private static final int WIDTH = 1000, HEIGHT = 650;

    private JPanel topRowWordDisplay, bottomRowWordDisplay;
    private JTextField inputField;
    private JLabel timeMiddleLabel, wpmLabel, keystrokesResultsLabel, accuracyPercentageLabel, correctNumberLabel, wrongNumberLabel, timeTakenLabel;
    private JButton refreshButton, settingsButton;

    /**
     * Sets up the JFrame and loads the GUI.
     */
    public TypingTestWindow() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.setProperty("awt.useSystemAAFontSettings", "on");
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setTitle("Typing Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center JFrame in the middle of the screen
        frame.add(createMainPanel());

        ToolTipManager.sharedInstance().setInitialDelay(0);

        SettingsWindow settingsWindow = new SettingsWindow();

        ClockModel clockModel = new ClockModel();
        TextBoxModel textBoxModel = new TextBoxModel();
        UserInputModel userInputModel = new UserInputModel();
        StatsModel statsModel = new StatsModel();

        RefreshController refreshController = new RefreshController(clockModel, textBoxModel, userInputModel, statsModel, refreshButton);
        ClockView clockView = new ClockView(clockModel, timeMiddleLabel);
        UserInputView userInputView = new UserInputView(userInputModel, inputField);
        TextBoxView textBoxView = new TextBoxView(textBoxModel, topRowWordDisplay, bottomRowWordDisplay);
        StatsView statsView = new StatsView(statsModel, wpmLabel, keystrokesResultsLabel, accuracyPercentageLabel, correctNumberLabel, wrongNumberLabel, timeTakenLabel);

        UserInputController userInputController = new UserInputController(userInputModel, textBoxModel, clockModel, statsModel, inputField);
        SettingsController settingsController = new SettingsController(SettingsModel.getInstance(), settingsButton, settingsWindow);
        SettingsView settingsView = new SettingsView(SettingsModel.getInstance(), settingsWindow, frame);
        ApplyController applyController = new ApplyController(SettingsModel.getInstance(), settingsWindow);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Creates the main panel with all the GUI components.
     *
     * @return the main panel
     */
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.decode("#BDDDFF"));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(createTopPanel());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(createMiddlePanel());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(createBottomPanel());
        return mainPanel;
    }

    /**
     * Creates the top panel which consists of the display of the words
     * of the typing test.
     *
     * @return the top panel
     */
    private JPanel createTopPanel() {
        final int WIDTH = 910, HEIGHT = 110;
        topRowWordDisplay = new JPanel();
        topRowWordDisplay.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        topRowWordDisplay.setPreferredSize(new Dimension(WIDTH, HEIGHT / 2));
        topRowWordDisplay.setMaximumSize(new Dimension(WIDTH, HEIGHT / 2));
        topRowWordDisplay.setMinimumSize(new Dimension(WIDTH, HEIGHT / 2));
        topRowWordDisplay.setBorder(BorderFactory.createEmptyBorder(9, 6, 3, 6));
        topRowWordDisplay.setBackground(Color.white);

        bottomRowWordDisplay = new JPanel();
        bottomRowWordDisplay.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        bottomRowWordDisplay.setPreferredSize(new Dimension(WIDTH, HEIGHT / 2));
        bottomRowWordDisplay.setMaximumSize(new Dimension(WIDTH, HEIGHT / 2));
        bottomRowWordDisplay.setMinimumSize(new Dimension(WIDTH, HEIGHT / 2));
        bottomRowWordDisplay.setBorder(BorderFactory.createEmptyBorder(3, 6, 9, 6));
        bottomRowWordDisplay.setBackground(Color.white);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        topPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        topPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        topPanel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        topPanel.add(topRowWordDisplay);
        topPanel.add(bottomRowWordDisplay);
        return topPanel;
    }

    /**
     * Creates the middle panel which consists of the field for user
     * input and buttons.
     *
     * @return the middle panel
     */
    private JPanel createMiddlePanel() {
        final int WIDTH_FIELD = 480, HEIGHT_FIELD = 50;
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(WIDTH_FIELD, HEIGHT_FIELD));
        inputField.setMaximumSize(new Dimension(WIDTH_FIELD, HEIGHT_FIELD));
        inputField.setMinimumSize(new Dimension(WIDTH_FIELD, HEIGHT_FIELD));
        inputField.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        inputField.setMargin(new Insets(0, 10, 0, 10));
        inputField.setEnabled(false);

        final int WIDTH_TIME = 70, HEIGHT_TIME = 50;
        timeMiddleLabel = new JLabel();
        timeMiddleLabel.setPreferredSize(new Dimension(WIDTH_TIME, HEIGHT_TIME));
        timeMiddleLabel.setMaximumSize(new Dimension(WIDTH_TIME, HEIGHT_TIME));
        timeMiddleLabel.setMinimumSize(new Dimension(WIDTH_TIME, HEIGHT_TIME));
        timeMiddleLabel.setFont(new Font("Arial", Font.PLAIN, 26));
        timeMiddleLabel.setHorizontalAlignment(JLabel.CENTER);
        timeMiddleLabel.setOpaque(true);
        timeMiddleLabel.setForeground(Color.white);
        timeMiddleLabel.setBackground(Color.decode("#3C4D5C"));

        final int WIDTH_REFRESH = 50, HEIGHT_REFRESH = 50;
        refreshButton = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/resources/images/redo-solid.png")).getImage().getScaledInstance(WIDTH_REFRESH / 3, HEIGHT_REFRESH / 3, Image.SCALE_SMOOTH)));
        refreshButton.setPreferredSize(new Dimension(WIDTH_REFRESH, HEIGHT_REFRESH));
        refreshButton.setMaximumSize(new Dimension(WIDTH_REFRESH, HEIGHT_REFRESH));
        refreshButton.setMinimumSize(new Dimension(WIDTH_REFRESH, HEIGHT_REFRESH));
        refreshButton.setHorizontalAlignment(SwingConstants.CENTER);
        refreshButton.setBackground(Color.decode("#428BCA"));

        final int WIDTH_SETTINGS = 50, HEIGHT_SETTINGS = 50;
        settingsButton = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/resources/images/cog-solid.png")).getImage().getScaledInstance(WIDTH_SETTINGS / 3, HEIGHT_SETTINGS / 3, Image.SCALE_SMOOTH)));
        settingsButton.setPreferredSize(new Dimension(WIDTH_SETTINGS, HEIGHT_SETTINGS));
        settingsButton.setMaximumSize(new Dimension(WIDTH_SETTINGS, HEIGHT_SETTINGS));
        settingsButton.setMinimumSize(new Dimension(WIDTH_SETTINGS, HEIGHT_SETTINGS));
        settingsButton.setHorizontalAlignment(SwingConstants.CENTER);
        settingsButton.setBackground(Color.decode("#428BCA"));

        final int WIDTH_MIDDLE_PANEL = 910, HEIGHT_MIDDLE_PANEL = 60;
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 5));
        middlePanel.setPreferredSize(new Dimension(WIDTH_MIDDLE_PANEL, HEIGHT_MIDDLE_PANEL));
        middlePanel.setMaximumSize(new Dimension(WIDTH_MIDDLE_PANEL, HEIGHT_MIDDLE_PANEL));
        middlePanel.setMinimumSize(new Dimension(WIDTH_MIDDLE_PANEL, HEIGHT_MIDDLE_PANEL));
        middlePanel.setBackground(Color.decode("#A7C8E7"));
        middlePanel.add(inputField);
        middlePanel.add(timeMiddleLabel);
        middlePanel.add(refreshButton);
        middlePanel.add(settingsButton);
        return middlePanel;
    }

    /**
     * Creates the bottom panel which consists of the user's results of
     * a typing test and some information about the typing test.
     *
     * @return the bottom panel
     */
    private JPanel createBottomPanel() {
        final int WIDTH_ABOUT_TITLE_LABEL = 620, HEIGHT_ABOUT_TITLE_LABEL = 30;
        JLabel aboutTitleLabel = new JLabel("About");
        aboutTitleLabel.setPreferredSize(new Dimension(WIDTH_ABOUT_TITLE_LABEL, HEIGHT_ABOUT_TITLE_LABEL));
        aboutTitleLabel.setMaximumSize(new Dimension(WIDTH_ABOUT_TITLE_LABEL, HEIGHT_ABOUT_TITLE_LABEL));
        aboutTitleLabel.setMinimumSize(new Dimension(WIDTH_ABOUT_TITLE_LABEL, HEIGHT_ABOUT_TITLE_LABEL));
        aboutTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        aboutTitleLabel.setAlignmentX(SwingConstants.LEFT);
        aboutTitleLabel.setOpaque(false);
        aboutTitleLabel.setForeground(Color.decode("#333333"));

        final int WIDTH_ABOUT_BODY_LABEL = 620, HEIGHT_ABOUT_BODY_LABEL = 90;
        JLabel aboutBodyLabel = new JLabel("<html>This typing test is a quick way "
                + "to test your typing speed. The words used in this typing test are sourced "
                + "from 10fastfingers.com and consist of the most common words in "
                + "the english language. By using this typing test, you can improve your "
                + "overall typing speed as you practice with words that you use all the "
                + "time.</html>");
        aboutBodyLabel.setPreferredSize(new Dimension(WIDTH_ABOUT_BODY_LABEL, HEIGHT_ABOUT_BODY_LABEL));
        aboutBodyLabel.setMaximumSize(new Dimension(WIDTH_ABOUT_BODY_LABEL, HEIGHT_ABOUT_BODY_LABEL));
        aboutBodyLabel.setMinimumSize(new Dimension(WIDTH_ABOUT_BODY_LABEL, HEIGHT_ABOUT_BODY_LABEL));
        aboutBodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        aboutBodyLabel.setAlignmentX(SwingConstants.LEFT);
        aboutBodyLabel.setVerticalAlignment(JLabel.TOP);
        aboutBodyLabel.setOpaque(false);
        aboutBodyLabel.setForeground(Color.decode("#333333"));

        JPanel aboutPanel = new JPanel();
        aboutPanel.setOpaque(false);
        aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
        aboutPanel.add(aboutTitleLabel);
        aboutPanel.add(aboutBodyLabel);

        final int WIDTH_INSTRUCTIONS_TITLE_LABEL = 620, HEIGHT_INSTRUCTIONS_TITLE_LABEL = 30;
        JLabel instructionsTitleLabel = new JLabel("How it works");
        instructionsTitleLabel.setPreferredSize(new Dimension(WIDTH_INSTRUCTIONS_TITLE_LABEL, HEIGHT_INSTRUCTIONS_TITLE_LABEL));
        instructionsTitleLabel.setMaximumSize(new Dimension(WIDTH_INSTRUCTIONS_TITLE_LABEL, HEIGHT_INSTRUCTIONS_TITLE_LABEL));
        instructionsTitleLabel.setMinimumSize(new Dimension(WIDTH_INSTRUCTIONS_TITLE_LABEL, HEIGHT_INSTRUCTIONS_TITLE_LABEL));
        instructionsTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        instructionsTitleLabel.setAlignmentX(SwingConstants.LEFT);
        instructionsTitleLabel.setOpaque(false);
        instructionsTitleLabel.setForeground(Color.decode("#333333"));

        final int WIDTH_INSTRUCTIONS_BODY_LABEL = 620, HEIGHT_INSTRUCTIONS_BODY_LABEL = 50;
        JLabel instructionsBodyLabel = new JLabel("<html>To start a new typing test, "
                + "click the refresh button. Type as many words as you can in the given "
                + "time limit, and as you type, the results of your typing test will update."
                + "</html>");
        instructionsBodyLabel.setPreferredSize(new Dimension(WIDTH_INSTRUCTIONS_BODY_LABEL, HEIGHT_INSTRUCTIONS_BODY_LABEL));
        instructionsBodyLabel.setMaximumSize(new Dimension(WIDTH_INSTRUCTIONS_BODY_LABEL, HEIGHT_INSTRUCTIONS_BODY_LABEL));
        instructionsBodyLabel.setMinimumSize(new Dimension(WIDTH_INSTRUCTIONS_BODY_LABEL, HEIGHT_INSTRUCTIONS_BODY_LABEL));
        instructionsBodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        instructionsBodyLabel.setAlignmentX(SwingConstants.LEFT);
        instructionsBodyLabel.setVerticalAlignment(JLabel.TOP);
        instructionsBodyLabel.setOpaque(false);
        instructionsBodyLabel.setForeground(Color.decode("#333333"));

        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setOpaque(false);
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));
        instructionsPanel.add(instructionsTitleLabel);
        instructionsPanel.add(instructionsBodyLabel);

        final int WIDTH_KEYSTROKES_INFO_TITLE_LABEL = 620, HEIGHT_KEYSTROKES_INFO_TITLE_LABEL = 30;
        JLabel keystrokesInfoTitleLabel = new JLabel("What is a keystroke?");
        keystrokesInfoTitleLabel.setPreferredSize(new Dimension(WIDTH_KEYSTROKES_INFO_TITLE_LABEL, HEIGHT_KEYSTROKES_INFO_TITLE_LABEL));
        keystrokesInfoTitleLabel.setMaximumSize(new Dimension(WIDTH_KEYSTROKES_INFO_TITLE_LABEL, HEIGHT_KEYSTROKES_INFO_TITLE_LABEL));
        keystrokesInfoTitleLabel.setMinimumSize(new Dimension(WIDTH_KEYSTROKES_INFO_TITLE_LABEL, HEIGHT_KEYSTROKES_INFO_TITLE_LABEL));
        keystrokesInfoTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        keystrokesInfoTitleLabel.setAlignmentX(SwingConstants.LEFT);
        keystrokesInfoTitleLabel.setOpaque(false);
        keystrokesInfoTitleLabel.setForeground(Color.decode("#333333"));

        final int WIDTH_KEYSTROKES_INFO_BODY_LABEL = 620, HEIGHT_KEYSTROKES_INFO_BODY_LABEL = 30;
        JLabel keystrokesInfoBodyLabel = new JLabel("<html>Every key you hit on the keyboard to type a letter is one keystroke.</html>");
        keystrokesInfoBodyLabel.setPreferredSize(new Dimension(WIDTH_KEYSTROKES_INFO_BODY_LABEL, HEIGHT_KEYSTROKES_INFO_BODY_LABEL));
        keystrokesInfoBodyLabel.setMaximumSize(new Dimension(WIDTH_KEYSTROKES_INFO_BODY_LABEL, HEIGHT_KEYSTROKES_INFO_BODY_LABEL));
        keystrokesInfoBodyLabel.setMinimumSize(new Dimension(WIDTH_KEYSTROKES_INFO_BODY_LABEL, HEIGHT_KEYSTROKES_INFO_BODY_LABEL));
        keystrokesInfoBodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        keystrokesInfoBodyLabel.setAlignmentX(SwingConstants.LEFT);
        keystrokesInfoBodyLabel.setVerticalAlignment(JLabel.TOP);
        keystrokesInfoBodyLabel.setOpaque(false);
        keystrokesInfoBodyLabel.setForeground(Color.decode("#333333"));

        JPanel keystrokeInfoPanel = new JPanel();
        keystrokeInfoPanel.setOpaque(false);
        keystrokeInfoPanel.setLayout(new BoxLayout(keystrokeInfoPanel, BoxLayout.Y_AXIS));
        keystrokeInfoPanel.add(keystrokesInfoTitleLabel);
        keystrokeInfoPanel.add(keystrokesInfoBodyLabel);

        final int WIDTH_WPM_INFO_TITLE_LABEL = 620, HEIGHT_WPM_INFO_TITLE_LABEL = 30;
        JLabel wpmInfoTitleLabel = new JLabel("What does WPM mean?");
        wpmInfoTitleLabel.setPreferredSize(new Dimension(WIDTH_WPM_INFO_TITLE_LABEL, HEIGHT_WPM_INFO_TITLE_LABEL));
        wpmInfoTitleLabel.setMaximumSize(new Dimension(WIDTH_WPM_INFO_TITLE_LABEL, HEIGHT_WPM_INFO_TITLE_LABEL));
        wpmInfoTitleLabel.setMinimumSize(new Dimension(WIDTH_WPM_INFO_TITLE_LABEL, HEIGHT_WPM_INFO_TITLE_LABEL));
        wpmInfoTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        wpmInfoTitleLabel.setAlignmentX(SwingConstants.LEFT);
        wpmInfoTitleLabel.setOpaque(false);
        wpmInfoTitleLabel.setForeground(Color.decode("#333333"));

        final int WIDTH_WPM_INFO_BODY_LABEL = 620, HEIGHT_WPM_INFO_BODY_LABEL = 50;
        JLabel wpmInfoBodyLabel = new JLabel("<html>WPM means \"Words per minute\" and is based on this calculation: 5 keystrokes equal 1 WPM.</html>");
        wpmInfoBodyLabel.setPreferredSize(new Dimension(WIDTH_WPM_INFO_BODY_LABEL, HEIGHT_WPM_INFO_BODY_LABEL));
        wpmInfoBodyLabel.setMaximumSize(new Dimension(WIDTH_WPM_INFO_BODY_LABEL, HEIGHT_WPM_INFO_BODY_LABEL));
        wpmInfoBodyLabel.setMinimumSize(new Dimension(WIDTH_WPM_INFO_BODY_LABEL, HEIGHT_WPM_INFO_BODY_LABEL));
        wpmInfoBodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        wpmInfoBodyLabel.setAlignmentX(SwingConstants.LEFT);
        wpmInfoBodyLabel.setVerticalAlignment(JLabel.TOP);
        wpmInfoBodyLabel.setOpaque(false);
        wpmInfoBodyLabel.setForeground(Color.decode("#333333"));

        JPanel wpmInfoPanel = new JPanel();
        wpmInfoPanel.setOpaque(false);
        wpmInfoPanel.setLayout(new BoxLayout(wpmInfoPanel, BoxLayout.Y_AXIS));
        wpmInfoPanel.add(wpmInfoTitleLabel);
        wpmInfoPanel.add(wpmInfoBodyLabel);

        final int WIDTH_INFORMATION_PANEL = 620, HEIGHT_INFORMATION_PANEL = 365;
        JPanel informationPanel = new JPanel();
        informationPanel.setPreferredSize(new Dimension(WIDTH_INFORMATION_PANEL, HEIGHT_INFORMATION_PANEL));
        informationPanel.setMaximumSize(new Dimension(WIDTH_INFORMATION_PANEL, HEIGHT_INFORMATION_PANEL));
        informationPanel.setMinimumSize(new Dimension(WIDTH_INFORMATION_PANEL, HEIGHT_INFORMATION_PANEL));
        informationPanel.setOpaque(true);
        informationPanel.setBackground(Color.decode("#DCEBFB"));
        informationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
        informationPanel.add(aboutPanel);
        informationPanel.add(instructionsPanel);
        informationPanel.add(wpmInfoPanel);
        informationPanel.add(keystrokeInfoPanel);

        final int WIDTH_BOTTOM_PANEL = 910, HEIGHT_BOTTOM_PANEL = 365;
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        bottomPanel.setPreferredSize(new Dimension(WIDTH_BOTTOM_PANEL, HEIGHT_BOTTOM_PANEL));
        bottomPanel.setMaximumSize(new Dimension(WIDTH_BOTTOM_PANEL, HEIGHT_BOTTOM_PANEL));
        bottomPanel.setMinimumSize(new Dimension(WIDTH_BOTTOM_PANEL, HEIGHT_BOTTOM_PANEL));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bottomPanel.add(createResultsPanel());
        bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        bottomPanel.add(informationPanel);
        return bottomPanel;
    }

    /**
     * Creates the results panel consisting of statistics of the user's
     * typing test.
     *
     * @return the results panel
     */
    private JPanel createResultsPanel() {
        final int WIDTH_RESULT_LABEL = 280, HEIGHT_RESULT_LABEL = 35;
        JLabel resultsLabel = new JLabel("Result");
        resultsLabel.setPreferredSize(new Dimension(WIDTH_RESULT_LABEL, HEIGHT_RESULT_LABEL));
        resultsLabel.setMaximumSize(new Dimension(WIDTH_RESULT_LABEL, HEIGHT_RESULT_LABEL));
        resultsLabel.setMinimumSize(new Dimension(WIDTH_RESULT_LABEL, HEIGHT_RESULT_LABEL));
        resultsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        resultsLabel.setOpaque(true);
        resultsLabel.setForeground(Color.white);
        resultsLabel.setBackground(Color.decode("#4E80A0"));
        resultsLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        resultsLabel.setAlignmentX(JLabel.LEFT);

        wpmLabel = new JLabel();
        wpmLabel.setFont(new Font("SansSerif", Font.BOLD, 46));
        wpmLabel.setForeground(Color.decode("#527A1E"));
        wpmLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel wpmInWordsLabel = new JLabel("(words per minute)");
        wpmInWordsLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        wpmInWordsLabel.setForeground(Color.decode("#81888C"));
        wpmInWordsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        final int WIDTH_WPM_PANEL = 280, HEIGHT_WPM_PANEL = 100;
        JPanel wpmPanel = new JPanel();
        wpmPanel.setPreferredSize(new Dimension(WIDTH_WPM_PANEL, HEIGHT_WPM_PANEL));
        wpmPanel.setMaximumSize(new Dimension(WIDTH_WPM_PANEL, HEIGHT_WPM_PANEL));
        wpmPanel.setMinimumSize(new Dimension(WIDTH_WPM_PANEL, HEIGHT_WPM_PANEL));
        wpmPanel.setOpaque(true);
        wpmPanel.setBackground(Color.decode("#F9F9F9"));
        wpmPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.decode("#DDDDDD")));
        wpmPanel.setAlignmentX(SwingConstants.LEFT);
        wpmPanel.setLayout(new BoxLayout(wpmPanel, BoxLayout.Y_AXIS));
        wpmPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        wpmPanel.add(wpmLabel);
        wpmPanel.add(wpmInWordsLabel);

        JLabel keystrokesLabel = new JLabel("Keystrokes");
        keystrokesLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        keystrokesLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        keystrokesLabel.setForeground(Color.decode("#333333"));

        keystrokesResultsLabel = new JLabel();
        keystrokesResultsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        keystrokesResultsLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        final int WIDTH_KEYSTROKES_PANEL = 280, HEIGHT_KEYSTROKES_PANEL = 38;
        JPanel keystrokesPanel = new JPanel();
        keystrokesPanel.setPreferredSize(new Dimension(WIDTH_KEYSTROKES_PANEL, HEIGHT_KEYSTROKES_PANEL));
        keystrokesPanel.setMaximumSize(new Dimension(WIDTH_KEYSTROKES_PANEL, HEIGHT_KEYSTROKES_PANEL));
        keystrokesPanel.setMinimumSize(new Dimension(WIDTH_KEYSTROKES_PANEL, HEIGHT_KEYSTROKES_PANEL));
        keystrokesPanel.setOpaque(true);
        keystrokesPanel.setBackground(Color.white);
        keystrokesPanel.setAlignmentX(SwingConstants.LEFT);
        keystrokesPanel.setLayout(new BorderLayout());
        keystrokesPanel.add(keystrokesLabel, BorderLayout.WEST);
        keystrokesPanel.add(keystrokesResultsLabel, BorderLayout.EAST);

        JLabel accuracyLabel = new JLabel("Accuracy");
        accuracyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        accuracyLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        accuracyLabel.setForeground(Color.decode("#333333"));

        accuracyPercentageLabel = new JLabel();
        accuracyPercentageLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        accuracyPercentageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        accuracyPercentageLabel.setForeground(Color.decode("#333333"));

        final int WIDTH_ACCURACY_PANEL = 280, HEIGHT_ACCURACY_PANEL = 38;
        JPanel accuracyPanel = new JPanel();
        accuracyPanel.setPreferredSize(new Dimension(WIDTH_ACCURACY_PANEL, HEIGHT_ACCURACY_PANEL));
        accuracyPanel.setMaximumSize(new Dimension(WIDTH_ACCURACY_PANEL, HEIGHT_ACCURACY_PANEL));
        accuracyPanel.setMinimumSize(new Dimension(WIDTH_ACCURACY_PANEL, HEIGHT_ACCURACY_PANEL));
        accuracyPanel.setOpaque(true);
        accuracyPanel.setBackground(Color.decode("#F9F9F9"));
        accuracyPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.decode("#DDDDDD")));
        accuracyPanel.setAlignmentX(SwingConstants.LEFT);
        accuracyPanel.setLayout(new BorderLayout());
        accuracyPanel.add(accuracyLabel, BorderLayout.WEST);
        accuracyPanel.add(accuracyPercentageLabel, BorderLayout.EAST);

        JLabel correctLabel = new JLabel("Correct words");
        correctLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        correctLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        correctLabel.setForeground(Color.decode("#333333"));

        correctNumberLabel = new JLabel();
        correctNumberLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        correctNumberLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        correctNumberLabel.setForeground(Color.decode("#527A1E"));

        final int WIDTH_CORRECT_WORDS_PANEL = 280, HEIGHT_CORRECT_WORDS_PANEL = 38;
        JPanel correctWordsPanel = new JPanel();
        correctWordsPanel.setPreferredSize(new Dimension(WIDTH_CORRECT_WORDS_PANEL, HEIGHT_CORRECT_WORDS_PANEL));
        correctWordsPanel.setMaximumSize(new Dimension(WIDTH_CORRECT_WORDS_PANEL, HEIGHT_CORRECT_WORDS_PANEL));
        correctWordsPanel.setMinimumSize(new Dimension(WIDTH_CORRECT_WORDS_PANEL, HEIGHT_CORRECT_WORDS_PANEL));
        correctWordsPanel.setOpaque(true);
        correctWordsPanel.setBackground(Color.white);
        correctWordsPanel.setAlignmentX(SwingConstants.LEFT);
        correctWordsPanel.setLayout(new BorderLayout());
        correctWordsPanel.add(correctLabel, BorderLayout.WEST);
        correctWordsPanel.add(correctNumberLabel, BorderLayout.EAST);

        JLabel wrongLabel = new JLabel("Wrong words");
        wrongLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        wrongLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        wrongLabel.setForeground(Color.decode("#333333"));

        wrongNumberLabel = new JLabel();
        wrongNumberLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        wrongNumberLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        wrongNumberLabel.setForeground(Color.red);

        final int WIDTH_WRONG_WORDS_PANEL = 280, HEIGHT_WRONG_WORDS_PANEL = 38;
        JPanel wrongWordsPanel = new JPanel();
        wrongWordsPanel.setPreferredSize(new Dimension(WIDTH_WRONG_WORDS_PANEL, HEIGHT_WRONG_WORDS_PANEL));
        wrongWordsPanel.setMaximumSize(new Dimension(WIDTH_WRONG_WORDS_PANEL, HEIGHT_WRONG_WORDS_PANEL));
        wrongWordsPanel.setMinimumSize(new Dimension(WIDTH_WRONG_WORDS_PANEL, HEIGHT_WRONG_WORDS_PANEL));
        wrongWordsPanel.setOpaque(true);
        wrongWordsPanel.setBackground(Color.decode("#F9F9F9"));
        wrongWordsPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.decode("#DDDDDD")));
        wrongWordsPanel.setAlignmentX(SwingConstants.LEFT);
        wrongWordsPanel.setLayout(new BorderLayout());
        wrongWordsPanel.add(wrongLabel, BorderLayout.WEST);
        wrongWordsPanel.add(wrongNumberLabel, BorderLayout.EAST);

        JLabel timeLabel = new JLabel("Time");
        timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        timeLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        timeLabel.setForeground(Color.decode("#333333"));

        timeTakenLabel = new JLabel();
        timeTakenLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        timeTakenLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        timeTakenLabel.setForeground(Color.decode("#333333"));

        final int WIDTH_TIME_PANEL = 280, HEIGHT_TIME_PANEL = 38;
        JPanel timePanel = new JPanel();
        timePanel.setPreferredSize(new Dimension(WIDTH_TIME_PANEL, HEIGHT_TIME_PANEL));
        timePanel.setMaximumSize(new Dimension(WIDTH_TIME_PANEL, HEIGHT_TIME_PANEL));
        timePanel.setMinimumSize(new Dimension(WIDTH_TIME_PANEL, HEIGHT_TIME_PANEL));
        timePanel.setOpaque(true);
        timePanel.setBackground(Color.white);
        timePanel.setAlignmentX(SwingConstants.LEFT);
        timePanel.setLayout(new BorderLayout());
        timePanel.add(timeLabel, BorderLayout.WEST);
        timePanel.add(timeTakenLabel, BorderLayout.EAST);

        final int WIDTH_RESULTS_PANEL = 280, HEIGHT_RESULTS_PANEL = 365;
        JPanel resultsPanel = new JPanel();
        resultsPanel.setOpaque(false);
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setPreferredSize(new Dimension(WIDTH_RESULTS_PANEL, HEIGHT_RESULTS_PANEL));
        resultsPanel.setMaximumSize(new Dimension(WIDTH_RESULTS_PANEL, HEIGHT_RESULTS_PANEL));
        resultsPanel.setMinimumSize(new Dimension(WIDTH_RESULTS_PANEL, HEIGHT_RESULTS_PANEL));
        resultsPanel.add(resultsLabel);
        resultsPanel.add(wpmPanel);
        resultsPanel.add(keystrokesPanel);
        resultsPanel.add(accuracyPanel);
        resultsPanel.add(correctWordsPanel);
        resultsPanel.add(wrongWordsPanel);
        resultsPanel.add(timePanel);
        return resultsPanel;
    }

    /**
     * Returns the top row panel that contains the words displayed
     * on the top row of the typing test.
     *
     * @return the top row panel
     */
    public JPanel getTopRowWordDisplay() {
        return topRowWordDisplay;
    }

    /**
     * Returns the bottom row panel that contains the words displayed
     * on the bottom row of the typing test.
     *
     * @return the bottom row panel
     */
    public JPanel getBottomRowWordDisplay() {
        return bottomRowWordDisplay;
    }

    /**
     * Returns the text field that the user types in.
     *
     * @return the text field that the user types in
     */
    public JTextField getInputField() {
        return inputField;
    }

    /**
     * Returns the label that indicates the time left or taken during
     * the typing test.
     *
     * @return the label that indicates the time left or taken during
     * the typing test
     */
    public JLabel getTimeMiddleLabel() {
        return timeMiddleLabel;
    }

    /**
     * Returns the WPM label that displays the user's WPM from a
     * typing test.
     *
     * @return the WPM label that displays the user's WPM from a
     * typing test
     */
    public JLabel getWpmLabel() {
        return wpmLabel;
    }

    /**
     * Returns the keystrokes label that displays the number of
     * correct, wrong, and total keystrokes from a typing test.
     *
     * @return the keystrokes label that displays the number of
     * correct, wrong, and total keystrokes from a typing test
     */
    public JLabel getKeystrokesResultsLabel() {
        return keystrokesResultsLabel;
    }

    /**
     * Returns the accuracy label that display the user's accuracy
     * as a percentage from a typing test.
     *
     * @return the accuracy label that display the user's accuracy
     * as a percentage from a typing test
     */
    public JLabel getAccuracyPercentageLabel() {
        return accuracyPercentageLabel;
    }

    /**
     * Returns the correct number of words label that displays
     * how many correct words the user typed.
     *
     * @return the correct number of words label that displays
     * how many correct words the user typed
     */
    public JLabel getCorrectNumberLabel() {
        return correctNumberLabel;
    }

    /**
     * Returns the wrong number of words label that displays
     * how many wrong words the user typed.
     *
     * @return the wrong number of words label that displays
     * how many wrong words the user typed
     */
    public JLabel getWrongNumberLabel() {
        return wrongNumberLabel;
    }

    /**
     * Returns the time taken label that displays how long the user
     * typed for during the typing test.
     *
     * @return the time taken label that displays how long the user
     * typed for during the typing test
     */
    public JLabel getTimeTakenLabel() {
        return timeTakenLabel;
    }

    /**
     * Returns the refresh button that refreshes the text for
     * a new typing test.
     *
     * @return the refresh button that refreshes the text for
     * a new typing test
     */
    public JButton getRefreshButton() {
        return refreshButton;
    }

    /**
     * Returns the settings button that opens the settings window
     * of the application.
     *
     * @return the settings button that opens the settings window
     * of the application
     */
    public JButton getSettingsButton() {
        return settingsButton;
    }
}
