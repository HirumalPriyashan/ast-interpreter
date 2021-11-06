package CSEMachine.Symbols;

public class BOp extends Rator{

    public BOp(String token) {
        super(token);
    }

    public Symbol apply(Rand rand1, Rand rand2) {
        if ("+".equals(getToken())) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString(val1+val2));
        } else if ("-".equals(getToken())) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString(val1-val2));
        } else if ("*".equals(getToken())) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString(val1*val2));
        } else if ("/".equals(getToken())) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString(val1/val2));
        } else if ("**".equals(getToken())) {
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Int(Integer.toString((int) Math.pow(val1, val2)));
        } else if ("&".equals(getToken())) {            
            boolean val1 = Boolean.parseBoolean(rand1.getToken());
            boolean val2 = Boolean.parseBoolean(rand2.getToken());
            return new Bool(Boolean.toString(val1 && val2));
        } else if ("or".equals(getToken())) {            
            boolean val1 = Boolean.parseBoolean(rand1.getToken());
            boolean val2 = Boolean.parseBoolean(rand2.getToken());
            return new Bool(Boolean.toString(val1 || val2));
        } else if ("eq".equals(getToken())) {            
            String val1 = rand1.getToken();
            String val2 = rand2.getToken();
            return new Bool(Boolean.toString(val1.equals(val2)));
        } else if ("ne".equals(getToken())) {            
            String val1 = rand1.getToken();
            String val2 = rand2.getToken();
            return new Bool(Boolean.toString(!val1.equals(val2)));
        } else if ("ls".equals(getToken()) || "<".equals(getToken())) {            
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Bool(Boolean.toString(val1 < val2));
        } else if ("le".equals(getToken()) || "<=".equals(getToken())) {            
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Bool(Boolean.toString(val1 <= val2));
        } else if ("gr".equals(getToken()) || ">".equals(getToken())) {            
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Bool(Boolean.toString(val1 > val2));
        } else if ("ge".equals(getToken()) || ">=".equals(getToken())) {            
            int val1 = Integer.parseInt(rand1.getToken());
            int val2 = Integer.parseInt(rand2.getToken());
            return new Bool(Boolean.toString(val1 >= val2));
        } else if ("aug".equals(getToken())) {  
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
