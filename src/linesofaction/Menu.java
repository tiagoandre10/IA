package src.linesofaction;

import java.util.*;
import static src.linesofaction.Game.*;

public class Menu {
  static void menu() throws InterruptedException {
    Scanner stdin = new Scanner(System.in);

    System.out.print("\033[H\033[2J");
    System.out.flush();

    System.out.println("  ");
    System.out.println("%----------  LINES OF ACTION  ----------%");
    System.out.println("  ");
    System.out.println("1. Player vs Player");
    System.out.println("2. Player vs Computer (Minimax Alpha Beta Pruning Algorithm");
    System.out.println("3. Computer vs Computer (Minimax Algorithm)");
    System.out.println("4. Computer vs Computer (Minimax Alpha Beta Pruning Algorithm)");
    System.out.println("5. Exit");
    System.out.println("  ");
    System.out.print("Option: ");

    while(true) {
        int option = stdin.nextInt();

        switch (option) {
          case 1:
            PlayerPlayer();
            break;
          case 2:
            System.out.println("  ");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard");
            System.out.println("  ");
            System.out.print("Option: ");

            int difficulty = stdin.nextInt();

            switch (difficulty) {
              case 1:
                PlayerComputer(1);
                break;
              case 2:
                PlayerComputer(2);
                break;
              case 3:
                PlayerComputer(3);
                break;
              default:
                System.out.print("Invalid option!\n" +
                        "Option: ");
                break;
            }
            break;
          case 3:
            ComputerComputer();
            break;
          case 4:
            ComputerComputerAlphaBeta();
            break;
          case 5:
            System.exit(0);
            break;
          default:
            System.out.print("Invalid option!\n" +
                    "Option: ");
            break;
        }
      System.exit(0);
    }
  }
}
