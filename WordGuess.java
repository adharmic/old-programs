import java.util.Scanner;
public class WordGuess {
    public static void main(String[] args) {
        
        System.out.println("\n");
        HiddenWord puzzle = new HiddenWord();
        
        System.out.println("The word you need to guess has " + puzzle.getAnswer().length() + " letters. \nA plus sign (\"+\") means that the letter is correct, but in the wrong place. \nType 'H' for a hint!");
        Scanner input = new Scanner(System.in);
        System.out.print("First guess: ");
        String guess = input.nextLine();
        
        while(!(isLonger(guess,puzzle.getAnswer()))) {
            if(guess.toUpperCase().equals("H")) {
                System.out.println("Here are the possible words, scrambled:");
                HiddenWord.scrambleAll();
            } else if(!guess.matches("^[a-zA-Z]*$")) {
                System.out.println("Please limit your entry to only letters.");
            } else {
                System.out.println("Too short.");
            }
            System.out.print("Next guess: ");
            guess = input.nextLine();
        }
        
        guess = guess.toUpperCase();
        while(!(guess.equals(puzzle.getAnswer()))) {
            System.out.println(puzzle.getHint(guess));
            System.out.print("Next guess: ");
            guess = input.nextLine();
            while(!(isLonger(guess,puzzle.getAnswer()))) {
                System.out.println("Too short.");
                System.out.print("Next guess: ");
                guess = input.nextLine();
            }
            guess = guess.toUpperCase();
        }
        System.out.println("You got it! The word was " + puzzle.getAnswer() + ".");
    }
    
    public static boolean isLonger(String a, String b) {
        if(a.length() >= b.length()) {
            return true;
        }
        return false;
    }
}



class Scrambler {
    public static String scrambleWord(String word) {
        word = word.toUpperCase();
        String toScramble = "";
        for(int x = 0; x < word.length(); x++) {
            if((x != word.length()-1)) {
                if(word.substring(x+1,x+2).equals("A") == false) {
                    toScramble += word.substring(x+1,x+2);
                    toScramble += word.substring(x,x+1);
                    x++;
                }
                else {
                    toScramble += word.substring(x,x+1);
                }
            }
            else {
                toScramble += word.substring(x,x+1);
            }
        }
        return toScramble;
    }
}


class HiddenWord {
    private String toGuess;
    private static String[] possibleAnswers = {"HELLO","HEART","TEETH","GHOST","JOKER","POOL","CHICKEN","ABRACADABRA", "EGGS", "PARMESAN", "NIAGARA", "AARDVARK"};
    
    public static void scrambleAll() {
        for(String answer: possibleAnswers)
            System.out.println(Scrambler.scrambleWord(answer));
    }
    
    public HiddenWord() {
        int length = possibleAnswers.length - 1;
        int rand = (int)(Math.random()*(length));
        toGuess = possibleAnswers[rand];
    }
    
    public HiddenWord(String word) {
        word = word.toUpperCase();
        toGuess = word;
    }
    
    public String getAnswer() {
        return toGuess;
    }
    
    public String getHint(String word) {
        word = word.toUpperCase();
        String hint = "";
        for(int x = 0; x < getAnswer().length(); x++) {
            if(getAnswer().substring(x,x+1).equals(word.substring(x,x+1))) {
                hint += getAnswer().substring(x,x+1);
            }
            else if(getAnswer().contains(word.substring(x,x+1))) {
                hint += "+";
            }
            else {
                hint += "*";
            }
        }
        return hint;
    }
}




