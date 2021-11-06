package CSEMachine.Symbols;

/**
 * Class representation for BOp symbols in control stack
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class BOp extends Rator{

    /**
     * Class constructor.
     * 
     * @param token token for the symbol
     */
    public BOp(String token) {
        super(token);
    }

    /**
     * Applies binary operation on the rands
     * 
     * @param rand1 first rand symbol
     * @param rand2 second rand symbol
     * @return new symbol created after appling the binary operation
     */
    public Symbol apply(Rand rand1, Rand rand2) {
        // handle +
        if ("+".equals(getToken())) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString(val1+val2));
        } 
        // handle -
        else if ("-".equals(getToken())) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString(val1-val2));
        }  
        // handle *
        else if ("*".equals(getToken())) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString(val1*val2));
        }  
        // handle /
        else if ("/".equals(getToken())) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString(val1/val2));
        }  
        // handle **
        else if ("**".equals(getToken())) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString((int) Math.pow(val1, val2)));
        } 
        // handle &
         else if ("&".equals(getToken())) {            
            boolean val1 = Boolean.parseBoolean(rand1.getToken());
            boolean val2 = Boolean.parseBoolean(rand2.getToken());
            return new Bool(Boolean.toString(val1 && val2));
        }  
        // handle or
        else if ("or".equals(getToken())) {            
            boolean val1 = Boolean.parseBoolean(rand1.getToken());
            boolean val2 = Boolean.parseBoolean(rand2.getToken());
            return new Bool(Boolean.toString(val1 || val2));
        }  
        // handle eq
        else if ("eq".equals(getToken())) {            
            String val1 = rand1.getToken();
            String val2 = rand2.getToken();
            return new Bool(Boolean.toString(val1.equals(val2)));
        } 
        // handle ne
         else if ("ne".equals(getToken())) {            
            String val1 = rand1.getToken();
            String val2 = rand2.getToken();
            return new Bool(Boolean.toString(!val1.equals(val2)));
        } 
        // handle ls
         else if ("ls".equals(getToken()) || "<".equals(getToken())) {            
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Bool(Boolean.toString(val1 < val2));
        } 
        // handle le
         else if ("le".equals(getToken()) || "<=".equals(getToken())) {            
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Bool(Boolean.toString(val1 <= val2));
        } 
        // handle gr
         else if ("gr".equals(getToken()) || ">".equals(getToken())) {            
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Bool(Boolean.toString(val1 > val2));
        } 
        // handle ge
         else if ("ge".equals(getToken()) || ">=".equals(getToken())) {            
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Bool(Boolean.toString(val1 >= val2));
        } 
        // handle aug
         else if ("aug".equals(getToken())) {  
            if (rand2 instanceof Tuple) {
                ((Tuple) rand1).addAllSymbols(((Tuple) rand2).getSymbols());
            } else {
                ((Tuple) rand1).addSymbol(rand2);
            }
            return rand1;
        } else {
            return new Error("");
        }
    }
    
}
