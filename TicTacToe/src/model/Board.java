package model;

import model.piece.PlayingPiece;

public class Board {
    private PlayingPiece[][] board;
    private int filledTillNow;

    public Board(int size) {
        board = new PlayingPiece[size][size];
        filledTillNow = 0;
    }

    public boolean putPieceAt(int row, int col, PlayingPiece piece) {
        if (board[row][col] != null) {
            return false; // Position already occupied
        }
        board[row][col] = piece;
        filledTillNow++;
        return true; // Piece placed successfully
    }

    public PlayingPiece getPieceAt(int row, int col) {
        return board[row][col];
    }

    public boolean isFull() {
        return filledTillNow == board.length * board.length;
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    str += " . "; // Empty cell
                } else {
                    str += " " + board[i][j].toString() + " "; // Piece representation
                }
            }
            str += "\n";
        }
        return str;
    }
}
