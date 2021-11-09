package MyExeptions;

/**
 * Class representation for ASTNotGeneratedException
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class ASTNotGeneratedException extends Exception {

    /**
     * Class constructor.
     * 
     * @param str error message
     */
    public ASTNotGeneratedException(String str) {
        super(str);
    }
    
}
