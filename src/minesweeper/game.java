package minesweeper;

//import javax.swing.border.
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;

public class game extends JFrame {
    
    public game(int size) {
        noOfMines = size;
        this.setSize(size*30, size*30 + 50);
        this.setTitle("Minesweeper");
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        

    }

    private void setMines(int size) {
        Random rand = new Random();
        
        mineLand = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mineLand[i][j] = 0;
            }
        }
        
        int count = 0;
        int xPoint;
        int yPoint;
        while(count<=noOfMines) {
            xPoint = rand.nextInt(size);
            yPoint = rand.nextInt(size);
            if (mineLand[xPoint][yPoint]!=-1) {
                mineLand[xPoint][yPoint]=-1;  // -1 represents bomb
                count++;
            }
        }
        
        // Fill boxes adjacent to mines
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (mineLand[i][j]==-1) {
                    for (int k = -1; k <= 1 ; k++) {
                        for (int l = -1; l <= 1; l++) {
                            try {
                                if (mineLand[i+k][j+l]!=-1) {
                                    mineLand[i+k][j+l] += 1;
                                }
                            }
                            catch (Exception e) {
                                // Do nothing
                            }
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                    System.out.print(mineLand[i][j] + "  ");
            }
            System.out.println("\n");
        }

    }
    
    public void main(game frame, int size) {

        GameEngine gameEngine = new GameEngine(frame);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        panel1 = new JPanel();
        panel2 = new JPanel();

        BoxLayout g1 = new BoxLayout(panel1, BoxLayout.X_AXIS);
        //FlowLayout g1 = new FlowLayout();
        panel1.setLayout(g1);
        JLabel jLabel1 = new JLabel(" Flags = ");
        flagsLabel = new JLabel("  ");
        smileButton = new JButton();
        smileButton.setPreferredSize(new Dimension(30, 30));
        smileButton.setMaximumSize(new Dimension(30, 30));
        //smileButton.setMinimumSize(new Dimension(18, 18));
        smileButton.setBorderPainted(true);
        smileButton.setName("smileButton");
        smileButton.addActionListener(gameEngine);
        JLabel jLabel2 = new JLabel(" Time :");
        timeLabel = new JLabel("....");
        // jLabel1.getWidth() == 39
        // flagsLabel.getWidth() == 14
        // smileButton.getWidth() == 30
        // jLabel2.getWidth()) == 37
        
        panel1.add(jLabel1);
        panel1.add(flagsLabel);
        panel1.add(Box.createRigidArea(new Dimension((size-1)*15 - 53,50)));
        panel1.add(smileButton, BorderLayout.PAGE_START);
        panel1.add(Box.createRigidArea(new Dimension((size-1)*15 - 65,50)));
        panel1.add(jLabel2);
        panel1.add(timeLabel);
        
        
        GridLayout g2 = new GridLayout(size, size);
        panel2.setLayout(g2);

        buttons = new JToggleButton[size][size];

        for (int i=0; i<size; i++) {
            for (int j=0; j<size ; j++ ) {
                buttons[i][j] = new JToggleButton();
                buttons[i][j].setPreferredSize(new Dimension(12, 12));
                buttons[i][j].setBorder(new LineBorder(Color.BLACK));
                buttons[i][j].setBorderPainted(true);
                buttons[i][j].setToolTipText("It's " + Integer.toString(i) + ", " + Integer.toString(j));
                buttons[i][j].setName(i + " " + j);
                buttons[i][j].addActionListener(gameEngine);
                panel2.add(buttons[i][j]);
            }
        }

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        frame.setContentPane(mainPanel);
        this.setVisible(true);
        
        // Algorithms
        setMines(size);

        revealed = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                revealed[i][j] = false;
            }
        }

        // setters and getters
    }
    
    public void changeSmile() {
        
    }
    
    public void buttonClicked(String coordinate) {
        int x, y;
        String[] xy = coordinate.split(" ", 2);
        x = Integer.parseInt(xy[0]);
        y = Integer.parseInt(xy[1]);
        if(!revealed[x][y]) {
            revealed[x][y] = true;
            
            switch (mineLand[x][y]) {
                case -1:
                    buttons[x][y].setText("X");
                    buttons[x][y].setBackground(Color.RED);
                    JOptionPane.showMessageDialog(rootPane, "Game Over !");
                    break;
                case 0:
                    buttons[x][y].setText("0");
                    buttons[x][y].setBackground(Color.BLUE);
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            try {
                                buttonClicked(x + i + " " + y + j);  // Recurse around
                            }
                            catch (Exception e) {
                                // Do nothing
                            }
                        }
                    }
                    break;
                default:
                    buttons[x][y].setText(Integer.toString(mineLand[x][y]));
                    buttons[x][y].setBackground(Color.BLUE);
                    break;
            }
        }
        
    }

    private JToggleButton[][] buttons;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel flagsLabel;
    private JButton smileButton;
    private JLabel timeLabel;

    private int noOfMines = 0;
    private int[][] mineLand;
    private boolean[][] revealed;
    
        
    }



class GameEngine implements ActionListener {
    game parent;
    
    GameEngine(game parent) {
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventSource = e.getSource();
        
        if (eventSource instanceof JButton) {
            parent.changeSmile();
        }
        else if (eventSource instanceof JToggleButton) {
            JToggleButton clickedButton = (JToggleButton) eventSource;
            parent.buttonClicked(clickedButton.getName());
        }
    }
}