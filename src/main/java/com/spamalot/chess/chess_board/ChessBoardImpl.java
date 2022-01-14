package com.spamalot.chess.chess_board;

class ChessBoardImpl implements ChessBoard {
  private final ChessPiece[][] board = new ChessPieceImpl[8][8];

  public ChessBoardImpl(FEN f) {
    int row = 7;
    for (String rowString : f.pos()) {
      board[row] = parseRowString(rowString);
      row--;
    }
  }

  private ChessPiece[] parseRowString(String rowString) {
    ChessPiece[] row = new ChessPieceImpl[8];
    int file = 0;
    for (char d : rowString.toCharArray()) {
      if (Character.isDigit(d)) {
        file = file + d - '0';
      } else {
        row[file] = new ChessPieceImpl(d);
        file++;
      }
    }
    return row;
  }

  public String toString() {

    StringBuilder sb = new StringBuilder();
    for (int r = 7; r >= 0; r--) {
      for (int f = 0; f < 8; f++) {
        if (board[r][f] != null) {
          sb.append(board[r][f]);
        } else {
          sb.append('.');
        }
      }
      sb.append('\n');
    }

    return sb.toString();
  }

  @Override
  public ChessPiece getPieceAt(int r, int f) {
    return board[r][f];
  }
}
