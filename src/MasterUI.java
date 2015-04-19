/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Albert
 */
public class MasterUI extends JFrame {

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
        this.setSize(250, 400);
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
    
    private void initComponents() {
        
        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jButton6 = new JButton();
        jButton7 = new JButton();

        jLabel1.setText(" Puzzle");
        jButton6.setText("backspace");
        jButton7.setText("submit");
        
        getContentPane().setLayout(new GridBagLayout());
        getContentPane().add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.BOTH, GridBagConstraints.CENTER));
        getContentPane().add(jTextField1, new GridBagConstraints(1, 0, 5, 1, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.WEST));
        
        jBtnFlags = new JButton[5];
        String colors[] = {"R", "G", "B", "Y", "O"};
        for (int i = 0; i < 5; ++i) {
            jBtnFlags[i] = new JButton(colors[i]);
            getContentPane().add(jBtnFlags[i], new GridBagConstraints(i, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.NORTH));
        }
        getContentPane().add(jButton6, new GridBagConstraints(0, 2, 3, 1, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.NORTH));
        getContentPane().add(jButton7, new GridBagConstraints(3, 2, 3, 1, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.NORTH));
        getContentPane().add(jTextField2, new GridBagConstraints(0, 3, 7, 1, 0, 0, GridBagConstraints.BOTH, GridBagConstraints.SOUTH));
        
        
        
    }
}
