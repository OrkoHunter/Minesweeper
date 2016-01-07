package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class input extends JFrame {

    public input(Minesweeper minesweeper) {
        this.iMinesweeper = minesweeper;
        this.setSize(400, 100);
        this.setTitle("Input");
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
    // Setter and Getter
    public void set(int n) {
        size = n;
        iMinesweeper.proceed(size);
    }

    public int get() {
        return size;
    }
    
    public void main(input Input) {
        inputEngine = new InputEngine(Input);

        size=0;

        panel = new JPanel();
        
        label = new JLabel("Enter grid size : ");
        panel.add(label);
        
        text = new JTextField(30);
        text.addActionListener(inputEngine);
        panel.add(text);
        
        Input.setContentPane(panel);
        this.setVisible(true);
    }
    
    final private Minesweeper iMinesweeper;  // A reference to the original game
    private InputEngine inputEngine;  // The ActionListener

    private int size;  // size given
    private JPanel panel;
    private JLabel label;
    private JTextField text;
}

class InputEngine implements ActionListener {
    input parent;
    
    InputEngine(input parent) {
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        Object eventSource = evt.getSource();
        JTextField text = (JTextField) eventSource;
        String input =  "0";
        int size = 0;
        
        while(true) {
            try {
                input = text.getText();
                size = Integer.parseInt(input);
                if (size<=6) {
                    JOptionPane.showMessageDialog(parent,
                            "Enter an integer greater than 6", "Invalid Input!",
                            JOptionPane.ERROR_MESSAGE);
                    text.setText("");
                    break;
                } else {
                    parent.setVisible(false);
                    parent.set(size);
                }
                break;
            }
            catch (NumberFormatException | HeadlessException e) {
                JOptionPane.showMessageDialog(parent,
                        "Enter a valid integer!", "Invalid Input", 
                        JOptionPane.ERROR_MESSAGE);
                text.setText("");
                break;
            }
        }
    }
}

