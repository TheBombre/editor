import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
    }

    private final int HEIGHT = 500;
    private final int WIDTH = 700;
    public MainWindow() {
        super("My super fun title!! yAyy");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar bar = new JMenuBar();
	JMenu menu = new JMenu("Menu");
	JMenu submenu = new JMenu("Submenu");
        JMenuItem file = new JMenuItem("File");
        JMenuItem create = new JMenuItem("New File");

        file.addActionListener(new MenuItemListener());
        menu.add(create);
        submenu.add(file);
        menu.add(submenu);
        bar.add(menu);

        setJMenuBar(bar);
        setVisible(true);
    }

    private class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("This has been clicked");
        }
    }
}
