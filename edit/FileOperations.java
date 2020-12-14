package edit;

import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class FileOperations {
    File file;

    public FileOperations()
    {

    }


    private void readFile(JTextArea textArea) {
        if(file == null) {
            System.out.println("File has not been instantiated!");
            return;
        }

        Path path = file.toPath();

        if (!Files.isReadable(path)) {
            System.out.println("File is not readable!");
            //TODO: Add frame to show this option to user
            return;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
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

}
