package com.spamalot.chess.chess_board;

interface ChessBoard {
  public enum Side {
    KING,
    QUEEN
  }

  public ChessPiece getPieceAt(int r, int f);
}
