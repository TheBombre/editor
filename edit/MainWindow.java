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

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
    }

    private final int HEIGHT = 500;
    private final int WIDTH = 700;

    public MainWindow() {
        super();
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
        setTitle(fileOp.getName());
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
        fileOp = new FileOperations();

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
        saveFileOption.addActionListener(new saveFileListener());
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

    private class NewFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MainWindow newFile = new MainWindow();
        }
    }

    private class saveFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(fileOp.getFile());
            int returnVal = chooser.showSaveDialog(null);

            if(returnVal == JFileChooser.APPROVE_OPTION) {
                fileOp.saveFromTextArea(textArea, MainWindow.this, chooser.getSelectedFile());
            }
        }
    }


    private class OpenFileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(null);

            if(returnVal == JFileChooser.APPROVE_OPTION) {
                fileOp = new FileOperations(chooser.getSelectedFile());
                fileOp.readToTextArea(textArea, MainWindow.this);
            }
        }
    }

}
