package CSEMachine.Symbols;

public class UOp extends Rator{

    public UOp(String token) {
        super(token);
    }

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
