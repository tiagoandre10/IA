package src.linesofaction;

import java.util.*;
import static src.linesofaction.Board.*;
import static src.linesofaction.Rules.*;

public class Game {
  static int _blackPieces = 12;
  static int _whitePieces = 12;
  static int _totalMoves = 0;

  Game() {
    initialize();
  }


  public static int getBlackPieces(){
    return _blackPieces;
  }

  public static int getWhitePieces(){
    return _whitePieces;
  }

  public static int getTotalMoves(){
    return _totalMoves;
  }




  static void PlayerPlayer() {
      Scanner stdin = new Scanner(System.in);
      int player = 1;
      String move, play;
      int winner = -1;

      System.out.print("\033[H\033[2J");
      System.out.flush();

      new Game();

      while(winner == -1) {
        status();
        System.out.print("\n(PLAYER " + player + ")\n\n");
        System.out.print("Piece to move: ");
        move = stdin.next();
        System.out.print("Piece destination: ");
        play = stdin.next();

        //Checks if the player is trying to move his pieces
        boolean canMove = false;
        while (!canMove){
          int helperPiece = board[GetRow(move)][GetColumn(move)];

          if(player == 1) {
            if (helperPiece < 13) {
              System.out.println("You can only move the black pieces! Try again!");
              System.out.print("Piece to move: ");
              move = stdin.next();
              System.out.print("Piece destination: ");
              play = stdin.next();
            }
            else{
              canMove = true;
            }

          }
          else{
              if (helperPiece < 1 || helperPiece > 12){
                System.out.println("You can only move the white pieces! Try again!") ;
                System.out.print("Piece to move: ");
                move = stdin.next();
                System.out.print("Piece destination: ");
                play = stdin.next();
              }
              else{
                canMove = true;
              }
          }
        }

        while(!IsLegal(move, play)) {
          System.out.println("");
          System.out.println("");
          System.out.println("Invalid play! Try again!") ;
          System.out.print("Piece to move: ");
          move = stdin.next();
          System.out.print("Piece destination: ");
          play = stdin.next();
        }

        //The player 2 (white) took a piece of the enemy
        if (board[GetRow(play)][GetColumn(play)] < 25 && board[GetRow(play)][GetColumn(play)] > 12) {
          _blackPieces--;
        }

        //The player 1 (black) took a piece of the enemy
        if (board[GetRow(play)][GetColumn(play)] < 13 && board[GetRow(play)][GetColumn(play)] > 0) {
          _whitePieces--;
        }

        board[GetRow(play)][GetColumn(play)] = board[GetRow(move)][GetColumn(move)];
        board[GetRow(move)][GetColumn(move)] = -1;

        _totalMoves++;
        if(_totalMoves >= 150){
          System.out.println("It was a draw!");
          winner = 0;
        }

        winner = GameOver(board, _whitePieces, _blackPieces);

        System.out.println("Play: " + move.toUpperCase() + " -> " + play.toUpperCase());

        if(player == 1)
            player = 2;

        else
            player = 1;
      }
  }

  static void PlayerComputer() {
      System.out.print("\033[H\033[2J");
      System.out.flush();
  }

  static void ComputerComputer() {
      System.out.print("\033[H\033[2J");
      System.out.flush();
  }
}
