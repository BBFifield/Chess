import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class which can create a GridPanel to build a graphical representation of a board grid.
 */
public class GridPanel extends JPanel{

    private JLabel storedLabel;
    private GamePanel gamePanel;
    private Square position;

    private boolean selected;
    private boolean pressed;
    private boolean dark;

    private Color lightColor;
    private Color darkColor;
    private Color highlightedColor;
    private Color pressedColor;
    private Color selectedColor;

    //Static variable holding the last clicked panel which held a piece.
    private static GridPanel clickedPanel = new GridPanel(new Square(0,0));

    /**
     * Constructs GridPanel objects.
     * @param position - the position of the GridPanel on the board grid.
     * @param gamePanel - the board graphical representation it is to be stuck to.
     */
    public GridPanel(Square position, GamePanel gamePanel) {
        this.position = position;
        this.gamePanel = gamePanel;
        initialize();
    }

    /**
     * Constructor without passing a reference to a Game Panel.
     * @param position - the position of the GridPanel on the board grid.
     */
    public GridPanel(Square position) {
        this.position = position;
        initialize();
    }

    /**
     * Initializes the graphics components of this specific panel.
     */
    public void initialize() {
        setPreferredSize(new Dimension(60,60));
        addMouseListener(new GridListener());
        lightColor = new Color(231, 231, 231);
        darkColor = new Color(0, 204, 0);
        highlightedColor = new Color(255, 255, 255);
        pressedColor = new Color(255, 255, 100);
        selectedColor = new Color(255, 195, 51);
    }

    /**
     * Gets the position on the board grid of this panel.
     * @return a Square object holding the panel's position as well as any piece it may hold.
     */
    public Square getPosition() {
        return position;
    }

    /**
     * Stores a label(image) of the piece in this grid object.
     * @param label - the image of the piece that may have this position.
     */
    public void storeLabel(JLabel label) {
        this.storedLabel = label;
    }

    /**
     * gets the image stored in the panel.
     * @return the Label object stored here.
     */
    public JLabel getStoredLabel() {
        return storedLabel;
    }

    /**
     * Removes the label(image) from this grid panel.
     */
    public void removeLabel() {
        remove(storedLabel);
    }

    /**
     * Sets the panel to selected and colours it the selectedColor.
     */
    public void setSelected() {
        selected = true;
        setBackground(selectedColor);
    }

    /**
     * Gets whether the the panel is selected.
     * @return a boolean value denoting whether the panel is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets the panel to pressed and colours it the pressedColor.
     */
    public void setPressed() {
        pressed = true;
        setBackground(pressedColor);
    }

    /**
     * Gets whether the the panel is pressed.
     * @return a boolean value denoting whether the panel is pressed.
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * Sets the panel to dark and colours it the darkColor. Also makes pressed false since it cannot be pressed and dark at the same time.
     */
    public void setDark() {
        this.dark = true;
        this.pressed = false;
        setBackground(darkColor);
    }

    /**
     * Sets the panel to light and colours it the lightColor. Also makes pressed false since it cannot be pressed and light at the same time.
     */
    public void setLight() {
        this.dark = false;
        this.pressed = false;
        setBackground(lightColor);
    }

    /**
     * Sets the panel to light or dark depending on its default colour. Also makes selected and pressed false since it cannot be selected or pressed and light or dark at the same time.
     */
    public void setUnselected() {
        selected = false;
        pressed = false;
        if(dark == true) {
            setBackground(darkColor);
        }
        else {
            setBackground(lightColor);
        }
    }

    /**
     * Sets the panel to highlightedColor.
     */
    public void setHighlighted() {
        setBackground(highlightedColor);
    }

    /**
     * A class which observes to see if a panel has been interacted with.
     */
    class GridListener implements MouseListener {
        /**
         * Not used. But would be activated in the event a panel was clicked.
         * @param event - the event which took place.
         */
        public void mouseClicked(MouseEvent event) {
        }

        /**
         * Activated in the event the mouse's cursor enters the boundaries of a panel.
         * @param event - the event which took place.
         */
        public void mouseEntered(MouseEvent event) {
            if (!isPressed() && !clickedPanel.isSelected()) {
                setHighlighted();
            }
        }

        /**
         * Activated in the event a mouses's cursor exits the boundaries of a panel.
         * @param event - the event which took place.
         */
        public void mouseExited(MouseEvent event) {
            if (!isPressed() && !isSelected()) {
                setUnselected();
            }
        }

        /**
         * Activated in the event a mouses's cursor presses and holds a panel.
         * @param event - the event which took place.
         */
        public void mousePressed(MouseEvent event) {
            //Will only highlight panels based on whether the player holding the piece has the turn.
            if (position.hasPiece() && (getPosition().getPiece().isUp() && gamePanel.getBoard().getUpperPlayer().isTurn()
                    || !getPosition().getPiece().isUp() && gamePanel.getBoard().getLowerPlayer().isTurn()))
            {
                if(gamePanel.getBoard().getUpperPlayer().isCheck() || gamePanel.getBoard().getLowerPlayer().isCheck())
                {
                    //In this instance when either player is in check, it will only highlight the panel holding the king.
                    if (getPosition().getPiece() == gamePanel.getBoard().getUpperPlayer().getKing()
                            || getPosition().getPiece() == gamePanel.getBoard().getLowerPlayer().getKing())
                    {
                        setPressed();
                    }
                }
                else
                {
                    setPressed();
                }
            }
        }

        /**
         * Activated in the event a mouses's clicker is released while the cursor is on top of a panel.
         * @param event - the event which took place.
         */
        public void mouseReleased(MouseEvent event) {
            boolean isPanelSelection = false;
            //Determines if the panel in the event was part of selectableMoves.
            if(gamePanel.getBoard().isMoveSelection(GridPanel.this.getPosition()))
            {
                isPanelSelection = true;
            }
            //Then resets selectableSquares so they can be reloaded in the event a different piece is clicked.
            gamePanel.resetSelectables();
            if (isPanelSelection == true)
            {
                //IF NOT CHECK MATE THEN SET ISCHECK TO FALSE
                clickedPanel.setUnselected();
                gamePanel.update(clickedPanel,GridPanel.this);
            }
            //Will only select panels based on whether the player holding the piece has the turn.
            else if (position.hasPiece() && !isSelected() && (getPosition().getPiece().isUp() && gamePanel.getBoard().getUpperPlayer().isTurn()
                        || !getPosition().getPiece().isUp() && gamePanel.getBoard().getLowerPlayer().isTurn()))
            {
                if(gamePanel.getBoard().getUpperPlayer().isCheck() || gamePanel.getBoard().getLowerPlayer().isCheck())
                {
                    //Panels will only highlight possible moves when the player who has the turn selects their King.
                    if(getPosition().getPiece() == gamePanel.getBoard().getUpperPlayer().getKing()
                            || getPosition().getPiece() == gamePanel.getBoard().getLowerPlayer().getKing())
                    {
                        clickedPanel.setUnselected();
                        gamePanel.highlightMoves(GridPanel.this);
                        setSelected();
                        clickedPanel = GridPanel.this;
                    }
                }
                else
                {
                    clickedPanel.setUnselected();
                    gamePanel.highlightMoves(GridPanel.this);
                    setSelected();
                    clickedPanel = GridPanel.this;
                }
            }
            else
            {
                clickedPanel.setUnselected();
            }
        }
    }
}
