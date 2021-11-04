package CSEMachine.Symbols;

import java.util.List;

public class Tuple extends Symbol{
    
    private List<Symbol> symbols;

    public Tuple() {
        super("tuple");
    }

    public List<Symbol> getSymbols() {
        return this.symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }
    
}
