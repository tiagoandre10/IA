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
            System.out.println("  ");
            System.out.println("1. Easy vs Easy");
            System.out.println("2. Medium vs Medium");
            System.out.println("3. Hard vs Hard");
            System.out.println("4. Easy vs Hard");
            System.out.println("5. Medium vs Hard");
            System.out.println("6. Easy vs Medium");
            System.out.println("  ");
            System.out.print("Option: ");

            option = stdin.nextInt();

            switch (option) {
              case 1:
                ComputerComputer(1,1);
                break;
              case 2:
                ComputerComputer(2,2);
                break;
              case 3:
                ComputerComputer(3,3);
                break;
              case 4:
                ComputerComputer(1,3);
                break;
              case 5:
                ComputerComputer(2,3);
                break;
              case 6:
                ComputerComputer(1,2);
                break;
              default:
                System.out.print("Invalid option!\n" +
                        "Option: ");
                break;
            }
            //ComputerComputer();
            break;
          case 4:
            System.out.println("  ");
            System.out.println("1. Easy vs Easy");
            System.out.println("2. Medium vs Medium");
            System.out.println("3. Hard vs Hard");
            System.out.println("4. Easy vs Hard");
            System.out.println("5. Medium vs Hard");
            System.out.println("6. Easy vs Medium");
            System.out.println("  ");
            System.out.print("Option: ");

            option = stdin.nextInt();

            switch (option) {
              case 1:
                ComputerComputerAlphaBeta(1,1);
                break;
              case 2:
                ComputerComputerAlphaBeta(2,2);
                break;
              case 3:
                ComputerComputerAlphaBeta(3,3);
                break;
              case 4:
                ComputerComputerAlphaBeta(1,3);
                break;
              case 5:
                ComputerComputerAlphaBeta(2,3);
                break;
              case 6:
                ComputerComputerAlphaBeta(1,2);
                break;
              default:
                System.out.print("Invalid option!\n" +
                        "Option: ");
                break;
            }
            //ComputerComputerAlphaBeta();
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
