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
    } else if (s == Side.KING) {
      castleBlackKing = true;
    } else {
      castleBlackQueen = true;
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
    for (int rank = 0; rank < 8; rank++) {
      for (int file = 0; file < 8; file++) {
        ChessPiece p = cb.getPieceAt(rank, file);
        if ((p == null) || (p.getColor() != colorToMove)) {
          continue;
        }
        System.out.println(p);

        switch (p.getType()) {
          case BISHOP:
            for (int diffRank = -1; diffRank <= 1; diffRank++) {
              for (int diffFile = -1; diffFile <= 1; diffFile++) {

                if (Math.abs(diffRank) == 1 && Math.abs(diffFile) == 1) {
                  al.addAll(generateListOfSlidingMoves(rank, file, diffRank, diffFile));
                }
              }
            }

            break;
          case KING:

            for (int diffRank = -1; diffRank <= 1; diffRank++) {
              for (int diffFile = -1; diffFile <= 1; diffFile++) {
                if (diffRank == 0 && diffFile == 0) {
                  continue;
                }

                if (theSquareIsOnTheBoard(rank + diffRank, file + diffFile)) {
                  ChessMove m = chessMoveDoSquareMove(rank, file, p, rank + diffRank, file + diffFile,
                      cb.getPieceAt(rank + diffRank, file + diffFile));
                  if (m != null) {
                    al.add(m);
                  }
                }
              }
            }

            break;
          case KNIGHT:
            break;
          case PAWN:
            break;
          case QUEEN:
            for (int diffRank = -1; diffRank <= 1; diffRank++) {
              for (int diffFile = -1; diffFile <= 1; diffFile++) {

                if (diffRank == 0 && diffFile == 0) {
                  continue;
                }

                al.addAll(generateListOfSlidingMoves(rank, file, diffRank, diffFile));

              }
            }

            break;
          case ROOK:
            for (int diffRank = -1; diffRank <= 1; diffRank++) {
              for (int diffFile = -1; diffFile <= 1; diffFile++) {
                if (isOrth(diffRank, diffFile)) {
                  al.addAll(generateListOfSlidingMoves(rank, file, diffRank, diffFile));
                }
              }
            }
            break;
          default:
            break;

        }

      }
    }
    return al;
  }

  private boolean isOrth(int dr, int df) {
    if (dr == 0 && df == 0) {
      return false;
    }
    if (Math.abs(dr) == 1 && Math.abs(df) == 1) {
      return false;
    }
    return true;
  }

  private List<ChessMove> generateListOfSlidingMoves(int rank, int file, int diffRank, int diffFile) {
    ChessPiece p = cb.getPieceAt(rank, file);

    int toRank = rank + diffRank;
    int toFile = file + diffFile;

    List<ChessMove> alOther = new ArrayList<>();

    while (theSquareIsOnTheBoard(toRank, toFile)) {
      ChessPiece toPiece = cb.getPieceAt(toRank, toFile);

      ChessMove move = chessMoveDoSquareMove(rank, file, p, toRank, toFile, toPiece);
      alOther.add(move);

      if (toPiece != null) {
        break;
      }

      toRank = toRank + diffRank;
      toFile = toFile + diffFile;
    }

    return alOther;
  }

  private boolean theSquareIsOnTheBoard(int rank, int file) {
    return ((rank >= 0) && (rank <= 7) && (file >= 0) && (file <= 7));
  }

  private ChessMove chessMoveDoSquareMove(int r, int f, ChessPiece p, int tr, int tf, ChessPiece toPiece) {
    ChessMove move = new ChessMoveImpl();
    move.setPiece(p);
    move.setFromSquare(r, f);
    move.setToSquare(tr, tf);

    if (toPiece != null) {
      if (toPiece.getColor() == colorToMove) {
        return null;
      }
      move.setCapturedPieceTo(toPiece);
    }
    return move;
  }

  @Override
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
