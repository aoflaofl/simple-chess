package com.spamalot.chess.chess_board;

import com.spamalot.chess.chess_board.ChessBoard.Side;
import com.spamalot.chess.chess_board.ChessPiece.Color;
import java.util.*;

class ChessPositionImpl implements ChessPosition {
  private final ChessBoard cb;
  private Color colorToMove;
  private boolean castleWhiteKing;
  private boolean castleWhiteQueen;
  private boolean castleBlackKing;
  private boolean castleBlackQueen;

  public ChessPositionImpl(String fenString) {
    FEN f = FEN.parseFEN(fenString);

    cb = new ChessBoardImpl(f);
    parseColorToMove(f.colorToMove());

    for (char ddddd : f.castling().toCharArray()) {

      switch (ddddd) {

        case 'K':
          setCastling(Color.WHITE, Side.KING);
          break;
        case 'k':
          setCastling(Color.BLACK, Side.KING);
          break;
        case 'Q':
          setCastling(Color.WHITE, Side.QUEEN);
          break;
        case 'q':
          setCastling(Color.BLACK, Side.QUEEN);
          break;
        default:
          break;
      }
    }
  }

  private void parseColorToMove(String f) {
    if (f.equals("w")) {
      setColorToMove(Color.WHITE);
    } else {
      setColorToMove(Color.BLACK);
    }
  }

  private void setColorToMove(Color c) {
    colorToMove = c;
  }

  private Color getColorToMove() {
    return colorToMove;
  }

  private void setCastling(Color c, Side s) {
    if (c == Color.WHITE) {
      if (s == Side.KING) {
        castleWhiteKing = true;
      } else {
        castleWhiteQueen = true;
      }
    } else {
      if (s == Side.KING) {
        castleBlackKing = true;
      } else {
        castleBlackQueen = true;
      }
    }
  }

  @Override
  public void setEnPassantSquare(int rank, int file) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setHalfMoveClock(int c) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setFullMoveNumber(int n) {
    // TODO Auto-generated method stub

  }

  @Override
  public ChessPiece getPiece(int rank, int file) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ChessMove> generateMoveList() {
    List<ChessMove> al = new ArrayList<>();
    for (int r = 0; r < 8; r++) {
      for (int f = 0; f < 8; f++) {
        ChessPiece p = cb.getPieceAt(r, f);
        if (p == null) {
          continue;
        }
        if (p.getColor() != colorToMove) {
          continue;
        }
        System.out.println(p);
        ChessMove m = new ChessMoveImpl();
        m.setPiece(p);
        m.setFromSquare(r, f);
        m.setToSquare(r, f);
        al.add(m);
      }
    }
    return al;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(cb);
    sb.append("Color to move: ");
    sb.append(getColorToMove());
    sb.append('\n');
    sb.append("castleWhiteKing: ");
    sb.append(castleWhiteKing);
    sb.append("\ncastleWhiteQueen: ");
    sb.append(castleWhiteQueen);
    sb.append("\ncastleBlackKing: ");
    sb.append(castleBlackKing);
    sb.append("\ncastleBlackQueen: ");
    sb.append(castleBlackQueen);
    return sb.toString();
  }

}
