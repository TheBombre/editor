import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JFrame {

    // Bar
    JMenuBar menuBar;

    // Main menus
    JMenu fileMenu;
    JMenu editMenu;

    // File Menu options
    JMenuItem newFileOption;
    JMenuItem openFileOption;
    JMenuItem saveFileOption;

    // Edit Menu options
    JMenuItem pasteOption;
    JMenuItem redoOption;
    JMenuItem undoOption;


    JTextArea textArea;

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
    }

    private final int HEIGHT = 500;
    private final int WIDTH = 700;
    public MainWindow() {
        super("Basic Text Editor");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        menuBar = new JMenuBar();

        // Initialise main menus
        editMenu = new JMenu("Edit");
        fileMenu = new JMenu("File");

        // Initialise file menu options
        newFileOption = new JMenuItem("New file");
        openFileOption = new JMenuItem("Open file");
        saveFileOption = new JMenuItem("Save file");

        // Initialise edit menu options
        pasteOption = new JMenuItem("Paste");
        redoOption = new JMenuItem("Redo");
        undoOption = new JMenuItem("Undo");

        // Action Listeners
        newFileOption.addActionListener(new NewFileListener());

        // Append file menu options to file menu
        fileMenu.add(newFileOption);
        fileMenu.add(openFileOption);
        fileMenu.add(saveFileOption);

        // Append edit menu options to edit menu
        editMenu.add(pasteOption);
        editMenu.add(redoOption);
        editMenu.add(undoOption);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setMargin(new Insets(0,5,0,5));

        setJMenuBar(menuBar);
        add(textArea);
        setVisible(true);
    }

    private class NewFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MainWindow newFile = new MainWindow();
        }
    }
}
