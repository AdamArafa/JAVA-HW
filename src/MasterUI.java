/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
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
    
    private JTextField[] jTfTriel;
    private JButton[] jBnFlagB;
    private JTextField[] jTFieldB;
    private JButton[] jBnFlagW;
    private JTextField[] jTFieldW;
    
    private final String colors[] = {"R", "G", "B", "Y", "O"};
    private String userMaster = "";
    
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
        
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        jTfTriel = new JTextField[10];
        jBnFlagB = new JButton[10];
        jTFieldB = new JTextField[10];
        jBnFlagW = new JButton[10];
        jTFieldW = new JTextField[10];
        for (int i = 0; i < 10; ++i) {
            JLabel jLabelTriel = new JLabel(" triel" + i);
            jLabelTriel.setHorizontalAlignment(JLabel.CENTER);
            jTfTriel[i] = new JTextField();
            jTfTriel[i].setEditable(false);
            jBnFlagB[i] = new JButton("B");
            jTFieldB[i] = new JTextField();
            jBnFlagW[i] = new JButton("W");
            jTFieldW[i] = new JTextField();
            
            jPanel.add(jLabelTriel, new GridBagConstraints(0, i, 1, 1, 0, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER));
            jPanel.add(jTfTriel[i], new GridBagConstraints(1, i, 3, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER));
            jPanel.add(jBnFlagB[i], new GridBagConstraints(4, i, 1, 1, 0, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER));
            jPanel.add(jTFieldB[i], new GridBagConstraints(5, i, 2, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER));
            jPanel.add(jBnFlagW[i], new GridBagConstraints(7, i, 1, 1, 0, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER));
            jPanel.add(jTFieldW[i], new GridBagConstraints(8, i, 2, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER));
            
        }
        getContentPane().add(jPanel, new GridBagConstraints(0, 3, 10, 5, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER));
        getContentPane().add(jTextField2, new GridBagConstraints(0, 13, 5, 1, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.SOUTH));        
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        boolean isColor = java.util.Arrays.asList(colors).contains(cmd);
        if (isColor) {
            userMaster += cmd;
            jTextField1.setText(userMaster);
            // System.out.println(cmd);
        }
        if (cmd.equals("backspace")) {
            userMaster = userMaster.substring(0, userMaster.length()-1);
            jTextField1.setText(userMaster);
        }
        if (cmd.equals("submit")) {
            
        }
        
    }
    
    
}
