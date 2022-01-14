package com.spamalot.chess.chess_board;

record FEN(String[] pos, String colorToMove, String castling, String enPassant, String halfMoveSincePawnMove,
    String fullMove) {

  static FEN parseFEN(String fenString) {
    String[] fenPart = fenString.split(" ");

    if (fenPart.length != 6) {
      throw new IllegalArgumentException("FEN String does not have correct amount of fields.");
    }

    String[] fenBoardPart = fenPart[0].split("/");
    if (fenBoardPart.length != 8) {
      throw new IllegalArgumentException("FEN Board string has wrong number of fields.");
    }

    return new FEN(fenBoardPart, fenPart[1], fenPart[2], fenPart[3], fenPart[4], fenPart[5]);
  }
}
