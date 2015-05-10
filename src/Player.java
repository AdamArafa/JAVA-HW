
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Albert
 */
public class Player {
    private static char[][] colors = new char[3125][5];
    private static final char[] color = {'R', 'G', 'B', 'Y', 'O'};
    private static int[] next = new int[3125];
    private static int index = 0;
    private char[] playerColor = new char[5];
    private boolean[] check = new boolean[5];
    static {
        for (int i = 0; i < 5; ++i)
            for (int j = 0; j < 5; ++j)
                for (int k = 0; k < 5; ++k)
                    for (int m = 0; m < 5; ++m)
                        for (int n = 0; n < 5; ++n) {
                            colors[index][0] = color[i];
                            colors[index][1] = color[j];
                            colors[index][2] = color[k];
                            colors[index][3] = color[m];
                            colors[index][4] = color[n];
                            next[index++] = 0;
                        }
    }

    public Player() {
        setComputerColor();
    }
    
    public Player(String color) {
        setPlayerColor(color);
    }
    
    public static PinCount compare(Player A, Player Q) {
        int blackPinCount = 0;
        int whitePinCount = 0;
        Arrays.fill(A.check, true);
        Arrays.fill(Q.check, true);
        for(int i = 0; i < Q.playerColor.length; i++) {
            if(A.playerColor[i] == Q.playerColor[i]) {
                A.check[i] = Q.check[i] = false;
                blackPinCount++;
            }
        }
        for (int i = 0;i < Q.playerColor.length;i++) {
            for (int j = 0; j < Q.playerColor.length;j++) {
                if (Q.check[i] && A.check[j]) {
                    if (A.playerColor[j] == Q.playerColor[i]) {
                        A.check[j] = Q.check[i] =false;
                        ++whitePinCount;
                    }
                }
            }
        }
        return new PinCount(blackPinCount, whitePinCount);
    }

    public void setPlayerColor(String input) {
        for (int i = 0; i < 5; ++i) {
            playerColor[i] = input.charAt(i);
        }
    }

    public void setComputerColor() {
        Random ran = new Random();
        int temp;
        do {
            temp = ran.nextInt(3125);
            playerColor = colors[temp];
        } while(next[temp] != 0);
        next[temp] = 1;
    }

    public boolean isColorExiest(Character A) {
        for (int i = 0; i < 5; ++i){
            if (playerColor[i] == A) return true;
        }
        return false;
    }

    public String getPlayerColor() {
        return Arrays.toString(playerColor);
    }
    
    static class PinCount {
        int BKCount;
        int WHCount;

        PinCount(int BKCount, int WHCount) {
            this.BKCount = BKCount;
            this.WHCount = WHCount;
        }
    }
    
    static ArrayList<String> getColors() {
        ArrayList<String> Colors = new ArrayList<>();
        for (int i = 0; i < index; ++i) {
            Colors.add(new String(colors[i]));
        }
        return Colors;
    }
    
    static ArrayList<String> removePossibaleD(ArrayList<String> Colors, Player A, int B, int W) {
        ArrayList<String> Possibale = new ArrayList<>();
        for (String tc : Colors) {
            PinCount tmp = compare(A, new Player(tc));
            if (tmp.BKCount >= B && tmp.WHCount >= W) {
                Possibale.add(tc);
            }
            
        }
        return Possibale;
    }
    
    static ArrayList<String> removePossibale(ArrayList<String> Colors, Player A, int B, int W) {
        ArrayList<String> Possibale = new ArrayList<>();
        for (String tc : Colors) {
            PinCount tmp = compare(A, new Player(tc));
            if (tmp.BKCount == B && tmp.WHCount == W) {
                Possibale.add(tc);
            }
            
        }
        return Possibale;
    }
}
