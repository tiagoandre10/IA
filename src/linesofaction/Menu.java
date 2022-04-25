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
          case 2:
            PlayerComputer();
          case 3:
            ComputerComputer();
          case 4:
            ComputerComputerAlphaBeta();
          case 5:
            System.exit(0);
          default:
            System.out.print("Invalid option!\n" +
                    "Option: ");
        }

        if(option == 1 || option == 2 || option == 3 || option == 4)
            break;
    }
  }
}
