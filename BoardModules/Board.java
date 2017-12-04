import javax.swing.*;
import java.util.ArrayList;

/**
 * Board class which creates a chess board.
 */
public class Board {

    private Player upperPlayer;
    private Player lowerPlayer;
    private ArrayList<Square> selectables;
    private ArrayList<Square> checkSquares;
    private Square[][] grid;

    //Constants for the boundaries of the board.
    public static final int RIGHT_X_BOUNDARY = 7;
    public static final int DOWN_Y_BOUNDARY = 7;
    public static final int LEFT_X_BOUNDARY = 0;
    public static final int UP_Y_BOUNDARY = 0;

    /**
     * Constructs a board object and initializes all its components.
     */
    public Board() {
        initialize();
    }

    /**
     * Gets the player in the upper half of the board.
     * @return a Player object representing the upper player.
     */
    public Player getUpperPlayer() {
        return upperPlayer;
    }

    /**
     * Gets the player in the lower half of the board.
     * @return a Player object representing the lower player.
     */
    public Player getLowerPlayer() {
        return lowerPlayer;
    }

    /**
     * Initializes an 8 by 8 grid of the board with Square objects representing each block.
     */
    public void initializeGrid() {
        grid = new Square[8][8];
        selectables = new ArrayList<>();
        checkSquares = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new Square(i,j);
            }
        }
    }

    /**
     * Initializes the pieces of each player.
     */
    public void initialize() {
        initializeGrid();
        ArrayList<Piece> upperPieces = new ArrayList<>();
        //Initialize all the pawns for player in upper half.
        Pawn uPawnOne = new Pawn(grid[0][1], true, new ImageIcon("pieces/pieceIcons/darkPawn.png"));
        Pawn uPawnTwo = new Pawn(grid[1][1], true, new ImageIcon("pieces/pieceIcons/darkPawn.png"));
        Pawn uPawnThree = new Pawn(grid[2][1], true, new ImageIcon("pieces/pieceIcons/darkPawn.png"));
        Pawn uPawnFour = new Pawn(grid[3][1], true, new ImageIcon("pieces/pieceIcons/darkPawn.png"));
        Pawn uPawnFive = new Pawn(grid[4][1], true, new ImageIcon("pieces/pieceIcons/darkPawn.png"));
        Pawn uPawnSix = new Pawn(grid[5][1], true, new ImageIcon("pieces/pieceIcons/darkPawn.png"));
        Pawn uPawnSeven = new Pawn(grid[6][1], true, new ImageIcon("pieces/pieceIcons/darkPawn.png"));
        Pawn uPawnEight = new Pawn(grid[7][1], true, new ImageIcon("pieces/pieceIcons/darkPawn.png"));
        //Initialize the knights for player in upper half
        Knight uKnightOne = new Knight(grid[1][0], true, new ImageIcon("pieces/pieceIcons/darkKnight.png"));
        Knight uKnightTwo = new Knight(grid[6][0], true, new ImageIcon("pieces/pieceIcons/darkKnight.png"));
        //Initialize the bishops for player in upper half
        Bishop uBishopOne = new Bishop(grid[2][0], true, new ImageIcon("pieces/pieceIcons/darkBishop.png"));
        Bishop uBishopTwo = new Bishop(grid[5][0], true, new ImageIcon("pieces/pieceIcons/darkBishop.png"));
        //Initialize the rooks for player in upper half
        Rook uRookOne = new Rook(grid[0][0], true, new ImageIcon("pieces/pieceIcons/darkRook.png"));
        Rook uRookTwo = new Rook(grid[7][0], true, new ImageIcon("pieces/pieceIcons/darkRook.png"));
        //Initialize the Queen for the player in the upper half
        Queen uQueen = new Queen(grid[4][0], true, new ImageIcon("pieces/pieceIcons/darkQueen.png"));
        //Initialize the King for the player in the upper half
        King uKing = new King(grid[3][0], true, new ImageIcon("pieces/pieceIcons/darkKing.png"));

        //Add them to list and initialize upper player objects
        upperPieces.add(uPawnOne);
        upperPieces.add(uPawnTwo);
        upperPieces.add(uPawnThree);
        upperPieces.add(uPawnFour);
        upperPieces.add(uPawnFive);
        upperPieces.add(uPawnSix);
        upperPieces.add(uPawnSeven);
        upperPieces.add(uPawnEight);
        upperPieces.add(uKnightOne);
        upperPieces.add(uKnightTwo);
        upperPieces.add(uBishopOne);
        upperPieces.add(uBishopTwo);
        upperPieces.add(uRookOne);
        upperPieces.add(uRookTwo);
        upperPieces.add(uQueen);
        upperPieces.add(uKing);

        upperPlayer = new Player(upperPieces, uKing);

        ArrayList<Piece> lowerPieces = new ArrayList<>();
        //Initialize all the pawns for player in lower half.
        Pawn lPawnOne = new Pawn(grid[0][6], false, new ImageIcon("pieces/pieceIcons/lightPawn.png"));
        Pawn lPawnTwo = new Pawn(grid[1][6], false, new ImageIcon("pieces/pieceIcons/lightPawn.png"));
        Pawn lPawnThree = new Pawn(grid[2][6], false, new ImageIcon("pieces/pieceIcons/lightPawn.png"));
        Pawn lPawnFour = new Pawn(grid[3][6], false, new ImageIcon("pieces/pieceIcons/lightPawn.png"));
        Pawn lPawnFive = new Pawn(grid[4][6], false, new ImageIcon("pieces/pieceIcons/lightPawn.png"));
        Pawn lPawnSix = new Pawn(grid[5][6], false, new ImageIcon("pieces/pieceIcons/lightPawn.png"));
        Pawn lPawnSeven = new Pawn(grid[6][6], false, new ImageIcon("pieces/pieceIcons/lightPawn.png"));
        Pawn lPawnEight = new Pawn(grid[7][6], false, new ImageIcon("pieces/pieceIcons/lightPawn.png"));
        //Initialize all the knights for player in lower half
        Knight lKnightOne = new Knight(grid[1][7], false, new ImageIcon("pieces/pieceIcons/lightKnight.png"));
        Knight lKnightTwo = new Knight(grid[6][7], false, new ImageIcon("pieces/pieceIcons/lightKnight.png"));
        //Initialize the bishops for player in lower half
        Bishop lBishopOne = new Bishop(grid[2][7], false, new ImageIcon("pieces/pieceIcons/lightBishop.png"));
        Bishop lBishopTwo = new Bishop(grid[5][7], false, new ImageIcon("pieces/pieceIcons/lightBishop.png"));
        //Initialize the rooks for player in lower half
        Rook lRookOne = new Rook(grid[0][7], false, new ImageIcon("pieces/pieceIcons/lightRook.png"));
        Rook lRookTwo = new Rook(grid[7][7], false, new ImageIcon("pieces/pieceIcons/lightRook.png"));
        //Initialize the Queen for the player in the lower half
        Queen lQueen = new Queen(grid[4][7], false, new ImageIcon("pieces/pieceIcons/lightQueen.png"));
        //Initialize the King for the player in the lower half
        King lKing = new King(grid[3][7], false, new ImageIcon("pieces/pieceIcons/lightKing.png"));

        //Add them to list and initialize upper player objects
        lowerPieces.add(lPawnOne);
        lowerPieces.add(lPawnTwo);
        lowerPieces.add(lPawnThree);
        lowerPieces.add(lPawnFour);
        lowerPieces.add(lPawnFive);
        lowerPieces.add(lPawnSix);
        lowerPieces.add(lPawnSeven);
        lowerPieces.add(lPawnEight);
        lowerPieces.add(lKnightOne);
        lowerPieces.add(lKnightTwo);
        lowerPieces.add(lBishopOne);
        lowerPieces.add(lBishopTwo);
        lowerPieces.add(lRookOne);
        lowerPieces.add(lRookTwo);
        lowerPieces.add(lQueen);
        lowerPieces.add(lKing);

        lowerPlayer = new Player(lowerPieces, lKing);
        lowerPlayer.setTurn(true);
    }

    /**
     * Gets the gridlayout of the board.
     * @return a 2D array representation of the board.
     */
    public Square[][] getGrid() {
        return grid;
    }

    /**
     * Iterates through all the moves of the piece occupying this square and finds all valid squares a piece can land for this user turn.
     * @param square - the square which was selected and occupying a piece.
     * @return an ArrayList which contains all the squares a player can select a piece to move to.
     */
    public ArrayList<Square> processSelectables(Square square) {
        ArrayList<Square> moves = square.getPiece().getPossibleMoves(this);
        for (Square move : moves) {
            for (Square gridCols[] : grid) {
                for (Square gridRow : gridCols) {
                    if (square.getY() + move.getY() == gridRow.getY() && square.getX() + move.getX() == gridRow.getX()) {
                        selectables.add(gridRow);
                    }
                }
            }
        }
        return selectables;
    }

    public  ArrayList<Square> getSelectables() {
        return selectables;
    }

    public void computeCheckSquares() {
        ArrayList<Square> allFoeSelectables = new ArrayList<>();
        if(upperPlayer.isTurn()) {
            checkSquares = (ArrayList<Square>) processSelectables(upperPlayer.getKing().getPosition()).clone();
            selectables.clear();
            for(Piece piece:lowerPlayer.getPieces()) {
                allFoeSelectables.addAll(processSelectables(piece.getPosition()));
                selectables.clear();
            }
            for(Square pos: allFoeSelectables) {
                if(checkSquares.contains(pos)) {
                    checkSquares.remove(pos);
                }
            }
        }
        else {
            checkSquares = (ArrayList<Square>) processSelectables(lowerPlayer.getKing().getPosition()).clone();
            selectables.clear();
            for(Piece piece:upperPlayer.getPieces()) {
                allFoeSelectables.addAll(processSelectables(piece.getPosition()));
                selectables.clear();
            }
            for(Square pos: allFoeSelectables) {
                if(checkSquares.contains(pos)) {
                    checkSquares.remove(pos);
                }
            }
        }

    }

    public ArrayList<Square> getCheckSquares() {
        return checkSquares;
    }

    public void checkForCheck() {
        ArrayList<Square> allSelectables = new ArrayList<>();
        if(upperPlayer.isTurn()) {
            for(Piece piece:lowerPlayer.getPieces()) {
                if(processSelectables(piece.getPosition()).contains(upperPlayer.getKing().getPosition())) {
                    upperPlayer.setCheck(true);
                }
                allSelectables.addAll(selectables);
                selectables.clear();
            }
        }
        else {
            for(Piece piece:upperPlayer.getPieces()) {
                if(processSelectables(piece.getPosition()).contains(lowerPlayer.getKing().getPosition())) {
                    lowerPlayer.setCheck(true);
                }
                allSelectables.addAll(selectables);
                selectables.clear();
            }
        }
        computeCheckSquares();
        if(checkSquares.size() == 0 && upperPlayer.isCheck()) {
            upperPlayer.setCheckMate(true);
        }
        else if(checkSquares.size() == 0 && lowerPlayer.isCheck()) {
            lowerPlayer.setCheckMate(true);
        }
    }

    /**
     * Moves a piece to a new position on the board.
     * @param piece - the piece to be moved.
     * @param newPos - the new position it will be moved to.
     */
    public void move(Piece piece, Square newPos) {
        if(newPos.hasPiece()) {
            if(upperPlayer.isTurn()) {
                lowerPlayer.remove(newPos.getPiece());
            }
            else {
                upperPlayer.remove(newPos.getPiece());
            }
        }
        switchStates();
        //Sets the piece to occupy the new position.
        newPos.setPiece(piece);
        piece.getPosition().setPiece(null);
        piece.updatePos(newPos);
        checkSquares.clear();
        checkForCheck();
        //Prepare the selectables list to be used again in the future.
        selectables.clear();
    }

    public void switchStates() {
        //Resets the check state for any states that were set as true because if the
        // process got this far the we know that the player under check got out of check.
        if(upperPlayer.isTurn()) {
            upperPlayer.setCheck(false);
            upperPlayer.setTurn(false);
            lowerPlayer.setTurn(true);
        }
        else {
            lowerPlayer.setCheck(false);
            lowerPlayer.setTurn(false);
            upperPlayer.setTurn(true);
        }
    }

    public void reset() {
        initialize();
    }

    /**
     * Checks to see if the square the mouse clicked is part of a piece's move set.
     * @param selection - the selection the mouse made.
     * @return a boolean value denoting which it is a move. (True being yes).
     */
    public boolean isMoveSelection(Square selection) {
        if(selectables.size() != 0) {
            if(selectables.contains(selection)) {
                return true;
            }
            return false;
        }
        else {
            return false;
        }
    }

    /**
     * Checks to see if a square holds a piece.
     * @param s - the square to be checked.
     * @return a boolean value denoting whether a Square object holds a chess piece. (True being yes.)
     */
    public boolean isOccupied(Square s) {
        for (Piece piece : upperPlayer.getPieces()) {
            if (s.equals(piece.getPosition())) {
                return true;
            }
        }
        for (Piece piece : lowerPlayer.getPieces()) {
            if (s.equals(piece.getPosition())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to if a square holds a foe chess piece.
     * @param s - the square object to be checked.
     * @param isUpper - a boolean value denoting if foe means up or down. (True being up).
     * @return a boolean value denoting if the square holds a foe piece. (True if yes).
     */
    public boolean isFoe(Square s, boolean isUpper) {
        if(isUpper) {
            for (Piece piece : lowerPlayer.getPieces()) {
                if (s.equals(piece.getPosition())) {
                    return true;
                }
            }
        }
        else {
            for (Piece piece : upperPlayer.getPieces()) {
                if (s.equals(piece.getPosition())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFoeKing(Square s, boolean isUpper) {
        if(isUpper && s.equals(lowerPlayer.getKing().getPosition())) {
            return true;
        }
        else if(!isUpper && s.equals(upperPlayer.getKing().getPosition())) {
            return true;
        }
        return false;
    }
}
