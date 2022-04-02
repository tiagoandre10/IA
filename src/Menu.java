import java.util.*;

public class Menu extends Game {
    Menu() {
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

        Game game = new Game();

        while(true) {
            int option = stdin.nextInt();

            switch (option) {
                case 1 -> PlayerPlayer(game, 1);
                case 2 -> PlayerComputer(game);
                case 3 -> ComputerComputer(game);
                case 4 -> System.exit(0);
                default -> System.out.print("Invalid option!\n" +
                        "Option: ");
            }

            if(option == 1 || option == 2 || option == 3)
                break;
        }
    }
}
