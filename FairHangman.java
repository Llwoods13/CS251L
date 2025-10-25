import java.util.ArrayList;
import java.util.Collection;

public class FairHangman extends AbstractHangman{

    ArrayList<Character> lettersGuessed;
    private String secretWord;
    private int wrongGuesses;

    public FairHangman(String path) {
        readDictionary(path);
    }

    protected String getRandomWord() {
        return words.get((int)(Math.random() * words.size()));
    }

    /**
     * @param guesses Number of guesses for game.
     */

    public void initGame(int guesses) {
        secretWord = getRandomWord();
        lettersGuessed = new ArrayList<>();
        wrongGuesses = 0;
        getPuzzle();
    }

    /**
     * @return
     */
    @Override
    public int getGuessesRemaining() {
        return 6 - wrongGuesses;
    }

    /**
     * @return
     */
    @Override
    public Collection<Character> getGuessedLetters() {
        return lettersGuessed;
    }

    /**
     * @return
     */
    @Override
    public String getPuzzle() {
        StringBuilder sb = new StringBuilder();
        for (Character letter : secretWord.toCharArray()){
            if (!getGuessedLetters().contains(letter)) {
                sb.append(BLANK);
            } else {
                sb.append(letter);
            }
        }
        return sb.toString();
    }

    /**
     * @return
     */
    @Override
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * @return
     */
    @Override
    public boolean isComplete() {
        if (getPuzzle().equals(secretWord)) {
            return true;
        } else if (getPuzzle().contains("-")){
            return false;
        }
        return false;
    }

    /**
     * @return
     */
    @Override
    public boolean isGameOver() {
        if (getGuessesRemaining() == 0) {
            return true;
        } else if (isComplete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param letter The guessed letter.
     * @return
     */
    @Override
    public boolean updateWithGuess(char letter) {
        lettersGuessed.add(letter);
        if (secretWord.indexOf(letter) != -1){
            return true;
        } else {
            wrongGuesses++;
            return false;
        }
    }
}