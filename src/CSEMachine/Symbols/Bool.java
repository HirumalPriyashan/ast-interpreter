package CSEMachine.Symbols;

/**
 * Class representation for Boolean symbols in control stack
 * 
 * @author Hirumal Priyshan
 * @version 1.0
 * @since 1.0
 */
public class Bool extends Rand{

    /**
     * Class constructor.
     * 
     * @param token token for the symbol
     */
    public Bool(String token) {
        super(token);
    }

    /**
     * Returns whether the symbol is a true symbol
     * 
     * @return <b>true</b> if the symbol is true symbol
     *         <b>false</> otherwise
     */
    public boolean isTrue() {
        return getToken().equals("true");
    }
    
}
