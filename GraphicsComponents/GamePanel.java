import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Creates a Game Panel to hold all grid panel components.
 */
public class GamePanel extends JPanel {

    private Board board;
    private ArrayList<Piece> upperPieces;
    private ArrayList<Piece> lowerPieces;
    private GridPanel[][] panelArray;
    private ArrayList<GridPanel> selectablePanels;
    private Object[] options;

    /**
     * Constructs a new GamePanel object and initializes a new game board.
     */
    public GamePanel() {
        board = new Board();
        initialize();
    }

    /**
     * Initializes all grid panel graphics components and add them to an array so computations can be done on them by iteration.
     */
    public void initialize() {
        selectablePanels = new ArrayList<>();
        upperPieces = board.getUpperPlayer().getPieces();
        lowerPieces = board.getLowerPlayer().getPieces();
        options = new Object[]{"Reset","Quit"};
        setSize(new Dimension(520,520));
        setBackground(Color.BLACK);
        setLayout(new GridLayout(8,8,2,2));

        Square[][] gridArray = board.getGrid();
        panelArray = new GridPanel[8][8];
        boolean isDark = false;

        for(int i = 0; i < gridArray.length; i++) {
            if(isDark) { isDark = false; }
            else { isDark = true; }
            for(int j = 0; j < gridArray[i].length; j++) {
                GridPanel panelSquare = new GridPanel(gridArray[j][i],this);
                if(isDark) {
                    panelSquare.setDark();
                    isDark = false;
                }
                else {
                    panelSquare.setLight();
                    isDark = true;
                }
                panelArray[gridArray[j][i].getX()][gridArray[j][i].getY()] = panelSquare;
                add(panelSquare);
            }
        }

        //Adds each piece's image to the grid panel at its location.
        for(Piece piece: upperPieces) {
            JLabel imageLabel = new JLabel(piece.getIcon());
            panelArray[piece.getPosition().getX()][piece.getPosition().getY()].add(imageLabel);
            panelArray[piece.getPosition().getX()][piece.getPosition().getY()].storeLabel(imageLabel);
        }
        for(Piece piece:lowerPieces) {
            JLabel imageLabel = new JLabel(piece.getIcon());
            panelArray[piece.getPosition().getX()][piece.getPosition().getY()].add(imageLabel);
            panelArray[piece.getPosition().getX()][piece.getPosition().getY()].storeLabel(imageLabel);
        }
    }

    /**
     * Highlights the selectable moves in the graphics components for this user turn.
     * @param panel - the panel at the Square which holds the piece to get the selectable moves from.
     */
    public void highlightMoves(GridPanel panel) {
        ArrayList<Square> selectableSquares;
        if(board.getUpperPlayer().isCheck() || board.getLowerPlayer().isCheck()) {
            selectableSquares = board.getCheckSquares();
        }
        else {
            selectableSquares = board.processSelectables(panel.getPosition());
        }
        for(Square square: selectableSquares) {
            panelArray[square.getX()][square.getY()].setSelected();
            selectablePanels.add(panelArray[square.getX()][square.getY()]);
        }
    }

    /**
     * Gets the selectable graphics representation of selectableSquares for this user turn.
     * @return an ArrayList contains all selectable panels of the current user turn.
     */
    public ArrayList<GridPanel> getSelectablePanels() {
        return selectablePanels;
    }

    /**
     *
     */
    public void resetSelectables() {
        for(GridPanel selectablePanel: selectablePanels) {
            selectablePanel.setUnselected();
            selectablePanel.setUnselected();
        }
        selectablePanels.clear();
        board.getSelectables().clear();
    }

    /**
     * Gets the grid panel at this Square position.
     * @param pos - the position of a grid panel.
     * @return the GridPanel object associated with this square position.
     */
    public GridPanel getPanel(Square pos) {
        return panelArray[pos.getX()][pos.getY()];
    }

    /**
     * Update the Graphics of the game for this move.
     * @param fromPanel - the panel the move originated.
     * @param toPanel - the panel the move is destined.
     */
    public void update(GridPanel fromPanel, GridPanel toPanel) {
        JLabel imageLabel = new JLabel(fromPanel.getPosition().getPiece().getIcon());
        if(toPanel.getPosition().hasPiece()) {
            //Remove image from toPanel.
            toPanel.removeLabel();
        }
        //Transfer user mouse input to board logic and move the pieces internal coordinates.
        board.move(fromPanel.getPosition().getPiece(), toPanel.getPosition());
        toPanel.add(imageLabel);
        toPanel.storeLabel(imageLabel);
        fromPanel.removeLabel();
        fromPanel.updateUI();
        toPanel.updateUI();
        //Clear selectableSquares and selectablePanels for future use.
        resetSelectables();
        if(board.getUpperPlayer().isCheckMate()) {
            showEndDialog();
        }
        else if(board.getLowerPlayer().isCheckMate()) {
            showEndDialog();
        }
    }

    /**
     * Get the board the program is using.
     * @return the Board object the graphics shell is using for its logic.
     */
    public Board getBoard() {
        return board;
    }

    public void showEndDialog() {
        JOptionPane.showMessageDialog(null,"White Player Wins!");
        int choice = JOptionPane.showOptionDialog(null,"Click \" Reset \" to play again or \" Quit \" to stop.","Play Again?",
                JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
        if(choice == JOptionPane.YES_OPTION) {
            board.reset();
            reset();
        }
        else if(choice == JOptionPane.NO_OPTION) {
            System.exit(0);
            updateUI();
        }
    }

    public void reset() {
        removeAll();
        initialize();
        updateUI();
    }
}
