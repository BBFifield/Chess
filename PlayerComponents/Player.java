import java.util.ArrayList;

/**
 * Created by Brandon on 2017-03-04.
 */
public class Player {

    private String name;
    private ArrayList<Piece> pieces;
    private Piece king;
    private boolean turn;
    private boolean check;
    private boolean checkMate;

    public Player(ArrayList<Piece> pieces, Piece king) {
        this.pieces = pieces;
        this.king = king;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public Piece getKing() {
        return king;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public void setCheckMate(boolean checkMate) {
        this.checkMate = checkMate;
    }

    public void remove(Piece beRemoved) {
        pieces.remove(beRemoved);
    }
}
