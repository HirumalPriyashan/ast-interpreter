package CSEMachine.Symbols;

public class Bool extends Symbol{

    public Bool(String token) {
        super(token);
    }
    public boolean isTrue(){
        return token.equals("true");
    }
}
