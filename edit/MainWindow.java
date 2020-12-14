package edit;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
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
    FileOperations fileOp;
    File currentFile;

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
        textArea.setWrapStyleWord(true);
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
        Path path = currentFile.toPath();

        if(currentFile == null) {
            System.out.println("File has not been instantiated!");
            return;
        }

        if (!Files.isReadable(path)) {
            System.out.println("File is not readable!");
            //TODO: Add frame to show this option to user
            return;
        }

        try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(currentFile));
                textArea.setText("");
                System.out.println("Call before alert call");
                Alert alert = new Alert("The changes made to the current file will not be saved if you continue. Otherwise, cancel.");
                //TODO: Window to confirm that their changes will be deleted if not saved

                String line = null;
                while((line = bufferedReader.readLine()) != null)
                {
                    textArea.append(line + "\n");
                }

                bufferedReader.close();
        } catch(IOException e) {
            System.out.println("An error has occurred with reading the data.");
            System.out.println(e);
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
                System.out.println("User request received");
                fileOp = new FileOperations(chooser.getSelectedFile());
                currentFile = chooser.getSelectedFile();
                MainWindow.this.readFile();
            }
        }
    }

    private class WindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            //TODO: Check is there are changes before closing window
            dispose();
        }
    }
}
