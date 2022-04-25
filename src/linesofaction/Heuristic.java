package src.linesofaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static src.linesofaction.Rules.checkSurroundings;

public class Heuristic {

  static List<Integer> visitedWhites = new ArrayList<>();
  static List<Integer> visitedBlacks = new ArrayList<>();

  // Evaluates board according to pieces position with fixed values.
  public static int piecePosition(int[][] board, int turn){
      int other;
      if(turn == 1 || turn == 0){
          other = 2;
      } else {
          other = 1;
      }
      int turnScore = 0;
      int otherScore = 0;

      int[][] valueTable =
              {{-80,-50,-50,-50,-50,-50,-50,-80},
                      {-50,-10,-10,-10,-10,-10,-10,-50},
                      {-50,-10,125,125,125,125,-10,-50},
                      {-50,-10,125,250,250,125,-10,-50},
                      {-50,-10,125,250,250,125,-10,-50},
                      {-50,-10,125,125,125,125,-10,-50},
                      {-50,-10,-10,-10,-10,-10,-10,-50},
                      {-80,-50,-50,-50,-50,-50,-50,-80}};

      for(int i = 0; i < 8; i++){
          for(int j =0 ; j < 8; j++){
              if(turn == 1 || turn == -1){
                  if(board[i][j] > 12 && board[i][j] < 25){
                      turnScore += valueTable[i][j];
                  }else if(turn == other) {
                      if(board[i][j] > 0 && board[i][j] < 13) {
                          otherScore += valueTable[i][j];
                      }
                  }
              }
              else if(turn == 2 || turn == 0){
                  if(board[i][j] > 0 && board[i][j] < 13){
                      turnScore += valueTable[i][j];
                  }else if(turn == other) {
                      if(board[i][j] > 12 && board[i][j] < 25) {
                          otherScore += valueTable[i][j];
                      }
                  }
              }
          }
      }
      return turnScore - otherScore;
  }

  // Evaluates according to area of board containing all pieces. Smaller area is better.
  public static double area(int[][]board, int turn){

    ArrayList<Integer> turnX = new ArrayList<>();
    ArrayList<Integer> turnY = new ArrayList<>();

    for(int i = 0; i < 8; i++){
        for(int j =0;j < 8; j++){
            if(turn == 1 || turn == -1){
                if(board[i][j] > 12 && board[i][j] < 25){
                    if(!turnX.contains(i)){
                        turnX.add(i);
                    }
                    if(!turnY.contains(j)){
                        turnY.add(j);
                    }
                }
            }else if(turn == 2 || turn == 0){
                if(board[i][j] > 0 && board[i][j] < 13){
                    if(!turnX.contains(i)){
                        turnX.add(i);
                    }
                    if(!turnY.contains(j)){
                        turnY.add(j);
                    }
                }
            }
        }
    }

    Collections.sort(turnX);
    Collections.sort(turnY);

    int areaOfTurn = (turnX.get(turnX.size() - 1) - turnX.get(0)) * (turnY.get(turnY.size() - 1) - turnY.get(0));

    return areaOfTurn * -1;

  }

  //Evaluates board according to the number of total pieces of the opponent (less pieces is worse, because it's easier for the opponent to win).
  public static int totalOpponentPieces(int[][]board, int turn){
    int other;
    if(turn == 1 || turn == -1){
      other = 2;
    } else if(turn == 2 || turn == 0){
      other = 1;
    }
    int totalOpponentPieces = 0;

    for(int i = 0; i < 8; i++){
      for(int j =0;j < 8; j++){
        if(turn == 1 || turn == -1){
          if(board[i][j] > 0 && board[i][j] < 13){
            totalOpponentPieces++;
          }
        }else if(turn == 2 || turn == 0){
          if(board[i][j] > 12 && board[i][j] < 25){
            totalOpponentPieces++;
          }
        }
      }
    }

    return totalOpponentPieces;
  }

  //Evaluates board according to the number of total pieces connected.
  public static int totalConnectedPieces(int[][] board, int turn){
    int maxWhiteConnected = 0;
    int maxBlackConnected = 0;

    for (int i = 0; i < 8; i++){
      for(int j= 0; j < 8; j++){
        //Check white pieces
        if (board[i][j] > 0 && board[i][j] < 13) {
          visitedWhites.clear();
          visitedWhites.add(board[i][j]);
          checkSurroundings(i, j, false);
          if(visitedWhites.size() > maxWhiteConnected){
            maxWhiteConnected = visitedWhites.size();
          }
        }
        else if (board[i][j] > 12 && board[i][j] < 25){
          visitedBlacks.clear();
          visitedBlacks.add(board[i][j]);
          checkSurroundings(i, j, true);
          if(visitedBlacks.size() > maxBlackConnected){
            maxBlackConnected = visitedBlacks.size();
          }
        }
      }
    }

    int totalConnected = 0;
    if(turn == 1 || turn == -1){
      totalConnected = maxBlackConnected;
    } else if(turn == 2 || turn == 0){
      totalConnected = maxWhiteConnected;
    }

    return totalConnected;

  }
}
