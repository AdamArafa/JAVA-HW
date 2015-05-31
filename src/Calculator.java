
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

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
    private Double Memory = 0.0;
    private Double lastNum = 0.0;
    private Double currNum = 0.0;
    private int lastOpt = 0;
    private boolean erase = true;
    private final static int OpNone = 0, OpPlus = 1, OpMinus = 2, OpMultiply = 3, OpDivide = 4;
    private DecimalFormat df = new DecimalFormat("##########.##########");
    
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
        setSize(400, 170);
    }

    private Double getTextDouble() {
        return Double.parseDouble(tf.getText());
    }
    
    private void clearMethod() {
        lastNum = 0.0;
        currNum = 0.0;
        lastOpt = OpNone;
        erase = true;
    }
    
    private String equlMethod(int op) {
        currNum = getTextDouble();
        double result = currNum;
        String text = "";
        try {
            switch (op) {
                case OpPlus:
                    result = lastNum + currNum;
                    break;
                case OpMinus:
                    result = lastNum - currNum;
                    break;
                case OpMultiply:
                    result = lastNum * currNum;
                    break;
                case OpDivide:
                    result = lastNum / currNum;
                    break;
            }
            lastNum = currNum;
            text = df.format(result);
            erase = true;
            lastOpt = OpNone;
        } catch (Exception ex) {
            tf.setText("ERROR: " + ex.getMessage());
            clearMethod();
        }
        return text;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String text = tf.getText();
        Double result = getTextDouble();
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
                text = equlMethod(OpPlus);
                lastOpt = OpPlus;
                break;
            case "-":
                text = equlMethod(OpMinus);
                lastOpt = OpMinus;
                break;
            case "*":
                text = equlMethod(OpMultiply);
                lastOpt = OpMultiply;
                break;
            case "/":
                text = equlMethod(OpDivide);
                lastOpt = OpDivide;
                break;
            case "sqrt":
                result = Math.sqrt(result);
                text = df.format(result);
                break;
            case "%":
                result %= result;
                text = df.format(result);
                break;
            case "1/x":
                result = 1 / result;
                text = df.format(result);
                break;
            case "=":
                text = equlMethod(lastOpt);
                break;
            case "±":
                result = -result;
                text = df.format(result);
                break;
            case ".":
                if (erase) {
                    text = "0";
                    erase = false;
                }
                text = text.contains(".") ? df.format(result) : df.format(result) + ".";
                break;
            case "Off":
                System.exit(0);
                break;
            case "CE":
                clearMethod();
                break;
            case "C":
                text = "0";
                break;
            case "Back":
                text = text.substring(0, text.length()-1);
                if (text.length() == 0) text = "0";
                break;
            default:
                // numbers
                if (erase) {
                    text = "0";
                    erase = false;
                }
                if (text.length() == 1 && text.charAt(0) == '0')
                    text = cmd;
                else
                    text += cmd;
        }
        tf.setText(text);
        
    }
    
}
