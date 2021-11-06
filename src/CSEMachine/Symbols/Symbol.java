package CSEMachine.Symbols;

/**
 * Class representation for Symbols in control stack
 * 
 * @author Hirumal Priyshan
 * @version 1.0
 * @since 1.0
 */
public class Symbol {
    private String token;

    /**
     * Class constructor.
     * 
     * @param token token for the symbol
     */
    public Symbol(String token) {
        this.token = token;
    }

    /**
     * Getter for token
     * 
     * @return token in the symbol
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Getter for token
     * 
     * @param token token to set for symbol
     */
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return  getToken();
    }

}
