package CSEMachine.Symbols;

public class BOp extends Rator{

    public BOp(String token) {
        super(token);
    }

    public Symbol apply(Rand rand1, Rand rand2) {
        if (token.equals("+")) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString((int) val1 +val2));
        } else if (token.equals("-")) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString((int) val1 -val2));
        } else if (token.equals("/")) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString((int) val1 /val2));
        }else if (token.equals("*")) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString((int) val1 *val2));
        } else if (token.equals("**")) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString((int) Math.pow(val1, val2)));
        }
        return null;
        
    }
    
}
