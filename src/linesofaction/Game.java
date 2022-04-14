package linesofaction;

import java.util.*;
import static linesofaction.Board.*;
import static linesofaction.Rules.*;

public class Game {
    Game() {
        initialize();
    }

    static void PlayerPlayer(Game game) {
        Scanner stdin = new Scanner(System.in);
        int player = 1;
        String move, play;

        System.out.print("\033[H\033[2J");
        System.out.flush();

        while(!GameOver()) {
            status();
            System.out.print("\n(PLAYER " + player + ")\n\n" +
                    "Piece to move: ");
            move = stdin.next();
            System.out.print("Piece destination: ");
            play = stdin.next();

            while(!InBounds(GetColumn(move), GetRow(move)) && !InBounds(GetColumn(play), GetRow(play))) {
                System.out.print("\nPlay out of bounds! Try again!" +
                        "Piece to move: ");
                move = stdin.next();
                System.out.print("Piece destination: ");
                play = stdin.next();
            }

            while(!IsLegal(move, play)) {
                System.out.print("\nInvalid play! Try again!" +
                        "Piece to move: ");
                move = stdin.next();
                System.out.print("Piece destination: ");
                play = stdin.next();
            }

            board[GetRow(play)][GetColumn(play)] = board[GetRow(move)][GetColumn(move)];
            board[GetRow(move)][GetColumn(move)] = -1;

            System.out.println("Play: " + move + " -> " + play);

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
