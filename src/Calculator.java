
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Albert
 */
public class Calculator extends Applet implements ActionListener {

    private TextField tf;
    private Button btnOff, btnBack, btnCE, btnC;
    private Button[] btnMems;
    private Button[] btnNums;
    private Button[] btnOpts;
    
    private final String[] btnStrings = {"MC", "7", "8", "9", "/", "sqrt",
                                         "MR", "4", "5", "6", "*",    "%",
                                         "MS", "1", "2", "3", "-",  "1/x",
                                         "M+", "0", "±", ".", "+",    "="};
    
    @Override
    public void init() {
        setLayout(new BorderLayout());
        Panel pNorth = new Panel(new GridLayout(2, 0));
        tf = new TextField("0");
        tf.setEditable(false);
        pNorth.add(tf);
        
        Panel pFunc = new Panel(new GridLayout(0, 6));
        btnOff = new Button("Off");
        pFunc.add(btnOff);
        pFunc.add(new Label());
        pFunc.add(new Label());
        btnOff.addActionListener(this);
        btnBack = new Button("Back");
        pFunc.add(btnBack);
        btnBack.addActionListener(this);
        btnCE = new Button("CE");
        pFunc.add(btnCE);
        btnCE.addActionListener(this);
        btnC = new Button("C");
        pFunc.add(btnC);
        btnC.addActionListener(this);
        pNorth.add(pFunc);
        add(pNorth, BorderLayout.NORTH);
        Panel pBtns = new Panel(new GridLayout(4, 6));
        btnMems = new Button[btnStrings.length];
        for (int i = 0; i < btnStrings.length; ++i) {
            btnMems[i] = new Button(btnStrings[i]);            
            pBtns.add(btnMems[i]);
            btnMems[i].setActionCommand(btnStrings[i]);
            btnMems[i].addActionListener(this);
        }
        add(pBtns, BorderLayout.CENTER);
        this.setSize(400, 175);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
