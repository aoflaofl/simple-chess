package com.spamalot.chess.chess_board;

interface ChessMove {

  void setPiece(ChessPiece p);

  void setFromSquare(int r, int f);

  void setToSquare(int r, int f);

  void setCapturedPieceTo(ChessPiece toPiece);

}
