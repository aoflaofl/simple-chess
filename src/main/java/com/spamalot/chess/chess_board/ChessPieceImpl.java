package com.spamalot.chess.chess_board;

class ChessPieceImpl implements ChessPiece {

  private ChessPiece.Type type;
  private final ChessPiece.Color color;

  public ChessPieceImpl(char d) {
    if (Character.isLowerCase(d)) {
      color = ChessPiece.Color.BLACK;
    } else {
      color = ChessPiece.Color.WHITE;
    }
    switch (d) {
      case 'r', 'R':
        type = ChessPiece.Type.ROOK;
        break;
      case 'b', 'B':
        type = ChessPiece.Type.BISHOP;
        break;
      case 'n', 'N':
        type = ChessPiece.Type.KNIGHT;
        break;
      case 'q', 'Q':
        type = ChessPiece.Type.QUEEN;
        break;
      case 'k', 'K':
        type = ChessPiece.Type.KING;
        break;
      case 'p', 'P':
        type = ChessPiece.Type.PAWN;
        break;
      default:
    }
  }

  public String toString() {
    if (color == ChessPiece.Color.BLACK) {
      return type.blackString();
    } else {
      return type.whiteString();
    }
  }
}
