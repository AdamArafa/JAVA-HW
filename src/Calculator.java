
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
    private Button[] btnKeys;
    
    private final String[] btnStrings = {"MC", "7", "8", "9", "/", "sqrt",
                                         "MR", "4", "5", "6", "*",    "%",
                                         "MS", "1", "2", "3", "-",  "1/x",
                                         "M+", "0", "±", ".", "+",    "="};
    private Double Result;
    
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
        btnKeys = new Button[btnStrings.length];
        for (int i = 0; i < btnStrings.length; ++i) {
            btnKeys[i] = new Button(btnStrings[i]);
            pBtns.add(btnKeys[i]);
            btnKeys[i].setActionCommand(btnStrings[i]);
            btnKeys[i].addActionListener(this);
        }
        add(pBtns, BorderLayout.CENTER);
        setSize(400, 175);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "MC":
                break;
            case "MR":
                break;
            case "MS":
                break;
            case "M+":
                break;
            case "+":
                break;
            case "-":
                break;
            case "*":
                break;
            case "/":
                break;
            case "sqrt":
                break;
            case "%":
                break;
            case "1/x":
                break;
            case "=":
                break;
            case "±":
                break;
            case ".":
                break;
            case "Off":
                System.exit(0);
                break;
            default:
                // numbers
        }
    }
    
}
