import javax.swing.*;

/**
 * Creates the main program frame.
 */
public class ChessFrame extends JFrame {

    String title;
    JMenuBar menuBar;

    public ChessFrame(String title) {
        this.title = title;
        setTitle(title);
        setSize(520,520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);





    initializeComponents();
    }

    public void initializeComponents() {
        menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");

        JMenuItem close = new JMenuItem("close");
        JMenuItem about = new JMenuItem("About");

        file.add(close);
        help.add(about);
        menuBar.add(file);
        menuBar.add(help);
        setJMenuBar(menuBar);
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
    }

    public static void main(String[] args) {
        ChessFrame chessFrame = new ChessFrame("Java Chess");
        chessFrame.setVisible(true);
    }
}
