import javax.swing.*;
import java.util.ArrayList;

/**
 * Class which implements the Piece interface so that you can create a rook chess piece.
 */
public class Rook extends Piece {

    private ArrayList<Square> mUp;
    private ArrayList<Square> mDown;
    private ArrayList<Square> mLeft;
    private ArrayList<Square> mRight;

    /**
     * Creates Rook objects to be used on a chess board.
     * @param position - the position of the rook on the board.
     * @param up - what size of the board the piece is on. True for 'up', false for 'down'.
     * @param icon - the image to be used for the rook in the graphics view.
     */
    public Rook(Square position, boolean up, ImageIcon icon) {
        super(position,up,icon);
        position.setPiece(this);
        initializeMoves();
    }

    /**
     * Initializes all the moves in the rook's repertoire. Since the rook has four possible moves directions, the calculations
     * will be split into eight different lists for easier initialization and eventually calculations.
     */
    public void initializeMoves() {
        mUp = new ArrayList<>();
        for(int y = -1; y >= -7; y--) {
            mUp.add(new Square(0,y));
        }

        mDown = new ArrayList<>();
        for(int y = 1; y <= 7; y++) {
            mDown.add(new Square(0,y));
        }

        mLeft = new ArrayList<>();
        for(int x = -1; x >= -7; x--) {
            mLeft.add(new Square(x,0));
        }

        mRight = new ArrayList<>();
        for(int x = 1; x <= 7; x++) {
            mRight.add(new Square(x,0));
        }
    }

    /**
     * Constructs a list of valid moves. Iterates through each list representing moves of a different direction so that stopping iteration
     * for a specific direction is possible if a move is deemed invalid. If close moves are invalid, then farther moves are also invalid.
     * @param board - the board object which will be analyzed.
     * @return an ArrayList of valid moves.
     */
    public ArrayList<Square> getPossibleMoves(Board board) {
        ArrayList<Square> possibleMoves = new ArrayList<>();
        for(Square move:mUp) {
            Square newPos = new Square(super.getPosition().getX() + move.getX(), super.getPosition().getY() + move.getY());
            if(isOutOfBounds(move)) {
                break;
            }
            else if(board.isOccupied(newPos) && !board.isFoeKing(newPos,super.isUp())) {
                if(board.isFoe(newPos, super.isUp())) {
                    possibleMoves.add(move);
                }
                break;
            }
            else {
                possibleMoves.add(move);
            }
        }

        for(Square move:mDown) {
            Square newPos = new Square(super.getPosition().getX() + move.getX(), super.getPosition().getY() + move.getY());
            if(isOutOfBounds(move)) {
                break;
            }
            else if(board.isOccupied(newPos) && !board.isFoeKing(newPos,super.isUp())) {
                if(board.isFoe(newPos, super.isUp())) {
                    possibleMoves.add(move);
                }
                break;
            }
            else {
                possibleMoves.add(move);
            }
        }

        for(Square move:mLeft) {
            Square newPos = new Square(super.getPosition().getX() + move.getX(), super.getPosition().getY() + move.getY());
            if(isOutOfBounds(move)) {
                break;
            }
            else if(board.isOccupied(newPos) && !board.isFoeKing(newPos,super.isUp())) {
                if(board.isFoe(newPos, super.isUp())) {
                    possibleMoves.add(move);
                }
                break;
            }
            else {
                possibleMoves.add(move);
            }
        }

        for(Square move:mRight) {
            Square newPos = new Square(super.getPosition().getX() + move.getX(), super.getPosition().getY() + move.getY());
            if(isOutOfBounds(move)) {
                break;
            }
            else if(board.isOccupied(newPos) && !board.isFoeKing(newPos,super.isUp())) {
                if(board.isFoe(newPos, super.isUp())) {
                    possibleMoves.add(move);
                }
                break;
            }
            else {
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }
}
