package CSEMachine.Symbols;

public class UOp extends Rator{

    public UOp(String token) {
        super(token);
    }

    public Symbol apply(Rand rand) {
        if (token.equals("neg")) {
            int val2 = Integer.parseInt(rand.getToken());
            return new Int(Integer.toString((int) -1 *val2));
        }
        return null;
    }
    
}
