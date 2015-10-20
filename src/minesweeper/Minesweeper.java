package minesweeper;

import javax.swing.JOptionPane;

public class Minesweeper {

    public void start(Minesweeper minesweeper) {
        int size;
        size = Integer.parseInt(JOptionPane.showInputDialog("Enter the number : "));
        running(size);
    }
    
    public static void main(String[] args) {
        minesweeper = new Minesweeper();
        minesweeper.start(minesweeper);
    }
    
    public void running(int size) {
        System.out.println("Good Going man! You're number " + size);
        newGame = new game(size);
        newGame.main(newGame, size);
    }
    
    private static Minesweeper minesweeper;
    private static game newGame;
}
