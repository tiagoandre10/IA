package src.linesofaction;

import java.util.ArrayList;
import java.util.Collections;

public class Heuristic {

    // Evaluates board according to pieces position with fixed values.
    public static int piecePosition(int[][] board, int turn){
        int other;
        if(turn == -1){
            other = 0;
        } else {
            other = -1;
        }
        int turnScore = 0;
        int otherScore = 0;

        int[][] valueTable =
                {{-80,-25,-20,-15,-15,-20,-25,-80},
                        {-25,10,10,10,10,10,10,-25},
                        {-25,10,10,25,25,10,10,-25},
                        {-25,10,10,25,25,10,10,-25},
                        {-25,10,10,10,10,10,10,-25},
                        {-80,-25,-20,-15,-15,-20,-25,-80}};

        for(int i = 0; i < 8; i++){
            for(int j =0 ; j < 8; j++){
                if(board[i][j] == turn){
                    turnScore += valueTable[i][j];
                }else if(board[i][j] == other){
                    otherScore += valueTable[i][j];
                }
            }
        }
        return turnScore - otherScore;
    }

    // Evaluates according to area of board containing all pieces. Smaller area is better.
    public static double area(int[][]board, int turn){
        int other;
        if(turn == -1){
            other = 0;
        } else {
            other = -1;
        }
        ArrayList<Integer> turnX = new ArrayList<>();
        ArrayList<Integer> turnY = new ArrayList<>();
        ArrayList<Integer> otherX = new ArrayList<>();
        ArrayList<Integer> otherY = new ArrayList<>();

        for(int i = 0; i < 6; i++){
            for(int j =0;j < 6; j++){
                if(board[i][j] == turn){
                    if(!turnX.contains(i)){
                        turnX.add(i);
                    }
                    if(!turnY.contains(j)){
                        turnY.add(j);
                    }
                }else if(board[i][j] == other){
                    if(!otherX.contains(i)){
                        otherX.add(i);
                    }
                    if(!otherY.contains(j)){
                        otherY.add(j);
                    }
                }
            }
        }

        Collections.sort(turnX);
        Collections.sort(turnY);
        Collections.sort(otherX);
        Collections.sort(otherY);

        int areaOfTurn = (turnX.get(turnX.size() - 1) - turnX.get(0)) * (turnY.get(turnY.size() - 1) - turnY.get(0));
        int areaOfOther = (otherX.get(otherX.size() - 1) - otherX.get(0)) * (otherY.get(otherY.size() - 1) - otherY.get(0));

        return areaOfOther - areaOfTurn;

    }
}
