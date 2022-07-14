package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input command: ");
            String command = scanner.nextLine();
            TicTacToe ticTacToe = new TicTacToe();

            if (command.equals("exit")) break;
            if (!command.matches("start (easy|medium|hard|user) (easy|medium|hard|user)")) {
                System.out.println("Bad parameters!");
                continue;
            }
            System.out.println(ticTacToe);

            String playerX = command.split(" ")[1];
            String playerO = command.split(" ")[2];

            while (!ticTacToe.isFinished()) {
                ticTacToe.play(scanner, playerX);
                if (ticTacToe.isFinished()) break;
                ticTacToe.play(scanner, playerO);
            }
            System.out.println();
        }
    }
}
