package edit;

import java.io.*;
import javax.swing.*;

public class FileOperations {
    private File file;

    public FileOperations(File input) {
        file = input.getAbsoluteFile();
    }

    public FileOperations() {
        file = new File("untitled.txt");
    }

    public String getName() {
        return file.getName();
    }

    public File getFile() {
        return file.getAbsoluteFile();
    }


    public void readToTextArea(JTextArea textArea, JFrame frame) {
        if (!file.canRead()) {
            System.out.println("File is not readable!");
            return;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            textArea.setText("");

            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                textArea.append(line + "\n");
            }

            bufferedReader.close();
            setWindowTitle(frame);
        } catch(IOException e) {
            System.out.println("An error has occurred with reading the data.");
            System.out.println(e);
        }
    }

    public void saveFromTextArea(JTextArea textArea, JFrame frame, File saveAs) {
        file = saveAs.getAbsoluteFile();

        try {
            if(file.exists()) {
                if(!file.delete()) {
                    System.out.println("Failed to delete file before performing save!");
                    return;
                }
            }

            if(!file.createNewFile()) {
                System.out.println("Failed to create new file!");
                return;
            }

            FileWriter writer = new FileWriter(file);
            writer.write(textArea.getText());
            writer.close();
            setWindowTitle(frame);


        } catch (IOException error) {
            System.out.println("Error on saving file: " + error);
        }
    }

    private void setWindowTitle(JFrame frame) {
        frame.setTitle(getName());
    }

}
