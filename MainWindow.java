import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    //Bar
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

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
    }

    private final int HEIGHT = 500;
    private final int WIDTH = 700;
    public MainWindow() {
        super("Basic Text Editor");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        setJMenuBar(menuBar);
        setVisible(true);
    }

    private class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("This has been clicked");
        }
    }
}
