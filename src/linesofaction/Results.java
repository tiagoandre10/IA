package src.linesofaction;

import static src.linesofaction.Game.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class Results {
  static void results() throws InterruptedException {
    LocalTime beginning = LocalTime.now();

    //Minimax Algorithm
    //ComputerComputer();
    //Minimax Alpha Beta Pruning Algorithm
    ComputerComputerAlphaBeta();

    LocalTime ending = LocalTime.now();
    System.out.printf("Total time: %s seconds%n", beginning.until(ending, ChronoUnit.SECONDS));
    System.exit(0);
  }

}
