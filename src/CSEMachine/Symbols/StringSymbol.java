package CSEMachine.Symbols;

/**
 * Class representation for StringSymbol symbols in control stack
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class StringSymbol extends Rand{

    /**
     * Class constructor.
     * 
     * @param token token for the symbol
     */
    public StringSymbol(String token) {
        super(token);
    }

    @Override
    public String toString() {
        if (getToken() == "") {
            return "''";
        }
        return getToken();
    }
    
}
