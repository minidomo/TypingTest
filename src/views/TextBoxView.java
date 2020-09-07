package views;

import models.TextBoxModel;

import javax.swing.*;
import java.awt.*;

/**
 * A viewer that adjusts the GUI related to the text box model.
 */
public class TextBoxView {
    /**
     * Creates a views.TextBoxView and adds observers to the given models.TextBoxModel.
     *
     * @param textBoxModel         the text box model
     * @param topRowWordDisplay    an observer
     * @param bottomRowWordDisplay an observer
     */
    public TextBoxView(TextBoxModel textBoxModel, JPanel topRowWordDisplay, JPanel bottomRowWordDisplay) {
        textBoxModel.addObserver((o, arg) -> {
            Integer event = (Integer) arg;
            if (event == TextBoxModel.RESET_EVENT) {
                topRowWordDisplay.removeAll();
                bottomRowWordDisplay.removeAll();
                int maxWidth = topRowWordDisplay.getWidth() - topRowWordDisplay.getInsets().left - topRowWordDisplay.getInsets().right;
                int additionalWidth = ((FlowLayout) topRowWordDisplay.getLayout()).getHgap();
                textBoxModel.formRows(maxWidth, additionalWidth, createLabel(""));
                textBoxModel.getRow(textBoxModel.getRowIndex()).forEach(word -> {
                    JLabel label = createLabel(word.getWord());
                    label.setForeground(word.getForeground());
                    label.setBackground(word.getBackground());
                    topRowWordDisplay.add(label);
                });
                if (textBoxModel.getRowIndex() + 1 < textBoxModel.getRowCount()) {
                    textBoxModel.getRow(textBoxModel.getRowIndex() + 1).forEach(word -> {
                        JLabel label = createLabel(word.getWord());
                        label.setForeground(word.getForeground());
                        label.setBackground(word.getBackground());
                        bottomRowWordDisplay.add(label);
                    });
                }
            } else if (event == TextBoxModel.WORD_UPDATE_EVENT) {
                topRowWordDisplay.removeAll();
                textBoxModel.getRow(textBoxModel.getRowIndex()).forEach(word -> {
                    JLabel label = createLabel(word.getWord());
                    label.setForeground(word.getForeground());
                    label.setBackground(word.getBackground());
                    topRowWordDisplay.add(label);
                });
            } else if (event == TextBoxModel.MOVE_ROWS_UPWARDS_EVENT) {
                topRowWordDisplay.removeAll();
                bottomRowWordDisplay.removeAll();
                textBoxModel.getRow(textBoxModel.getRowIndex()).forEach(word -> {
                    JLabel label = createLabel(word.getWord());
                    label.setForeground(word.getForeground());
                    label.setBackground(word.getBackground());
                    topRowWordDisplay.add(label);
                });
                if (textBoxModel.getRowIndex() + 1 < textBoxModel.getRowCount()) {
                    textBoxModel.getRow(textBoxModel.getRowIndex() + 1).forEach(word -> {
                        JLabel label = createLabel(word.getWord());
                        label.setForeground(word.getForeground());
                        label.setBackground(word.getBackground());
                        bottomRowWordDisplay.add(label);
                    });
                }
            } else if (event == TextBoxModel.CLEAR_EVENT) {
                topRowWordDisplay.removeAll();
                bottomRowWordDisplay.removeAll();
            }
            topRowWordDisplay.revalidate();
            bottomRowWordDisplay.revalidate();
            topRowWordDisplay.repaint();
            bottomRowWordDisplay.repaint();
        });
    }

    /**
     * Returns a label with an empty border, a font, and a given string.
     *
     * @param str the string to be on the label
     * @return a label with an empty border, a font, and a given string
     */
    private JLabel createLabel(String str) {
        JLabel label = new JLabel(str);
        label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        label.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        label.setOpaque(true);
        return label;
    }
}
