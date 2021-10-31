package CSEMachine.Symbols;

import java.util.List;

public class Delta extends Symbol{
    List<Symbol> symbols;

    public Delta(String token) {
        super(token);
    }

    public List<Symbol> getSymbols() {
        return null;
    }
    
    public void setSymbols(List<Symbol> symbols){
        this.symbols = symbols;
    }
}
