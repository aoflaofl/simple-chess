package com.spamalot.chess.chess_board;

import java.util.List;

interface ChessPosition {

  void setEnPassantSquare(int rank, int file);

  void setHalfMoveClock(int c);

  void setFullMoveNumber(int n);

  ChessPiece getPiece(int rank, int file);

  List<ChessMove> generateMoveList();
}
