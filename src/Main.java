package tictactoe;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe ticTacToe = new TicTacToe();
        System.out.println(ticTacToe);

        while (true) {
            System.out.print("Enter the coordinates: ");
            String[] pieces = scanner.nextLine().split(" ");
            int row, col;

            try {
                row = Integer.parseInt(pieces[0]);
                col = Integer.parseInt(pieces[1]);
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (ticTacToe.isOccupied(row, col)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            ticTacToe.makeMove(row, col);
            if (ticTacToe.isFinished()) {
                break;
            } else {
                ticTacToe.easyMove();
            }
        }
    }
}

class TicTacToe {
    private int totalTurns;
    private String winner;

    private final String[] grid;

    // initialises blank 3x3 grid
    public TicTacToe() {
        this.grid = "         ".split("");
    }

    // returns 3x3 grid as string
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("---------\n");
        for (int i = 0; i < 9; i += 3) {
            builder.append(String.format("| %s %s %s |\n", grid[i], grid[i + 1], grid[i + 2]));
        }
        builder.append("---------");
        return builder.toString();
    }

    // checks whether (row, col) is occupied
    public boolean isOccupied(int row, int col) {
        return !this.grid[twoDToOneD(row, col)].equals(" ");
    }

    // converts 2D coordinates to 1D (zero-indexed)
    private int twoDToOneD(int row, int col) {
        return (row - 1) * 3 + (col - 1);
    }

    // marks (row, col) with "X" or "O"
    public void makeMove(int row, int col) {
        int index = twoDToOneD(row, col);
        if (this.totalTurns % 2 == 0) {
            this.grid[index] = "X";

        } else {
            this.grid[index] = "O";
        }
        this.totalTurns++;
        System.out.println(this);
        checkWinner();
        if (this.winner != null && !this.winner.isBlank()) {
            System.out.println(this.winner + " wins");
        } else if (isFinished()) {
            System.out.println("Draw");
        }
    }

    // checks if there is a winner
    private void checkWinner() {
        // horizontal three in a row
        for (int i = 0; i < 9; i += 3) {
            if (grid[i].equals(grid[i + 1]) && grid[i + 1].equals(grid[i + 2])) {
                this.winner = grid[i];
            }
        }

        // vertical three in a row
        for (int i = 0; i < 3; i += 1) {
            if (grid[i].equals(grid[i + 3]) && grid[i + 3].equals(grid[i + 6])) {
                this.winner = grid[i];
            }
        }

        // diagonal three in a row
        if (grid[0].equals(grid[4]) && grid[4].equals(grid[8])) {
            this.winner = grid[4];
        }

        // opposite diagonal three in a row
        if (grid[2].equals(grid[4]) && grid[4].equals(grid[6])) {
            this.winner = grid[4];
        }
    }

    // checks if a player has won, or if no further moves are possible
    public boolean isFinished() {
        return this.winner != null && !this.winner.isBlank() || totalTurns == 9;
    }

    // makes a random move
    public void easyMove() {
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        while (true) {
            int row = random.nextInt(3) + 1;
            int col = random.nextInt(3) + 1;
            if (!isOccupied(row, col)) {
                makeMove(row, col);
                break;
            }
        }
    }
}
