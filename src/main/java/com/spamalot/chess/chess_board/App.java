package com.spamalot.chess.chess_board;

import java.util.List;

public class App {
  public static void main(String[] args) {
    ChessPosition pos = new ChessPositionImpl("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    System.out.println(pos.toString());
    List<ChessMove> cml = pos.generateMoveList();
    for (ChessMove move : cml) {
      System.out.println(move);
    }

    pos = new ChessPositionImpl("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
    System.out.println(pos.toString());

    pos = new ChessPositionImpl("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
    System.out.println(pos.toString());

    pos = new ChessPositionImpl("8/k7/1pK5/8/8/8/5R2/8 w - - 0 1");
    System.out.println(pos.toString());
    cml = pos.generateMoveList();
    for (ChessMove move : cml) {
      System.out.println(move);
    }

    pos = new ChessPositionImpl("8/8/6pp/6pk/7P/6K1/7P/8 w - - 0 1"
    // 1. h3 gxh4+ 2. Kf4 g5+ 3. Kf5 g4 4. hxg4#
    );

    System.out.println(pos.toString());
    cml = pos.generateMoveList();
    for (ChessMove move : cml) {
      System.out.println(move);
    }

  }
}
