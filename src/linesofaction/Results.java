package src.linesofaction;

import static src.linesofaction.Game.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

//This class is only used to compute the experimental results
public class Results {
  static void results() throws InterruptedException {
    LocalTime beginning = LocalTime.now();

    //Minimax Algorithm
    ComputerComputer(2,3);

    //Minimax Alpha Beta Pruning Algorithm
    //ComputerComputerAlphaBeta(2,3);

    LocalTime ending = LocalTime.now();
    System.out.printf("Total time: %s seconds%n", beginning.until(ending, ChronoUnit.SECONDS));
    System.exit(0);
  }

}
