/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.*;
/**
 *
 * @author Albert
 */
public class MasterUI extends JFrame implements ActionListener {

    private class GridBagConstraints extends java.awt.GridBagConstraints {

        public GridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty, int fill, int anchor) {
            this.gridx = gridx;
            this.gridy = gridy;
            this.gridwidth = gridwidth;
            this.gridheight = gridheight;
            this.weightx = weightx;
            this.weighty = weighty;
            this.fill = fill;
            this.anchor = anchor;
        }

    }
    
    public MasterUI() {
        initComponents();
        this.setTitle("MASTER");
        // this.setSize(250, 400);
        this.setLocationRelativeTo(null); // Center the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private JLabel jLabel1;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JButton jButton6;
    private JButton jButton7;
    private JButton[] jBtnFlags;
    
    private final String colors[] = {"R", "G", "B", "Y", "O"};
    private String uMaster = "";
    private UserPanel myPanel;
    private ArrayList<String> Colors;
    
    class UserPanel extends JPanel {
        class Trial extends JPanel {
            JTextField jTxtfield;
            JButton blackPin;
            JTextField bkField;
            JButton whitePin;
            JTextField whField;
            private int id;
            Trial(int num) {
                setLayout(new FlowLayout());
                add(new JLabel("Trial" + String.format("%d", num)));
                add(jTxtfield = new JTextField(5));
                add(blackPin  = new JButton("B"));
                blackPin.setActionCommand("B"+num);
                blackPin.addActionListener(new myPanelListener());
                add(bkField  = new JTextField("-1"));
                add(whitePin  = new JButton("W"));
                whitePin.setActionCommand("W"+num);
                whitePin.addActionListener(new myPanelListener());
                add(whField  = new JTextField("-1"));
                jTxtfield.setEditable(false);
                bkField.setEditable(false);
                whField.setEditable(false);
                setButtonDisable(false);
                this.id = num;
            }
            void setButtonDisable(boolean enabled) {
                blackPin.setEnabled(enabled);
                whitePin.setEnabled(enabled);
            }
            
        }
        public Trial[] trials;
        UserPanel() {
            this.setLayout(new GridLayout(0, 1));
            this.trials = new Trial[10];
            for (int i = 0; i < 10; i++) {
                add(this.trials[i] = new Trial(i));
            }
        }
        public void enableBtns() {
            for (int i = 0; i < 10; i++) {
                trials[i].setButtonDisable(true);
            }
        }
        private void calc(int index, int b, int w) {
            int lastOne = index - 1;
            if (b == 5) {
                jTextField2.setText("win");
            } else
            if (lastOne > -1) {
                int lb = parseInt(this.trials[lastOne].bkField.getText());
                int lw = parseInt(this.trials[lastOne].whField.getText());
                if (lb > -1 && lw > -1) {
                    this.trials[lastOne].setButtonDisable(false);
                    Colors = Player.removePossibale(Colors, new Player(this.trials[lastOne].jTxtfield.getText()), lb, lw);
                    if (Colors.isEmpty()) {
                        jTextField2.setText("lose");
                    }
                    else {
                        this.trials[index].jTxtfield.setText(Colors.get(0));
                    }
                }
            }
            String tmp = this.trials[index].jTxtfield.getText();
            System.out.println(tmp);
            Colors = Player.removePossibaleD(Colors, new Player(tmp), b, w);
            if (index < 9) {
                if (Colors.isEmpty()) {
                    jTextField2.setText("lose");
                }
                else {
                    this.trials[index+1].jTxtfield.setText(Colors.get(0));
                } 
            }
        }
        class myPanelListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                char whichColor = cmd.charAt(0);
                System.out.println(whichColor);
                int index = Character.getNumericValue(cmd.charAt(1));
                System.out.println(index);
                int b = parseInt(myPanel.trials[index].bkField.getText());
                int w = parseInt(myPanel.trials[index].whField.getText());
                calc(index, b, w);
                if (whichColor == 'B') {
                    ++b;
                    if (b <= 5 && b + w <= 5) {
                        myPanel.trials[index].bkField.setText("" + b);
                    }
                }
                else {
                    ++w;
                    if (b <= 5 && b + w <= 5) {
                        myPanel.trials[index].whField.setText("" + w);
                    }
                }
            }
        }
        
    }
    
    private void initComponents() {
        
        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        jTextField1.setEditable(false);
        jTextField2 = new JTextField();
        jTextField2.setEditable(false);
        jButton6 = new JButton();
        jButton7 = new JButton();

        jLabel1.setText("Puzzle");
        jLabel1.setHorizontalAlignment(JLabel.CENTER);
        jButton6.setText("backspace");
        jButton6.setActionCommand("backspace");
        jButton6.addActionListener(this);
        jButton7.setText("submit");
        jButton7.setActionCommand("submit");
        jButton7.addActionListener(this);
        
        getContentPane().setLayout(new GridBagLayout());
        getContentPane().add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.BOTH, GridBagConstraints.NORTH));
        getContentPane().add(jTextField1, new GridBagConstraints(1, 0, 4, 1, 1, 0, GridBagConstraints.BOTH, GridBagConstraints.NORTH));
        
        jBtnFlags = new JButton[5];
        for (int i = 0; i < 5; ++i) {
            jBtnFlags[i] = new JButton(colors[i]);
            jBtnFlags[i].setActionCommand(colors[i]);
            jBtnFlags[i].addActionListener(this);
            getContentPane().add(jBtnFlags[i], new GridBagConstraints(i, 1, 1, 1, 1, 0, GridBagConstraints.BOTH, GridBagConstraints.NORTH));
        }
        getContentPane().add(jButton6, new GridBagConstraints(0, 2, 3, 1, 1, 0, GridBagConstraints.BOTH, GridBagConstraints.NORTH));
        getContentPane().add(jButton7, new GridBagConstraints(3, 2, 2, 1, 1, 0, GridBagConstraints.BOTH, GridBagConstraints.NORTH));
        getContentPane().add(myPanel = new UserPanel(), new GridBagConstraints(0, 3, 10, 5, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER));
        getContentPane().add(jTextField2, new GridBagConstraints(0, 13, 5, 1, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.SOUTH));        
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        boolean isColor = java.util.Arrays.asList(colors).contains(cmd);
        if (isColor) {
            if (uMaster.length() < 5) {
                uMaster += cmd;
                jTextField1.setText(uMaster);
                // System.out.println(cmd);
            }
        }
        if (cmd.equals("backspace")) {
            uMaster = uMaster.substring(0, uMaster.length()-1);
            jTextField1.setText(uMaster);
        }
        if (cmd.equals("submit")) {
             if (uMaster.length() < 5) {
                jTextField2.setText("Input Error!");
             }
            else {
                // for (int i = 0; i < 5; ++i) {
                    // jBtnFlags[i].setEnabled(false);
                // }
                myPanel.enableBtns();
                jButton6.setEnabled(false);
                jButton7.setEnabled(false);
                jTextField2.setText("");
                this.Colors = Player.getColors();
                //jTfTriel[0].setText(Colors.get(0));
                myPanel.trials[0].jTxtfield.setText(Colors.get(0));
            }
        }
        
    }
    
}
