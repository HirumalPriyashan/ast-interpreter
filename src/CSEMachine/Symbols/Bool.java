package CSEMachine.Symbols;

public class Bool extends Rand{

    public Bool(String token) {
        super(token);
    }

    public boolean isTrue() {
        return getToken().equals("true");
    }
    
}
