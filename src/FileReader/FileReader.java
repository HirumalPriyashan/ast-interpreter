package FileReader;
import java.util.List;

/**
 * Abstraction for file readers
 * 
 * @author Hirumal Priyshan
 * @version 1.0
 * @since 1.0
 */
public interface FileReader {
    /**
     * Read and return the content from a file
     * 
     * @return line content in the file as a  list
     */
    public List<String> getData();
}
