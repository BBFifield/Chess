import javax.swing.*;
import java.util.ArrayList;

/**
 * Class which implements the Piece interface so that you can create a Knight chess piece.
 */
public class Knight extends Piece {

    private Square[] moves;

    /**
     * Creates Knight objects to be used on a chess board.
     * @param position - the position of the Knight on the board.
     * @param up - what size of the board the piece is on. True for 'up', false for 'down'.
     * @param icon - the image to be used for the knight in the graphics view.
     */
    public Knight(Square position, boolean up, ImageIcon icon) {
        super(position,up,icon);
        position.setPiece(this);
        initializeMoves();
    }

    /**
     * Initializes all the moves in the knight's repertoire. Unlike other pieces you don't need separate lists because the knight only has one possible
     * move in a direction.
     */
    public void initializeMoves() {
        moves = new Square[]{new Square(1,2), new Square(2,1), new Square(-1,-2), new Square(-2,-1),
                new Square(-1,2), new Square(1,-2), new Square(2,-1), new Square(-2,1), new Square(-1,2)};
    }

    /**
     * Constructs a list of valid moves. Iterates through the move set and adds it to a list of valid moves.
     * @param board - the board object which will be analyzed.
     * @return an ArrayList of valid moves.
     */
    public ArrayList<Square> getPossibleMoves(Board board) {
        ArrayList<Square> possibleMoves = new ArrayList<>();
        for(Square move:moves) {
            Square newPos = new Square(super.getPosition().getX() + move.getX(), super.getPosition().getY() + move.getY());
            if(!isOutOfBounds(move)) {
                if (!board.isOccupied(newPos) || board.isFoe(newPos, super.isUp())) {
                    possibleMoves.add(move);
                }
            }
        }
        return possibleMoves;
    }
}
