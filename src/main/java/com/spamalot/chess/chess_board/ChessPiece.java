package com.spamalot.chess.chess_board;

interface ChessPiece {

  public enum Type {
    ROOK('r', 'R'),
    BISHOP('b', 'B'),
    KNIGHT('n', 'N'),
    QUEEN('q', 'Q'),
    KING('k', 'K'),
    PAWN('p', 'P');

    final String b;
    final String w;

    Type(char blackChar, char whiteChar) {
      b = blackChar + "";
      w = whiteChar + "";
    }

    String blackString() {
      return b;
    }

    String whiteString() {
      return w;
    }
  }

  public enum Color {
    WHITE,
    BLACK
  }

  public Color getColor();

}
