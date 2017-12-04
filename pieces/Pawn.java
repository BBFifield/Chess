import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class which implements the Piece interface so that you can create a Pawn chess piece.
 */
public class Pawn extends Piece {

    private ArrayList<Square> forwardMoves;
    private ArrayList<Square> diagonalMoves;
    private boolean start;

    /**
     * Creates Pawn objects to be used on a chess board.
     * @param position - the position of the pawn on the board.
     * @param up - what size of the board the piece is on. True for 'up', false for 'down'.
     * @param icon - the image to be used for the pawn in the graphics view.
     */
    public Pawn(Square position, boolean up, ImageIcon icon) {
        super(position,up,icon);
        //Ties the pawn to its Square object for easier access.
        position.setPiece(this);
        start = true;
        initializeMoves();
    }

    /**
     * Updates the position of a piece.
     * @param newPos - the position the piece will be updated to.
     */
    public void updatePos(Square newPos) {
        super.getPosition().setPiece(null);
        super.updatePos(newPos);
        start = false;
    }

    /**
     * Initializes the move set of the pawn based on whether it is located in the upper or lower half and at the start.
     */
    public void initializeMoves() {
        //Initializes the move set of a pawn located in the upper side of the board, at the start the pawn will be able to move one additional square forwards.
        if (super.isUp()) {
            if (!start) {
                forwardMoves = new ArrayList<>(Arrays.asList(new Square(0, 1)));
            } else {
                forwardMoves = new ArrayList<>(Arrays.asList(new Square(0, 1), new Square(0, 2)));
            }
            diagonalMoves = new ArrayList<>(Arrays.asList(new Square(1, 1), new Square(-1, 1)));
        }
        //Negative values denote x-values  of moves which takes a pawn to the left, or up in the case of y-values.
        else {
            if (!start) {
                forwardMoves = new ArrayList<>(Arrays.asList(new Square(0, -1)));
            } else {
                forwardMoves = new ArrayList<>(Arrays.asList(new Square(0, -1), new Square(0, -2)));
            }
            diagonalMoves = new ArrayList<>(Arrays.asList(new Square(1, -1), new Square(-1, -1)));
        }
    }

    /**
     * Constructs a list of valid moves.
     * @param board - the board object which will be analyzed.
     * @return an ArrayList of valid moves.
     */
    public ArrayList<Square> getPossibleMoves(Board board) {
        ArrayList<Square> possibleMoves = new ArrayList<>();
        //Since the forward moves of a pawn varies depending on whether it is at its initial position, we will initialize its move set every time this method is called.
        initializeMoves();
        //The most set is split because a forward move which lands on a square which has a 'Foe' piece is not valid for a pawn.
        for(Square move:forwardMoves) {
            Square newPos = new Square(super.getPosition().getX() + move.getX(), super.getPosition().getY() + move.getY());
            //Will break the loop iterating through the moves if the it lands on an occupied square, this is because the loop starts at the
            //closest move and if it is invalid then its farther moves cannot be valid. As per the rules of chess.
            if(board.isOccupied(newPos)) { break; }
            if(!isOutOfBounds(move)) {
                possibleMoves.add(move);
            }
        }
        for(Square move:diagonalMoves) {
            Square newPos = new Square(super.getPosition().getX() + move.getX(), super.getPosition().getY() + move.getY());
            //As per the rules of chess, a pawn's diagonal move is valid if the move lands on an foe's square.
            if(!isOutOfBounds(move) && board.isFoe(newPos, super.isUp())) {
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }
}
