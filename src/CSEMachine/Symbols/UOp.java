package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UOp extends Rator{
    List<String> UOps = new ArrayList<String>(Arrays.asList("neg"));

    public UOp(String token) {
        super(token);
    }

    public Symbol apply(Rand rand) {
        if (UOps.contains(getToken())) {
            if (getToken().equals("neg")) {
                int val2 = Integer.parseInt(rand.getToken());
                return new Int(Integer.toString((int) -1 *val2));
            }
        }
        return null;
    }
    
}
