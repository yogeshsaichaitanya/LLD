import model.Board;
import model.Player;
import model.piece.PlayingPiece;
import model.piece.PlayingPieceO;
import model.piece.PlayingPieceX;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TicTacToe {
    private Board board;
    int size;
    private Queue<Player> players;

    public TicTacToe(int size) {
        this.board = new Board(size);
        players = new LinkedList<>();
        this.size = size;
        initializeGame();
    }

    private void initializeGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Player 1 name : ");
        String player1Name = sc.nextLine();
        System.out.println("Enter Player 2 name : ");
        String player2Name = sc.nextLine();

        Player player1 = new Player(player1Name, new PlayingPieceX());
        Player player2 = new Player(player2Name, new PlayingPieceO());

        players.add(player1);
        players.add(player2);
    }

    public void startGame() {
        boolean isWinner = false;
        Scanner sc = new Scanner(System.in);

        while (!isWinner) {
            Player currentPlayer = players.peek();
            System.out.println(currentPlayer.getName() + " it's your turn. Enter (row, column) (0-indexed): ");
            String[] split = sc.nextLine().split(",");
            int row = Integer.parseInt(split[0].trim());
            int col = Integer.parseInt(split[1].trim());

            boolean pieceAddedSuccessfully = board.putPieceAt(row, col, currentPlayer.getPiece());
            if (!pieceAddedSuccessfully) {
                System.out.println("Position already occupied, try again.");
                continue;
            }
            if (checkWinner(row, col, currentPlayer.getPiece())) {
                System.out.println(currentPlayer.getName() + " wins!");
                isWinner = true;
            }
            players.add(players.poll());
            System.out.println(board);

            if (board.isFull() && !isWinner) {
                System.out.println("It's a draw!");
                break;
            }
        }
    }

    private boolean checkWinner(int row, int col, PlayingPiece piece) {
        // Check the row
        if (checkRow(row, piece)) {
            return true;
        }
        // Check the column
        if (checkColumn(col, piece)) {
            return true;
        }
        // Check the diagonals
        if (row == col) { // Check main diagonal
            for (int i = 0; i < size; i++) {
                if (board.getPieceAt(i, i) != piece) {
                    return false;
                }
            }
            return true;
        }
        if (row + col == size - 1) { // Check anti-diagonal
            for (int i = 0; i < size; i++) {
                if (board.getPieceAt(i, size - 1 - i) != piece) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkColumn(int j, PlayingPiece piece) {
        for (int i = 0; i < size; i++) {
            if (board.getPieceAt(i, j) != piece) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRow(int i, PlayingPiece piece) {
        for (int j = 0; j < size; j++) {
            if (board.getPieceAt(i, j) != piece) {
                return false;
            }
        }
        return true;
    }
}
