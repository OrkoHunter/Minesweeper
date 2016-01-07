package minesweeper;

import javax.swing.JOptionPane;

public class Minesweeper {

    public void start(Minesweeper minesweeper) {
        Input = new input(minesweeper);
        Input.main(Input);
    }
    
    public void proceed(int size) {
        int toughness = 1;
        Object[] options = {"Easy", "Moderate", "Hard"};
        toughness = JOptionPane.showOptionDialog(null,
                "What's your difficulty level ?", "Difficulty",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
        if(toughness == -1)
            System.exit(0);
        newGame = new game(size, toughness);
        newGame.main(newGame, size);

    }
    
    public static void main(String[] args) {
        minesweeper = new Minesweeper();
        minesweeper.start(minesweeper);
    }
    
    private static Minesweeper minesweeper;
    private static game newGame;
    private static input Input;
}

