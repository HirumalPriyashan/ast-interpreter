package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rator extends Symbol{
    List<String> mathOps = new ArrayList<String>(Arrays.asList( "+", "-", "*", "/", "**"));
    List<String> UOps = new ArrayList<String>(Arrays.asList( "neg"));

    public Rator(String token) {
        super(token);
    }

    public Symbol apply(Rand rand) {
        if (mathOps.contains(token)) {
            return new IntermediateRator("(" + token + rand.getToken() + ")");
        } else if (UOps.contains(token)) {
            if (token.equals("neg")) {
                int val2 = Integer.parseInt(rand.getToken());
                return new Int(Integer.toString((int) -1 *val2));
            }
        }
        return null;
    }
    
}
