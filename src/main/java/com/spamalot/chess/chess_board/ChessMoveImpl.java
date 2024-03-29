package com.spamalot.chess.chess_board;

public class ChessMoveImpl implements ChessMove {
  ChessPiece piece;
  int fromRank;
  int fromFile;
  int toRank;
  int toFile;
  private ChessPiece capturedPiece;

  @Override
  public void setPiece(ChessPiece p) {
    piece = p;
  }

  @Override
  public void setFromSquare(int r, int f) {
    fromRank = r;
    fromFile = f;
  }

  @Override
  public void setToSquare(int r, int f) {
    toRank = r;
    toFile = f;
  }

  public String toString() {

    StringBuilder sb = new StringBuilder();

    sb.append(piece.toString());

    sb.append((char) (fromFile + 'a'));
    sb.append(fromRank + 1);
    if (capturedPiece != null) {
      sb.append('x');
    } else {
      sb.append('-');
    }
    sb.append((char) (toFile + 'a'));
    sb.append(toRank + 1);

    return sb.toString();
  }

  @Override
  public void setCapturedPieceTo(ChessPiece toPiece) {
    capturedPiece = toPiece;
  }
}
