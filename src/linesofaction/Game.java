package src.linesofaction;

import java.util.*;
import static src.linesofaction.Board.*;
import static src.linesofaction.Rules.*;

public class Game {
  static int _blackPieces = 12;
  static int _whitePieces = 12;
  //static int _blackPieces = 2;
  //static int _whitePieces = 2;
  static int _totalMoves = 0;

  Game() {
    initialize();
    //initializeTester();
  }

  public static int getBlackPieces(int[][] board) {
    int blackPieces = 0;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (board[i][j] > 12 && board[i][j] < 25) blackPieces++;
      }
    }
    return blackPieces;
  }

  public static int getWhitePieces(int[][] board) {
    int whitePieces = 0;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (board[i][j] > 0 && board[i][j] < 13) whitePieces++;
      }
    }
    return whitePieces;
  }

  public static int getTotalMoves() {
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

    while (winner == -1) {
      status();
      System.out.print("\n(PLAYER " + player + ")\n\n");
      System.out.print("Piece to move: ");
      move = stdin.next();
      System.out.print("Piece destination: ");
      play = stdin.next();

      boolean differentPlay = false;

      while (!differentPlay) {
        if (move.equals(play)) {
          System.out.println("You cannot stay in the same place! Try again!");
          System.out.print("Piece to move: ");
          move = stdin.next();
          System.out.print("Piece destination: ");
          play = stdin.next();
        } else {
          differentPlay = true;
        }
      }

      //Checks if the player is trying to move his pieces
      boolean canMove = false;
      while (!canMove) {
        int helperPiece = board[GetRow(move)][GetColumn(move)];

        if (player == 1) {
          if (helperPiece < 13) {
            System.out.println("You can only move the black pieces! Try again!");
            System.out.print("Piece to move: ");
            move = stdin.next();
            System.out.print("Piece destination: ");
            play = stdin.next();
          } else {
            canMove = true;
          }

        } else {
          if (helperPiece < 1 || helperPiece > 12) {
            System.out.println("You can only move the white pieces! Try again!");
            System.out.print("Piece to move: ");
            move = stdin.next();
            System.out.print("Piece destination: ");
            play = stdin.next();
          } else {
            canMove = true;
          }
        }
      }

      while (!IsLegal(move, play)) {
        System.out.println("");
        System.out.println("");
        System.out.println("Invalid play! Try again!");
        System.out.print("Piece to move: ");
        move = stdin.next();
        System.out.print("Piece destination: ");
        play = stdin.next();

        differentPlay = false;

        while (!differentPlay) {
          if (move.equals(play)) {
            System.out.println("You cannot stay in the same place! Try again!");
            System.out.print("Piece to move: ");
            move = stdin.next();
            System.out.print("Piece destination: ");
            play = stdin.next();
          } else {
            differentPlay = true;
          }
        }

        //Checks if the player is trying to move his pieces
        canMove = false;
        while (!canMove) {
          int helperPiece = board[GetRow(move)][GetColumn(move)];

          if (player == 1) {
            if (helperPiece < 13) {
              System.out.println("You can only move the black pieces! Try again!");
              System.out.print("Piece to move: ");
              move = stdin.next();
              System.out.print("Piece destination: ");
              play = stdin.next();
            } else {
              canMove = true;
            }

          } else {
            if (helperPiece < 1 || helperPiece > 12) {
              System.out.println("You can only move the white pieces! Try again!");
              System.out.print("Piece to move: ");
              move = stdin.next();
              System.out.print("Piece destination: ");
              play = stdin.next();
            } else {
              canMove = true;
            }
          }
        }
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
      if (_totalMoves >= 150) {
        System.out.println("It was a draw!");
        winner = 0;
      }

      winner = GameOver(board);

      System.out.println("Play: " + move.toUpperCase() + " -> " + play.toUpperCase());

      if (player == 1)
        player = 2;

      else
        player = 1;
    }
  }

  static void PlayerComputer() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  static void ComputerComputer() throws InterruptedException {
    int player = 1;
    int winner = -1;

    System.out.print("\033[H\033[2J");
    System.out.flush();

    new Game();

    while (winner == -1) {
      System.out.print("\n(PLAYER " + player + ")\n\n");
      int[][] result = new int[8][8];
      src.linesofaction.Minimax minimax = new src.linesofaction.Minimax(4);
      int[][] copy = new int[8][8];
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          copy[i][j] = board[i][j];
        }
      }
      if (player == 1) {
        result = minimax.bestMove(copy, 4, 1);
        for(int i=0; i<8; i++){
          for(int j=0; j<8; j++){
            board[i][j] = result[i][j];
          }
        }
      } else if(player == 2){
        result = minimax.bestMove(copy, 4, 2);
        for(int i=0; i<8; i++){
          for(int j=0; j<8; j++){
            board[i][j] = result[i][j];
          }
        }
      }
      if(player == 1){
        player = 2;
      }
      else if(player == 2){
        player = 1;
      }
      status();
      winner = GameOver(board);
    }
  }
}
