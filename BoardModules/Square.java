/**
 * Creates a Square object with holds coordinates and a piece on a game board.
 */
public class Square {

    private int x;
    private int y;
    private Piece piece;

    /**
     * Constructs a square object holding coordinates in a 2D (x,y) plane.
     * @param x - the x-coordinate.
     * @param y - the y-coordinate
     */
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the piece tied to this square coordinate.
     * @param piece - the piece on this square.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Gets the piece on this square if there is one.
     * @return a Piece object on this square.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Removes the piece on the square by setting the object variable to null.
     */
    public void removePiece() {
        this.piece = null;
    }

    /**
     * Checks if the square has a piece on it.
     * @return a boolean value denoting whether or not the square has a piece on it or not. True means yes, false means no.
     */
    public boolean hasPiece() {
        return piece != null;
    }

    /**
     * Determines if a coordinate of one square is equal to this square object.
     * @param s
     * @return
     */
    public boolean equals(Square s) {
        return s.getX() == x && s.getY() == y;
    }

    /**
     * Gets the x-coordinate.
     * @return the x-coordinate.
     */
    public Integer getX() {
        return x;
    }

    /**
     * Gets the y-coordinate.
     * @return an integer representing the y-coordinate
     */
    public Integer getY() {
        return y;
    }
}
