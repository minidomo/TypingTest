import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Manages words in a typing test.
 */
public class TextBoxModel extends Observable {
    private ArrayList<ArrayList<Word>> rows;
    private ArrayList<Word> words;
    private int rowIndex, columnIndex;

    private static final int MAX_COUNT_RANDOM = 1000;
    public static final int
            RESET_EVENT = 0,
            WORD_UPDATE_EVENT = 1,
            MOVE_ROWS_UPWARDS_EVENT = 2,
            CLEAR_EVENT = 3;

    /**
     * Creates a TextBoxModel with default values based on SettingsModel.
     */
    public TextBoxModel() {
        rows = new ArrayList<>();
        words = new ArrayList<>(MAX_COUNT_RANDOM);
        reset();
    }

    /**
     * Resets the TextBox with new words.
     */
    public void reset() {
        rowIndex = columnIndex = 0;
        words.clear();
        rows.clear();
        if (SettingsModel.getInstance().getDifficulty() == SettingsModel.EASY_DIFFICULTY) {
            for (int i = 0; i < MAX_COUNT_RANDOM; i++) {
                int x = (int) (Math.random() * WordDatabase.getInstance().getEasyWordCount());
                words.add(WordDatabase.getInstance().getEasyWord(x));
            }
        } else {
            for (int i = 0; i < MAX_COUNT_RANDOM; i++) {
                int x = (int) (Math.random() * WordDatabase.getInstance().getHardWordCount());
                words.add(WordDatabase.getInstance().getHardWord(x));
            }
        }
        words.get(0).setBackground(Word.BACKGROUND_CURRENT);
        setChanged();
        notifyObservers(RESET_EVENT);
    }

    /**
     * Forms rows of the current words based on the maximum width of a
     * row to ensure that every row does not have words that will be
     * out of bounds of the GUI.
     *
     * @param maxWidth        the maximum width of a row
     * @param additionalWidth the additional width of a word
     * @param referenceLabel  a reference label to get the preferred
     *                        width of a word
     */
    public void formRows(int maxWidth, int additionalWidth, JLabel referenceLabel) {
        int x = 0;
        while (x < words.size()) {
            ArrayList<Word> row = new ArrayList<>();
            int currentWidth = 0;
            boolean filled = false;
            while (!filled && x < words.size()) {
                Word word = words.get(x);
                referenceLabel.setText(word.getWord());
                int wordWidth = (int) referenceLabel.getPreferredSize().getWidth() + additionalWidth;
                if (currentWidth + wordWidth <= maxWidth) {
                    currentWidth += wordWidth;
                    row.add(word);
                    x++;
                } else {
                    filled = true;
                }
            }
            rows.add(row);
        }
    }

    /**
     * Returns the number of rows this object has.
     *
     * @return the number of rows this object has
     */
    public int getRowCount() {
        return rows.size();
    }

    /**
     * Returns the number of columns at a given row.
     *
     * @param rowIndex the row to check
     * @return the number of columns at a given row
     */
    public int getColumnCount(int rowIndex) {
        return rows.get(rowIndex).size();
    }

    /**
     * Returns the current row.
     *
     * @return the current row
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Sets the current row
     *
     * @param rowIndex the new row
     */
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * Returns the current column.
     *
     * @return the current column
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * Sets the current column.
     *
     * @param columnIndex the new column
     */
    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    /**
     * Returns the words at a given row.
     *
     * @param rowIndex the index of the row to get
     * @return the words at a given row
     */
    public ArrayList<Word> getRow(int rowIndex) {
        return rows.get(rowIndex);
    }

    /**
     * Sets the background of the word at the current row and column
     * of this object to a gray color.
     */
    public void markCurrentWordAsCurrentlyCorrect() {
        Word word = rows.get(rowIndex).get(columnIndex);
        word.setForeground(Word.FOREGROUND_DEFAULT);
        word.setBackground(Word.BACKGROUND_CURRENT);
        setChanged();
        notifyObservers(WORD_UPDATE_EVENT);
    }

    /**
     * Sets the background of the word at the current row and column
     * of this object to a red color.
     */
    public void markCurrentWordAsCurrentlyWrong() {
        Word word = rows.get(rowIndex).get(columnIndex);
        word.setForeground(Word.FOREGROUND_DEFAULT);
        word.setBackground(Word.BACKGROUND_WRONG);
        setChanged();
        notifyObservers(WORD_UPDATE_EVENT);
    }

    /**
     * Sets background to a white color and the foreground to a red
     * color of the word at the current row and column of this object.
     */
    public void markCurrentWordAsWrong() {
        Word word = rows.get(rowIndex).get(columnIndex);
        word.setForeground(Word.FOREGROUND_WRONG);
        word.setBackground(Word.BACKGROUND_DEFAULT);
        setChanged();
        notifyObservers(WORD_UPDATE_EVENT);
    }

    /**
     * Sets background to a white color and the foreground to a green
     * color of the word at the current row and column of this object.
     */
    public void markCurrentWordAsCorrect() {
        Word word = rows.get(rowIndex).get(columnIndex);
        word.setForeground(Word.FOREGROUND_CORRECT);
        word.setBackground(Word.BACKGROUND_DEFAULT);
        setChanged();
        notifyObservers(WORD_UPDATE_EVENT);
    }

    /**
     * Increases the columnIndex by 1. If the columnIndex becomes equal
     * to the current column size then it columnIndex will become zero
     * and rowIndex will increase by 1. Returns true if it successfully
     * went to the next word, false otherwise.
     *
     * @return true if it successfully went to the next word, false otherwise
     */
    public boolean goToNextWord() {
        columnIndex++;
        if (columnIndex == rows.get(rowIndex).size()) {
            rowIndex++;
            columnIndex = 0;
        }
        return rowIndex < rows.size() && columnIndex < rows.get(rowIndex).size();
    }

    /**
     * Returns true if the current rowIndex is greater than zero and
     * the columnIndex is equal to zero, false otherwise.
     *
     * @return true if the current rowIndex is greater than zero and
     * the columnIndex is equal to zero, false otherwise
     */
    public boolean needsToMoveRowsUp() {
        return rowIndex > 0 && columnIndex == 0;
    }

    /**
     * Notifies the observers that the rows needs to be moved upwards
     * on the text box.
     */
    public void moveRowsUp() {
        setChanged();
        notifyObservers(MOVE_ROWS_UPWARDS_EVENT);
    }

    /**
     * Clears the current words.
     */
    public void clear() {
        rowIndex = columnIndex = 0;
        words.clear();
        rows.clear();
        setChanged();
        notifyObservers(CLEAR_EVENT);
    }
}
