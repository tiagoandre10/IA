package src.linesofaction;

import java.util.*;
import static src.linesofaction.Board.*;
import static src.linesofaction.Direction.*;
import static src.linesofaction.Game.getBlackPieces;
import static src.linesofaction.Game.getWhitePieces;

public class Rules {
  static List<Integer> visitedWhites = new ArrayList<>();
  static List<Integer> visitedBlacks = new ArrayList<>();

  public static String GetString(int row, int col){
    int rowValue = row + 'A';
    char rowChar = (char) rowValue;
    int colValue = col + 1;
    char colChar = Character.forDigit(colValue, 10);

    return "" + rowChar + colChar;
  }

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

    return ((possible_move != 0) &&(possible_move == required_move) && !blocked(move, play));
  }

  //Counts the pieces in a row, column or diagonal
  static int PiecesCountAlong(String move, String play) {
    //Row distances between old row and new row
    int dRow = Math.abs(GetRow(move) - GetRow(play));
    //Column distance between old column and new column
    int dCol = Math.abs(GetColumn(play) - GetColumn(move));

    int row = GetRow(move), col = GetColumn(move);
    int _row = GetRow(play), _col = GetColumn(play);
    int count = 0;

    //Move left or right
    if(dRow == 0){
      count = piecesAlongHelper(E, row, col);
    }
    //Move up or down
    else if(dCol == 0){
      count = piecesAlongHelper(N, row, col);
    }
    //Move in a diagonal
    else if(dCol == dRow){
      if(_row > row && _col < col){
        count = piecesAlongHelper(SW, row, col);
      }
      else if(_row < row && _col < col){
        count = piecesAlongHelper(NW, row, col);
      }
      else if(_row > row && _col > col){
        count = piecesAlongHelper(SE, row, col);
      }
      else if(_row < row && _col > col){
        count = piecesAlongHelper(NE, row, col);
      }
    }
    return count;
  }

  static int piecesAlongHelper(Direction dir, int row, int col){
    int count = 0;
    if(dir == NOWHERE){
      return (!(board[row][col] == -1) ? 1 : 0);
    }

    else if(dir == E || dir == W) {
      for(int j = 0; j < 8; j++){
        if(!(board[row][j] == -1)){
          count++;
        }
      }
    }

    else if (dir == S || dir == N) {
      for(int j = 0; j < 8; j++) {
        if(!(board[j][col] == -1)) {
          count++;
        }
      }
    }

    else if (dir == NE) {
      int colNE = col + row;
      if(colNE > 7){
        colNE = 7;
      }
      int rowNE = row - (colNE - col);
      for(int j = rowNE; j < 8 && colNE >= 0; j++){
        if (!(board[j][colNE] == -1)){
          count++;
        }
        colNE--;
      }
    }

    else if (dir == SW) {
      int colSW = col + row;
      if(colSW > 7){
        colSW = 7;
      }
      int rowSW = row - (colSW - col);
      for(int j = rowSW; j <8 && colSW >= 0; j++) {
        if (!(board[j][colSW] == -1)){
          count++;
        }
        colSW--;
      }
    }

    else if (dir == NW) {
      int rowNW = row - col;
      if(rowNW < 0){
        rowNW = 0;
      }
      int colNW = col - row;
      if(colNW < 0){
        colNW = 0;
      }
      for(int j = rowNW; j < 8 && colNW < 8; j++) {
        if (!(board[j][colNW] == -1)) {
          count++;
        }
        colNW++;
      }
    }

    else if(dir == SE) {
      int rowSE = row - col;
      if(rowSE < 0){
        rowSE = 0;
      }
      int colSE = col - row;
      if(colSE < 0){
        colSE = 0;
      }
      for(int j = rowSE; j < 8 && colSE < 8; j++) {
        if (!(board[j][colSE] == -1)) {
          count++;
        }
        colSE++;
      }
    }
    return count;
  }

  //Checks if we can't make the play because we are blocked
  static boolean blocked(String move, String play) {
    if(move.equals(play)){
      return true;
    }

    int old_row = GetRow(move), new_row = GetRow(play);
    int old_col = GetColumn(move), new_col = GetColumn(play);
    int dRow = new_row - old_row, dCol = new_col - old_col;

    for(Direction dir : Direction.values()){
      for(int i = 1; i <= 7; i++){
        if(dRow == dir.row * i && dCol == dir.col * i){
          for(int j = 1; j < i; j++) {
            if(board[old_row][old_col] > 0 && board[old_row][old_col] < 13 && board[old_row + dir.row * j][old_col + dir.col * j] > 12 && board[old_row + dir.row * j][old_col + dir.col * j] < 25){
              return true;
            }

            if(board[old_row][old_col] > 12 && board[old_row][old_col] < 25 && board[old_row + dir.row * j][old_col + dir.col * j] > 0 && board[old_row + dir.row * j][old_col + dir.col * j] < 13){
              return true;
            }

            if(board[new_row][new_col] > 0 && board[new_row][new_col] < 13 && board[old_row + dir.row * j][old_col + dir.col * j] > 0 && board[old_row + dir.row * j][old_col + dir.col * j] < 13) {
              return true;
            }

            if(board[new_row][new_col] > 12 && board[new_row][new_col] < 25 && board[old_row + dir.row * j][old_col + dir.col * j] > 12 && board[old_row + dir.row * j][old_col + dir.col * j] < 25) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  static void checkSurroundings( int row, int column, boolean isBlack){

    //Down white piece
    if( (row+1 < 8 && 0 < board[row+1][column] && board[row+1][column] < 13 && !isBlack)){
      if(!visitedWhites.contains(board[row+1][column])){
        visitedWhites.add(board[row+1][column]);
        checkSurroundings(row+1, column, false);
      }
    }

    //Down black piece
    if((row+1 < 8 && 12 < board[row+1][column] && board[row+1][column] < 25 && isBlack)){
      if(!visitedBlacks.contains(board[row+1][column])){
        visitedBlacks.add(board[row+1][column]);
        checkSurroundings(row+1, column, true);
      }
    }

    //Up white piece
    if( row-1 >= 0 && 0 < board[row-1][column] && board[row-1][column] < 13 && !isBlack){
      if(!visitedWhites.contains(board[row-1][column])){
        visitedWhites.add(board[row-1][column]);
        checkSurroundings(row-1, column, false);
      }
    }

    //Up black piece
    if(row-1 >= 0 && 12 < board[row-1][column] && board[row-1][column] < 25 && isBlack){
      if(!visitedBlacks.contains(board[row-1][column])){
        visitedBlacks.add(board[row-1][column]);
        checkSurroundings(row-1, column, true);
      }
    }

    //Left white piece
    if( column-1 >= 0 && 0 < board[row][column-1] && board[row][column-1] < 13 && !isBlack){
      if(!visitedWhites.contains(board[row][column-1])){
        visitedWhites.add(board[row][column-1]);
        checkSurroundings(row, column-1, false);
      }
    }

    //Left black piece
    if (column-1 >= 0 && 12 < board[row][column-1] && board[row][column-1] < 25 && isBlack) {
      if(!visitedBlacks.contains(board[row][column-1])){
        visitedBlacks.add(board[row][column-1]);
        checkSurroundings(row, column-1, true);
      }
    }

    //Right white piece
    if( column+1 < 8 && 0 < board[row][column+1] && board[row][column+1] < 13 && !isBlack){
      if(!visitedWhites.contains(board[row][column+1])){
        visitedWhites.add(board[row][column+1]);
        checkSurroundings(row, column+1, false);
      }
    }

    //Right black piece
    if(column+1 < 8 && 12 < board[row][column+1] && board[row][column+1] < 25 && isBlack){
      if(!visitedBlacks.contains(board[row][column+1])){
        visitedBlacks.add(board[row][column+1]);
        checkSurroundings(row, column+1, true);
      }
    }

    //Diagonal up right white piece
    if( row-1 >= 0 && column+1 < 8 && 0 < board[row-1][column+1] && board[row-1][column+1] < 13 && !isBlack){
      if(!visitedWhites.contains(board[row-1][column+1])){
        visitedWhites.add(board[row-1][column+1]);
        checkSurroundings(row-1, column+1, false);
      }
    }

    //Diagonal up right black piece
    if(row-1 >= 0 && column+1 < 8 && 12 < board[row-1][column+1] && board[row-1][column+1] < 25 && isBlack){
      if(!visitedBlacks.contains(board[row-1][column+1])){
        visitedBlacks.add(board[row-1][column+1]);
        checkSurroundings(row-1, column+1, true);
      }
    }

    //Diagonal up left white piece
    if( row-1 >= 0 && column-1 >= 0 && 0 < board[row-1][column-1] && board[row-1][column-1] < 13 && !isBlack){
      if(!visitedWhites.contains(board[row-1][column-1])){
        visitedWhites.add(board[row-1][column-1]);
        checkSurroundings(row-1, column-1, false);
      }
    }

    //Diagonal up left black piece
    if ( row-1 >= 0 && column-1 >= 0 && 12 < board[row-1][column-1] && board[row-1][column-1] < 25 && isBlack){
      if(!visitedBlacks.contains(board[row-1][column-1])){
        visitedBlacks.add(board[row-1][column-1]);
        checkSurroundings(row-1, column-1, true);
      }
    }

    //Diagonal down right white piece
    if( row+1 < 8 && column+1 < 8 && 0 < board[row+1][column+1] && board[row+1][column+1] < 13 && !isBlack){
      if(!visitedWhites.contains(board[row+1][column+1])){
        visitedWhites.add(board[row+1][column+1]);
        checkSurroundings(row+1, column+1, false);
      }
    }

    //Diagonal down right black piece
    if(row+1 < 8 && column+1 < 8 && 12 < board[row+1][column+1] && board[row+1][column+1] < 25 && isBlack){
      if(!visitedBlacks.contains(board[row+1][column+1])){
        visitedBlacks.add(board[row+1][column+1]);
        checkSurroundings(row+1, column+1, true);
      }
    }

    //Diagonal down left white piece
    if( row+1 < 8 && column-1 >= 0 && 0 < board[row+1][column-1] && board[row+1][column-1] < 13 && !isBlack){
      if(!visitedWhites.contains(board[row+1][column-1])){
        visitedWhites.add(board[row+1][column-1]);
        checkSurroundings(row+1, column-1, false);
      }
    }

    //Diagonal down left black piece
    if(row+1 < 8 && column-1 >= 0 && 12 < board[row+1][column-1] && board[row+1][column-1] < 25 && isBlack){
      if(!visitedBlacks.contains(board[row+1][column-1])){
        visitedBlacks.add(board[row+1][column-1]);
        checkSurroundings(row+1, column-1, true);
      }
    }
  }

  //Checks to see if a player has won the game
  static int GameOver(int[][] board) {
    boolean foundWhite = false;
    boolean foundBlack = false;
    visitedWhites.clear();
    visitedBlacks.clear();
    int whitePieces = getWhitePieces(board);
    //System.out.println("White Pieces: "+ whitePieces);
    int blackPieces = getBlackPieces(board);
    //System.out.println("Black Pieces: "+ blackPieces);

    if(whitePieces == 1) {
      return 2;
    }

    if(blackPieces == 1) {
      return 1;
    }

    for (int i = 0; i < 8; i++){
      for(int j= 0; j < 8; j++){
        //Check white pieces
        if (board[i][j] > 0 && board[i][j] < 13 && !foundWhite) {
          foundWhite = true;
          visitedWhites.add(board[i][j]);
          checkSurroundings(i, j, false);
          if(foundBlack){
            break;
          }
        }
        else if (board[i][j] > 12 && board[i][j] < 25 && !foundBlack){
          foundBlack = true;
          visitedBlacks.add(board[i][j]);
          checkSurroundings(i, j, true);
          if(foundWhite){
            break;
          }
        }
      }
    }

    if(visitedWhites.size() == whitePieces){
      return 2;
    }

    if(visitedBlacks.size() == blackPieces){
      return 1;
    }

    return -1;
  }
}
