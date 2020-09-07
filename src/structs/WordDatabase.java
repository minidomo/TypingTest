package structs;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A database containing all the supported words for the typing test.
 */
public class WordDatabase {
    private static final WordDatabase WORD_DATABASE = new WordDatabase();

    private ArrayList<Word> easyWords, hardWords;

    /**
     * Prevent instantiation outside of this class. Loads in data for the database.
     */
    private WordDatabase() {
        try {
            Scanner sc = new Scanner(getClass().getResourceAsStream("/resources/words/easy.txt"));
            easyWords = new ArrayList<>();
            while (sc.hasNext()) {
                easyWords.add(new Word(sc.next()));
            }
            sc = new Scanner(getClass().getResourceAsStream("/resources/words/hard.txt"));
            hardWords = new ArrayList<>();
            while (sc.hasNext()) {
                hardWords.add(new Word(sc.next()));
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the structs.WordDatabase for this Java application.
     *
     * @return the structs.WordDatabase for this Java application
     */
    public static WordDatabase getInstance() {
        return WORD_DATABASE;
    }

    /**
     * Returns the total number of easy words in the database.
     *
     * @return the total number of easy words in the database
     */
    public int getEasyWordCount() {
        return easyWords.size();
    }

    /**
     * Returns the total number of hard words in the database.
     *
     * @return the total number of hard words in the database
     */
    public int getHardWordCount() {
        return hardWords.size();
    }

    /**
     * Returns a word from the easy word database at a given index.
     *
     * @param index the index
     * @return a word from the easy word database a given index
     */
    public Word getEasyWord(int index) {
        return new Word(easyWords.get(index).getWord());
    }

    /**
     * Returns a word from the hard word database at a given index.
     *
     * @param index the index
     * @return a word from the hard word database at a given index
     */
    public Word getHardWord(int index) {
        return new Word(hardWords.get(index).getWord());
    }
}
