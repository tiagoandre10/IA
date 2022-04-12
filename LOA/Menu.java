package LOA;

import java.util.*;
import static LOA.Game.*;

public class Menu {
     static void menu() {
        Scanner stdin = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.print("""
                %----------  LINES OF ACTION  ----------%
                                
                1. Player vs Player
                2. Player vs Computer
                3. Computer vs Computer
                4. Exit
                                
                Option:\s""");

        while(true) {
            int option = stdin.nextInt();
            Game game = new Game();

            switch (option) {
                case 1 -> PlayerPlayer(game);
                case 2 -> PlayerComputer();
                case 3 -> ComputerComputer();
                case 4 -> System.exit(0);
                default -> System.out.print("Invalid option!\n" +
                        "Option: ");
            }

            if(option == 1 || option == 2 || option == 3)
                break;
        }
    }
}
