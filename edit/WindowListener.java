package edit;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent window) {
            //TODO: Check is there are changes before closing window
            window.getWindow().dispose();
        }
    }
