package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representation for IntermediateRator symbols in control stack
 * No need for this class with CSE Rule 6 & 7 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class IntermediateRator extends Rator{
    List<String> mathOps = new ArrayList<String>(Arrays.asList( "+", "-", "*", "/", "**"));

    /**
     * Class constructor.
     * 
     * @param token token for the symbol
     */
    public IntermediateRator(String token) {
        super(token);
    }

    @Override
    public Symbol apply(Rand rand) {
        if (getToken().substring(1, 2).equals("+")) {
            int val1 = Integer.parseInt(this.getToken().substring(2, this.getToken().length() - 1));
            int val2 = Integer.parseInt(rand.getToken());
            return new Int(Integer.toString((int) val1 +val2));
        } else if (getToken().substring(1, 2).equals("-")) {
            int val1 = Integer.parseInt(this.getToken().substring(2, this.getToken().length() - 1));
            int val2 = Integer.parseInt(rand.getToken());
            return new Int(Integer.toString((int) val1 -val2));
        } else if (getToken().substring(1, 2).equals("/")) {
            int val1 = Integer.parseInt(this.getToken().substring(2, this.getToken().length() - 1));
            int val2 = Integer.parseInt(rand.getToken());
            return new Int(Integer.toString((int) val1 /val2));
        }else if (getToken().substring(1, 2).equals("*")) {
            int val1 = Integer.parseInt(this.getToken().substring(2, this.getToken().length() - 1));
            int val2 = Integer.parseInt(rand.getToken());
            return new Int(Integer.toString((int) val1 *val2));
        } else if (getToken().substring(1, 3).equals("**")) {
            int val1 = Integer.parseInt(this.getToken().substring(3, this.getToken().length() - 1));
            int val2 = Integer.parseInt(rand.getToken());
            return new Int(Integer.toString((int) Math.pow(val1, val2)));
        }
        return null;
    }
}