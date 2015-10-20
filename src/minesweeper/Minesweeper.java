package minesweeper;

public class Minesweeper {

    public static void main(String[] args) {
        input.main();
    }
    
    public static void running(int size) {
        System.out.println("Good Going man! You're number " + size);
        game.main(size);
    }
}
