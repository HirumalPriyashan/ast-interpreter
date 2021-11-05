package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.List;

public class Tuple extends Rand{
    
    private List<Symbol> symbols;

    public Tuple() {
        super("tuple");
        symbols = new ArrayList<Symbol>();
    }

    public List<Symbol> getSymbols() {
        return this.symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }
    
    public void addSymbol(Symbol symbol){
        this.symbols.add(symbol);
    }

    public Symbol getSymbol(int i) {
        return this.symbols.get(i);
    }
}
