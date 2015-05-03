
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
public class PPlayer {
    private static char[][] colors = new char[3125][5];
    private static final char[] avail = {'R', 'G', 'B', 'Y', 'O'};
    private static int[] next = new int[3125];
    private static int index = 0;
    private char[] playerColor = new char[5];
    private boolean[] check = new boolean[5];
    static{
        int I,J,K,M,N;
        for (I = 0; I < 5; I++)
            for (J = 0; J < 5; J++)
                for (K = 0; K < 5; K++)
                    for (M = 0; M < 5; M++)
                        for (N = 0; N < 5; N++){
                            colors[index][0] = avail[I];
                            colors[index][1] = avail[J];
                            colors[index][2] = avail[K];
                            colors[index][3] = avail[M];
                            colors[index][4] = avail[N];
                            next[index] = ++index;
                        }
        Arrays.fill(next, 0);
    }

    public PPlayer() {
        setComputerColor();
    }
    
    public static PinCount compare(PPlayer A, PPlayer Q){
        int blackPinCount = 0;
        int whitePinCount = 0;
        Arrays.fill(A.check, true);
        Arrays.fill(Q.check, true);
        for(int i = 0; i < Q.playerColor.length; i++){
            if(A.playerColor[i] == Q.playerColor[i]) {
                A.check[i] = Q.check[i] = false;
                blackPinCount++;
            }
        }
        for(int i = 0;i < Q.playerColor.length;i++){
            for (int j = 0; j < Q.playerColor.length;j++){
                if (Q.check[i] && A.check[j]){
                    if (A.playerColor[j] == Q.playerColor[i]){
                        A.check[j] = Q.check[i] =false;
                        whitePinCount++;
                    }
                }
            }
        }
        return new PinCount(blackPinCount, whitePinCount);
    }

    public void setPlayerColor(String input){
        for(int i = 0; i < 5; i++){
            playerColor[i] = input.charAt(i);
        }
    }

    public void setComputerColor(){
        Random ran = new Random();
        int temp;
        do{
            temp = ran.nextInt(3125);
            playerColor = colors[temp];
        }while(next[temp] != 0);
        next[temp] = 1;
    }

    public boolean isColorExiest(Character A){
        for (int i = 0; i < 5; i++){
            if (playerColor[i] == A) return true;
        }
        return false;
    }

    public String getPlayerColor() {
        return Arrays.toString(playerColor);
    }
    
    static class PinCount{
        int BKCount;
        int WHCount;

        PinCount(int BKCount, int WHCount){
            this.BKCount = BKCount;
            this.WHCount = WHCount;
        }
    }
}
