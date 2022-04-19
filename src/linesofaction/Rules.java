package src.linesofaction;

import java.util.*;
import src.linesofaction.graph.*;
import static src.linesofaction.Game.*;
import static src.linesofaction.Board.*;
import static src.linesofaction.Direction.*;

public class Rules {
  //Gets the column number of the move
  static int GetColumn(String move) {
    return move.toUpperCase().charAt(0) - 'A';
  }

  //Gets the row number of the move
  static int GetRow(String move) {
    return Math.abs(move.charAt(1) - '0' - 8);
  }

  //Check if a move is legal
  static boolean IsLegal(String move, String play) {
    int old_row = GetRow(move), old_col = GetColumn(move);
    int new_row = GetRow(play), new_col = GetColumn(play);

    int possible_move = PiecesCountAlong(move, play);
    int required_move = Math.max(Math.abs(new_row - old_row), Math.abs(new_col - old_col));

    return ((possible_move == required_move) && !blocked(move, play));
  }

  //Counts the pieces in a row, column or diagonal
  static int PiecesCountAlong(String move, String play) {
    int dRow = GetRow(move) - GetRow(play);
    int dCol = GetColumn(play) - GetColumn(move);
    int row = GetRow(move), col = GetColumn(move), count = 0;

    for(Direction dir : Direction.values())
        for(int i = 1; i <= 7; i++)
            if(dRow == dir.row * i && dCol == dir.col * i) {
                row--;
                col--;

                if(dir == NOWHERE)
                    return (!(board[row][col] == -1) ? 1 : 0);

                else if(dir == E || dir == N) {
                    for(int j = 0; j < 8; j++)
                        if(!(board[row][j] == -1))
                            count++;
                }

                else if (dir == S || dir == W) {
                    for(int j = 0; j < 8; j++)
                        if(!(board[j][col] == -1))
                            count++;
                 }

                else if (dir == NE || dir == SE) {
                    for(int j = -Math.min(row, col); j <= Math.min(7 - row, 7 - col); j++)
                        if(!(board[row + j][col + j] == -1))
                            count++;
                }

                else if (dir == NW || dir == SW) {
                    for(int j = -Math.min(7 - row, col); j <= Math.min(row, 7 - col); j++)
                        if (!(board[row -j][col + j] == -1))
                            count++;
                }
            }

    return count;
  }

  //Checks if the we can't make the play because we are blocked
  static boolean blocked(String move, String play) {
    if(move.equals(play))
      return true;

    int old_row = GetRow(move), new_row = GetRow(play);
    int old_col = GetColumn(move), new_col = GetColumn(play);
    int dRow = new_row - old_row, dCol = new_col - old_col;

    for(Direction dir : Direction.values()){
      for(int i = 1; i <= 7; i++){
        if(dRow == dir.row * i && dCol == dir.col * i){
          for(int j = 1; j < i; j++) {
            if(board[new_row][new_col] > 0 && board[new_row][new_col] < 13 && board[old_row + dir.row * j][old_col + dir.col * j] == 0)
              return true;

            if(board[new_row][new_col] > 12 && board[new_row][new_col] < 25 && board[old_row + dir.row * j][old_col + dir.col * j] == 1)
              return true;

            if(board[new_row][new_col] > 12 && board[new_row][new_col] < 25 && board[old_row + dir.row * j][old_col + dir.col * j] == 0)
              return true;

            if(board[new_row][new_col] > 0 && board[new_row][new_col] < 13  && board[old_row + dir.row * j][old_col + dir.col * j] == 1)
              return true;
          }
        }
      }
    }
    return false;
  }


  static boolean GameOver() {
    List<Graph> gameGraphs = getGraphs();
      for (Graph gameGraph : gameGraphs) {
          if (gameGraph.getFirstNode().getValue() > 0 && gameGraph.getFirstNode().getValue() < 13 && gameGraph.getNodes().size() == getWhitePieces()) {
              System.out.println("White team won the game!");
          } else if (gameGraph.getFirstNode().getValue() > 12 && gameGraph.getFirstNode().getValue() < 25 && gameGraph.getNodes().size() == getBlackPieces()) {
              System.out.println("Black team won the game!");
          }
      }
    return false;
  }
}
