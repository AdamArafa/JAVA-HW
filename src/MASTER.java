
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Albert
 */
public class MASTER extends JFrame implements ActionListener {

    private int guessTimes;
    private UserPanel uPanel;
    private SubmitPanel sPanel;
    
    MASTER() {
        initComponents();
        this.setTitle("YOU PLAY");
        this.setLocationRelativeTo(null); // Center the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void initComponents() {
        setResizable(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(new UserPanel());
        getContentPane().add(new SubmitPanel());
        pack();        
    }
    
    class UserPanel extends JPanel {
            Trial[] trials;

            UserPanel() {
                this.setLayout(new GridLayout(0, 1));
                this.trials = new Trial[10];
                for (int i = 0; i < 10; i++) {
                    add(this.trials[i] = new Trial(i + 1));
                }
            }

            class Trial extends JPanel {
                JTextField jTxtfield;
                JLabel blackPin;
                JTextField bkField;
                JLabel whitePin;
                JTextField whField;

                Trial(int num) {
                    setLayout(new FlowLayout());
                    this.add(new JLabel("Trial" + String.format("%02d", num)));
                    this.add(jTxtfield = new JTextField(14));
                    this.add(blackPin = new JLabel("B"));
                    this.add(bkField = new JTextField(3));
                    this.add(whitePin = new JLabel("W"));
                    this.add(whField = new JTextField(3));
                    jTxtfield.setEditable(false);
                    bkField.setEditable(false);
                    whField.setEditable(false);
                }

                void setButtonEnabled(boolean enabled) {
                    blackPin.setEnabled(enabled);
                    whitePin.setEnabled(enabled);
                }
            }
        }
        
        class SubmitPanel extends JPanel implements ActionListener {
            JButton[] jBtnFlags;
            JButton SubmitButton;
            JButton BackspaceButton;
            JPanel buttonPanel;
            JPanel subPanel;
            private final String colors[] = {"R", "G", "B", "Y", "O"};
            
            SubmitPanel() {
                this.setLayout(new GridLayout(2, 0));
                buttonPanel = new JPanel(new GridLayout(0, 5));
                jBtnFlags = new JButton[5];
                for (int i = 0; i < 5; ++i) {
                    jBtnFlags[i] = new JButton(colors[i]);
                    jBtnFlags[i].setActionCommand(colors[i]);
                    jBtnFlags[i].addActionListener(this);
                    buttonPanel.add(jBtnFlags[i]);
                }
                subPanel = new JPanel(new GridLayout(0, 2));
                subPanel.add(SubmitButton = new JButton("Submit"));
                SubmitButton.setActionCommand("Submit");
                subPanel.add(BackspaceButton = new JButton("Backspace"));
                BackspaceButton.setActionCommand("Backspace");
                this.BackspaceButton.addActionListener(this);
                this.SubmitButton.addActionListener(this);
                this.add(buttonPanel);
                this.add(subPanel);
            }
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                boolean isColor = java.util.Arrays.asList(colors).contains(cmd);
                JTextField txtField = uPanel.trials[guessTimes].jTxtfield;
                String uMaster = txtField.getText();
                
                if (isColor) {
                    if (uMaster.length() < 5) {
                        uMaster += cmd;
                        txtField.setText(uMaster);
                        // System.out.println(cmd);
                    }
                }
                if (cmd.equals("backspace")) {
                    uMaster = uMaster.substring(0, uMaster.length()-1);
                    txtField.setText(uMaster);
                }
                if (cmd.equals("submit")) {
                    if (uMaster.length() < 5) {
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Input less than 5 characters",
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int result = JOptionPane.showConfirmDialog(new JFrame(),
            "使用者玩遊戲請按(N)，電腦玩遊戲請按(Y)",
            "是去要玩油戲呢？",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        if (result == JOptionPane.YES_OPTION) {
            MasterUI main = new MasterUI();
        }
        else if (result == JOptionPane.NO_OPTION) {
            MASTER main = new MASTER();
        }
        else {
            System.exit(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
