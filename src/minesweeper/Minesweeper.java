package minesweeper;

import javax.swing.JOptionPane;

public class Minesweeper {

    public void start(Minesweeper minesweeper) {
        Input = new input(minesweeper);
        Input.main(Input);
    }
    
    public void tough(int size) {
        int toughness = 1;
        Object[] options = {"Easy", "Moderate", "Hard"};
        toughness = JOptionPane.showOptionDialog(newGame,
                "What's your difficulty level ?", "Difficulty",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
            running(size, toughness);
    }
    
    public static void main(String[] args) {
        minesweeper = new Minesweeper();
        minesweeper.start(minesweeper);
    }
    
    public void running(int size, int toughness) {
        newGame = new game(size, toughness);
        newGame.main(newGame, size);
    }
    
    private static Minesweeper minesweeper;
    private static game newGame;
    private static input Input;
}
