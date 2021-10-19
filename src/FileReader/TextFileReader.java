package FileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TextFileReader implements FileReader{
    private String fileName;
    private ArrayList<String> data;

    public TextFileReader(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ArrayList<String> getData() {
        try {
            File file = new File(this.fileName);
            Scanner scanner = new Scanner(file);
            this.data = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                this.data.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return this.data;
    }
}
