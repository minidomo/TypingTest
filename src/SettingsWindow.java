import javax.swing.*;
import java.awt.*;

/**
 * The window for the settings of the typing test application.
 */
public class SettingsWindow {
    private JComboBox<String> difficultyComboBox;
    private JTextField durationField;
    private JButton applyButton;
    private JFrame frame;

    /**
     * Loads the GUI for the settings.
     */
    public SettingsWindow() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("SettingsModel");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center JFrame in the middle of the screen        
        frame.add(createMainPanel());


        frame.pack();
    }

    /**
     * Creates the main panel with all the GUI components.
     *
     * @return the main panel
     */
    private JPanel createMainPanel() {
        applyButton = new JButton("Apply");
        applyButton.setPreferredSize(new Dimension(100, 40));
        applyButton.setMaximumSize(new Dimension(100, 40));
        applyButton.setMinimumSize(new Dimension(100, 40));
        applyButton.setHorizontalAlignment(SwingConstants.CENTER);
        applyButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        applyButton.setForeground(Color.white);
        applyButton.setBackground(Color.decode("#428BCA"));

        final int WIDTH = 300, HEIGHT = 50;
        JPanel applyPanel = new JPanel();
        applyPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        applyPanel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        applyPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        applyPanel.setOpaque(true);
        applyPanel.setBackground(Color.white);
        applyPanel.setAlignmentX(SwingConstants.LEFT);
        applyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        applyPanel.add(applyButton);

        JLabel durationLabel = new JLabel("Duration");
        durationLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        durationLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        durationLabel.setForeground(Color.decode("#333333"));

        final int WIDTH_DURATION_FIELD = 50, HEIGHT_DURATION_FIELD = 22;
        durationField = new JTextField("1:00");
        durationField.setPreferredSize(new Dimension(WIDTH_DURATION_FIELD, HEIGHT_DURATION_FIELD));
        durationField.setMaximumSize(new Dimension(WIDTH_DURATION_FIELD, HEIGHT_DURATION_FIELD));
        durationField.setMinimumSize(new Dimension(WIDTH_DURATION_FIELD, HEIGHT_DURATION_FIELD));
        durationField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        durationField.setOpaque(true);
        durationField.setForeground(Color.decode("#333333"));
        durationField.setBackground(Color.decode("#F9F9F9"));
        durationField.setFont(new Font("SansSerif", Font.BOLD, 16));
        durationField.setToolTipText("Time must be from 0:01 to 9:59 in the form M:SS.");

        final int WIDTH_DURATION_PANEL = 300, HEIGHT_DURATION_PANEL = 50;
        JPanel durationPanel = new JPanel();
        durationPanel.setPreferredSize(new Dimension(WIDTH_DURATION_PANEL, HEIGHT_DURATION_PANEL));
        durationPanel.setMinimumSize(new Dimension(WIDTH_DURATION_PANEL, HEIGHT_DURATION_PANEL));
        durationPanel.setMaximumSize(new Dimension(WIDTH_DURATION_PANEL, HEIGHT_DURATION_PANEL));
        durationPanel.setOpaque(true);
        durationPanel.setBackground(Color.decode("#F9F9F9"));
        durationPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.decode("#DDDDDD")));
        durationPanel.setAlignmentX(SwingConstants.LEFT);
        durationPanel.setLayout(new BorderLayout());
        durationPanel.add(durationLabel, BorderLayout.WEST);
        durationPanel.add(durationField, BorderLayout.EAST);

        final int WIDTH_DIFFICULTY_COMBO_BOX = 100, HEIGHT_DIFFICULTY_COMBO_BOX = 40;
        difficultyComboBox = new JComboBox<>();
        difficultyComboBox.addItem("Easy");
        difficultyComboBox.addItem("Hard");
        difficultyComboBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
        difficultyComboBox.setPreferredSize(new Dimension(WIDTH_DIFFICULTY_COMBO_BOX, HEIGHT_DIFFICULTY_COMBO_BOX));
        difficultyComboBox.setMinimumSize(new Dimension(WIDTH_DIFFICULTY_COMBO_BOX, HEIGHT_DIFFICULTY_COMBO_BOX));
        difficultyComboBox.setMaximumSize(new Dimension(WIDTH_DIFFICULTY_COMBO_BOX, HEIGHT_DIFFICULTY_COMBO_BOX));
        difficultyComboBox.setForeground(Color.decode("#333333"));

        JPanel difficultyComboBoxPanel = new JPanel();
        difficultyComboBoxPanel.setOpaque(false);
        difficultyComboBoxPanel.add(difficultyComboBox);

        JLabel difficultyLabel = new JLabel("Difficulty");
        difficultyLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        difficultyLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        difficultyLabel.setForeground(Color.decode("#333333"));

        final int WIDTH_DIFFICULTY_PANEL = 300, HEIGHT_DIFFICULTY_PANEL = 50;
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setPreferredSize(new Dimension(WIDTH_DIFFICULTY_PANEL, HEIGHT_DIFFICULTY_PANEL));
        difficultyPanel.setMinimumSize(new Dimension(WIDTH_DIFFICULTY_PANEL, HEIGHT_DIFFICULTY_PANEL));
        difficultyPanel.setMaximumSize(new Dimension(WIDTH_DIFFICULTY_PANEL, HEIGHT_DIFFICULTY_PANEL));
        difficultyPanel.setOpaque(true);
        difficultyPanel.setBackground(Color.white);
        difficultyPanel.setAlignmentX(SwingConstants.LEFT);
        difficultyPanel.setLayout(new BorderLayout());
        difficultyPanel.add(difficultyLabel, BorderLayout.WEST);
        difficultyPanel.add(difficultyComboBoxPanel, BorderLayout.EAST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(difficultyPanel);
        mainPanel.add(durationPanel);
        mainPanel.add(applyPanel);
        return mainPanel;
    }

    /**
     * Returns the difficulty combo box.
     *
     * @return the difficulty combo box
     */
    public JComboBox<String> getDifficultyComboBox() {
        return difficultyComboBox;
    }

    /**
     * Returns the duration text field.
     *
     * @return the duration text field
     */
    public JTextField getDurationField() {
        return durationField;
    }

    /**
     * Returns the apply button.
     *
     * @return the apply button
     */
    public JButton getApplyButton() {
        return applyButton;
    }

    /**
     * Returns the JFrame.
     *
     * @return the JFrame
     */
    public JFrame getFrame() {
        return frame;
    }
}
