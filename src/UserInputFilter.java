import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * A filter that modifies the text field while the user types
 * during the typing test.
 */
public class UserInputFilter extends DocumentFilter {
    private UserInputModel userInputModel;
    private TextBoxModel textBoxModel;
    private ClockModel clockModel;
    private StatsModel statsModel;
    private Timer timer;

    /**
     * Creates a new filter given the user input model.
     *
     * @param userInputModel the user input model
     * @param textBoxModel   the text box model
     * @param clockModel     the clock model
     */
    public UserInputFilter(UserInputModel userInputModel, TextBoxModel textBoxModel, ClockModel clockModel, StatsModel statsModel) {
        this.userInputModel = userInputModel;
        this.textBoxModel = textBoxModel;
        this.clockModel = clockModel;
        this.statsModel = statsModel;
        timer = new Timer(clockModel, userInputModel, textBoxModel, statsModel);
    }

    /**
     * Updates the text field, user input model, and text box model as the user types.
     *
     * @param fb     FilterBypass that can be used to mutate Document
     * @param offset Location in Document
     * @param length Length of text to delete
     * @param text   Text to insert, null indicates no text to insert
     * @param attrs  AttributeSet indicating attributes of inserted text,
     *               null is legal.
     * @throws BadLocationException the given insert position is not a
     *                              valid position within the document
     */
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null || text.isEmpty()) {
            // if text field is set to null or is empty replace normally
            super.replace(fb, offset, length, text, attrs);
        } else if (text.length() == 1) {
            if (!timer.isActive()) {
                timer.setActive(true);
                timer.start();
            }
            Word currentWord = textBoxModel.getRow(textBoxModel.getRowIndex()).get(textBoxModel.getColumnIndex());
            if (text.equals(" ")) {
                if (userInputModel.getCurrentInput().length() > 0) {
                    if (currentWord.getWord().equals(userInputModel.getCurrentInput())) {
                        textBoxModel.markCurrentWordAsCorrect();
                        statsModel.setCorrectWords(statsModel.getCorrectWords() + 1);
                        statsModel.setCorrectKeystrokes(statsModel.getCorrectKeystrokes() + userInputModel.getCurrentInput().length() + 1);
                    } else {
                        textBoxModel.markCurrentWordAsWrong();
                        statsModel.setWrongWords(statsModel.getWrongWords() + 1);
                        statsModel.setWrongKeystrokes(statsModel.getWrongKeystrokes() + userInputModel.getCurrentInput().length() / 2 + 1);
                    }
                    if (textBoxModel.goToNextWord()) {
                        textBoxModel.markCurrentWordAsCurrentlyCorrect();
                        if (textBoxModel.needsToMoveRowsUp()) {
                            textBoxModel.moveRowsUp();
                        }
                    } else {
                        // typing test ended, shouldn't happen with RANDOM unless cheating 
                    }
                    remove(fb, 0, userInputModel.getCurrentInput().length());
                }
            } else {
                userInputModel.setCurrentInput(userInputModel.getCurrentInput() + text);
                if (currentWord.getWord().startsWith(userInputModel.getCurrentInput())) {
                    textBoxModel.markCurrentWordAsCurrentlyCorrect();
                } else {
                    textBoxModel.markCurrentWordAsCurrentlyWrong();
                }
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    /**
     * Updates the text field and the user input model as the user removes text
     * from the text field.
     *
     * @param fb     FilterBypass that can be used to mutate Document
     * @param offset the offset from the beginning &gt;= 0
     * @param length the number of characters to remove &gt;= 0
     * @throws BadLocationException some portion of the removal range
     *                              was not a valid part of the document.  The location in the exception
     *                              is the first bad position encountered.
     */
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        userInputModel.setCurrentInput(userInputModel.getCurrentInput().substring(0, offset));
        super.remove(fb, offset, length);
    }
}
