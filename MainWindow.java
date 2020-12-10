import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.nio.file.*;

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
    JScrollPane scrollPane;
    File currentFile;
    List<String> fileContent;

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
    }

    private final int HEIGHT = 500;
    private final int WIDTH = 700;
    public MainWindow() {
        super("Basic Text Editor");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        initialiseObjects();

        // Action Listeners
        addEventListeners();
        addOptionsToMenu();


        setupTextArea();
        setJMenuBar(menuBar);
        add(scrollPane);
        addWindowListener(new WindowListener());
        setVisible(true);
    }

    private void initialiseObjects() {

        menuBar = new JMenuBar();

        //  main menus
        editMenu = new JMenu("Edit");
        fileMenu = new JMenu("File");

        //  file menu options
        newFileOption = new JMenuItem("New file");
        openFileOption = new JMenuItem("Open file");
        saveFileOption = new JMenuItem("Save file");

        //  edit menu options
        pasteOption = new JMenuItem("Paste");
        redoOption = new JMenuItem("Redo");
        undoOption = new JMenuItem("Undo");

        currentFile = null;
    }

    private void setupTextArea() {
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setMargin(new Insets(0,5,0,5));

        scrollPane = new JScrollPane(textArea);
    }

    private void addEventListeners() {
        newFileOption.addActionListener(new NewFileListener());
        openFileOption.addActionListener(new OpenFileListener());
    }

    private void addOptionsToMenu() {

        // file menu options to file menu
        fileMenu.add(newFileOption);
        fileMenu.add(openFileOption);
        fileMenu.add(saveFileOption);

        // edit menu options to edit menu
        editMenu.add(pasteOption);
        editMenu.add(redoOption);
        editMenu.add(undoOption);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

    }

    private void readFile() {
        Path path = Paths.get(currentFile.getAbsolutePath());
        try {
            if(fileContent == null)
                fileContent = Files.readAllLines(path);
            else {
                fileContent.clear();
                fileContent = Files.readAllLines(path);
            }
        } catch(IOException e) {
            System.out.println("An error has occurred with reading the data.");
            System.out.println(e);
        }

        textArea.setText(null);
        for(String line: fileContent.toArray(new String[fileContent.size()])) {
            System.out.println(line);
            textArea.append(line + "\n");
        }

    }

    private class NewFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MainWindow newFile = new MainWindow();
        }
    }


    private class OpenFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(null);

            if(returnVal == JFileChooser.APPROVE_OPTION) {
                currentFile = chooser.getSelectedFile();
                MainWindow.this.readFile();
            }
        }
    }

    private class WindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }
}
