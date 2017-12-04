import javax.swing.*;
import java.util.ArrayList;

/**
 * This class is ideal for pieces of a board game.
 */
public abstract class Piece {

    private Square position;
    private boolean up;
    private ImageIcon icon;

    public Piece(Square position, boolean up, ImageIcon icon) {
        this.position = position;
        this.up = up;
        this.icon = icon;
    }

    /**
     * Gets the position of a piece.
     * @return a Square object which relates to a position on a board grid.
     */
    public Square getPosition() {
        return position;
    }

    /**
     * Updates the position of a piece.
     * @param newPos - the position the piece will be updated to.
     */
    public void updatePos(Square newPos) {
        position.setPiece(null);
        position = newPos;
    }

    /**
     * Get the icon which represents the piece in a graphical application.
     * @return an ImageIcon object.
     */
    public ImageIcon getIcon() {
        return icon;
    }

    public boolean isUp() {
        return up;
    }

    /**
     * Determines if a move in the piece's repertoire is out of bounds and impossible.
     * @param move - the move to analyze.
     * @return a boolean value of whether the move would be out of bounds.
     */
    public boolean isOutOfBounds(Square move) {
        return (position.getX() + move.getX() < Board.LEFT_X_BOUNDARY && position.getX() + move.getX() > Board.RIGHT_X_BOUNDARY
                && position.getY() + move.getY() < Board.UP_Y_BOUNDARY && position.getY() + move.getY() > Board.DOWN_Y_BOUNDARY);
    }

    /**
     * Initializes the move set of the piece;
     */
    public abstract void initializeMoves();

    /**
     * Constructs a list of valid moves. Iterates through the move set and adds it to a list of valid moves.
     * @param board - the board object which will be analyzed.
     * @return an ArrayList of valid moves.
     */
    public abstract ArrayList<Square> getPossibleMoves(Board board);
}
