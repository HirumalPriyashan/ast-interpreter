package CSEMachine.Symbols;

/**
 * Class representation for UOp symbols in control stack
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class UOp extends Rator{

    /**
     * Class constructor.
     * 
     * @param token token for the symbol
     */
    public UOp(String token) {
        super(token);
    }

    /**
     * Applies unary operation on the rands
     * 
     * @param rand rand symbol
     * @return new symbol created after appling the unary operation
     */
    @Override
    public Symbol apply(Rand rand) {
        if ("neg".equals(getToken())) {
            int val = Integer.parseInt(rand.getToken());
            return new Int(Integer.toString(-1*val));
        } else if ("not".equals(getToken())) {
            boolean val = Boolean.parseBoolean(rand.getToken());
            return new Bool(Boolean.toString(!val));
        } else {
            return new Error("");
        }
    }
    
}
