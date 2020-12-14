package edit;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Alert extends JFrame {
    JButton confirmButton;
    JButton cancelButton;
    JTextArea field;
    JPanel buttonPanel;
    String response;

    private final int HEIGHT = 200;
    private final int WIDTH = 200;

    public Alert(String message) {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());


        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");
        field = new JTextArea(message);
        field.setLineWrap(true);
        field.setEditable(false);
        field.setMargin(new Insets(0,5,0,5));
        field.setWrapStyleWord(true);
        buttonPanel = new JPanel(new GridLayout(1,2));

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        add(field, BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
        addWindowListener(new WindowListener());
        setVisible(true);
        System.out.println("I am here");

    }

    public String getResponse() {
        if(response == null) return "";
        return response.toLowerCase();
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            response = action;
            System.out.println(action);
        }
    } 
}
