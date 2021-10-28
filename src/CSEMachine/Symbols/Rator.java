package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rator extends Symbol{
    List<String> mathOps = new ArrayList<String>(Arrays.asList( "+", "-", "*", "/", "**"));

    public Rator(String token) {
        super(token);
    }

    public Symbol apply(Rand rand) {
        if (mathOps.contains(token)) {
            return new IntermediateRator("(" + token + rand.getToken() + ")");
        }
        return null;
    }
    
}
