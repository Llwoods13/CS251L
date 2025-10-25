import java.util.*;

public class EvilHangman extends AbstractHangman{
    ArrayList<Character> lettersGuessed;
    private String secretWord;
    private int wrongGuesses;
    private Map<String, List<String>> evilPartitions = new HashMap<>();

    public EvilHangman(String path) {
        readDictionary(path);
    }


    /**
     * @param guesses Number of guesses for game.
     */
    @Override
    public void initGame(int guesses) {
        secretWord = getRandomWord();
        lettersGuessed = new ArrayList<>();
        wrongGuesses = 0;
        getPuzzle();
    }

    public String getRandomWord(){
        return words.get((int)(Math.random() * words.size()));
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
        return "";
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

        /*Using puzzle to help with partitioning. Partially filled in puzzles are the
    keys. Use a map like this Map<String, List(String)> Could be a set or collection
    could do a list<list
     */

    /*
    With using the key as your string you could call puzzle which then uses the
    list to find the list of strings that are similar to how the puzzle will look.
    Like this Map<getPuzzle(), List<Strings>>
     */

    /**
//     * This method should be called in my
//     * @return
//     */
//    public ArrayList<String> getPartitions() {
//        ArrayList<String> sameLength = new ArrayList<>();
//        for (int i = 0; i < words.size(); i++){
//            if (words.get(i).length() == secretWord.length()){
//                sameLength.add(String.valueOf(i));
//            }
//        }
//        return sameLength;
//    }

//    public HashMap<String , List<String>> getGuess(String input, List<String> dictionary){
//        List<String> Options = new ArrayList<>();
//        for(int i = 0; i < input.length(); i++){
//             if(input.indexOf(i) == ){
//             Options = getWordList(input.indexOf()
//         }
//
//     }
//
////        return HashMap<String, List<String>> = new HashMap<Input, Options(i)>()
//
//        return null;
//    }

    private List<String> getWordList(String input){

        return null;
    }

}
