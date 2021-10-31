package CSEMachine.Symbols;

import java.util.List;

public class B extends Symbol{
    List<Symbol> symbols;

    public B(String token) {
        super(token);
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }
    
}
