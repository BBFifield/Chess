import javax.swing.*;
import java.util.ArrayList;

/**
 * Class which implements the Piece interface so that you can create a Queen chess piece.
 */
public class Queen extends Piece {

    private ArrayList<Square> mLowerRight;
    private ArrayList<Square> mLowerLeft;
    private ArrayList<Square> mUpperLeft;
    private ArrayList<Square> mUpperRight;
    private ArrayList<Square> mUp;
    private ArrayList<Square> mDown;
    private ArrayList<Square> mLeft;
    private ArrayList<Square> mRight;

    /**
     * Creates Queen objects to be used on a chess board.
     * @param position - the position of the queen on the board.
     * @param up - what size of the board the piece is on. True for 'up', false for 'down'.
     * @param icon - the image to be used for the queen in the graphics view.
     */
    public Queen(Square position, boolean up, ImageIcon icon) {
        super(position,up,icon);
        position.setPiece(this);
        initializeMoves();
    }

    /**
     * Initializes all the moves in the queen's repertoire. Since the queen has eight possible moves directions, the calculations
     * will be split into eight different lists for easier initialization and eventually calculations.
     */
    public void initializeMoves() {
        mLowerRight = new ArrayList<>();
        int y = 1;
        for(int x = 1; x <= 7; x++) {
            mLowerRight.add(new Square(x,y));
            y++;
        }

        mLowerLeft = new ArrayList<>();
        y = 1;
        for(int x = -1; x >= -7; x--) {
            mLowerLeft.add(new Square(x,y));
            y++;
        }

        mUpperLeft = new ArrayList<>();
        y = -1;
        for(int x = -1; x >= -7; x--) {
            mUpperLeft.add(new Square(x,y));
            y--;
        }

        mUpperRight = new ArrayList<>();
        y = -1;
        for(int x = 1; x <= 7; x++) {
            mUpperRight.add(new Square(x,y));
            y--;
        }

        mUp = new ArrayList<>();
        for(int yy = -1; yy >= -7; yy--) {
            mUp.add(new Square(0,yy));
        }

        mDown = new ArrayList<>();
        for(int yy = 1; yy <= 7; yy++) {
            mDown.add(new Square(0,yy));
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
        for(Square move:mLowerRight) {
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

        for(Square move:mLowerLeft) {
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

        for(Square move:mUpperRight) {
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

        for(Square move:mUpperLeft) {
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
