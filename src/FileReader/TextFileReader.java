package FileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * TextFileReader reads from a given file and returns content
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class TextFileReader implements FileReader{
    private String fileName;
    private List<String> data;

    /**
    * Class constructor specifying the path to the file
    * to be read.
    * 
    * @param fileName relative or absolute path to the file
    */
    public TextFileReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Getter for file name
     * @return filepath
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Setter for file name
     * 
     * @param fileName path to the file
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String> getData() {
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
            System.out.println("There is no file in the specified path");
            System.exit(-1);
        }
        return this.data;
    }
}
