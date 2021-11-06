package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representation for Rator symbols in control stack
 * 
 * @author Hirumal Priyshan
 * @version 1.0
 * @since 1.0
 */
public class Rator extends Symbol{
    List<String> mathOps = new ArrayList<String>(Arrays.asList( "+", "-", "*", "/", "**"));

    /**
     * Class constructor.
     * 
     * @param token token for the symbol
     */
    public Rator(String token) {
        super(token);
    }

    /**
     * Applies math operation on the rands
     * 
     * @param rand rand symbol
     * @return new symbol created after appling the math operation
     */
    public Symbol apply(Rand rand) {
        if (mathOps.contains(getToken())) {
            return new IntermediateRator("(" + getToken() + rand.getToken() + ")");
        }
        return null;
    }
    
}
