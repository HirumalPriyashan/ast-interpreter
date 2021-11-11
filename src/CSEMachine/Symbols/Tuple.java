package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representation for Tuple symbols in control stack
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class Tuple extends Rand{
    private List<Symbol> symbols;

    /**
     * Class constructor
     */
    public Tuple() {
        super("tuple");
        symbols = new ArrayList<Symbol>();
    }

    /**
     * Getter for symbols
     * 
     * @return symbol list in the Tuple symbol
     */
    public List<Symbol> getSymbols() {
        return this.symbols;
    }

    /**
     * Setter for symbols
     * 
     * @param symbols list of symbols
     */
    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }
    
    /**
     * Add a symbol to the symbols list
     * 
     * @param symbol symbol to be add
     */
    public void addSymbol(Symbol symbol){
        this.symbols.add(symbol);
    }

    /**
     * Add a list of symbols to the symbols list
     * 
     * @param symbols list of symbol to be add
     */
    public void addAllSymbols(List<Symbol> symbols){
        this.symbols.addAll(symbols);
    }

    /**
     * Get a symbol from the symbols list in the specified position
     * 
     * @param index position to select the symbol
     * @return  symbol in the specified index
     */
    public Symbol getSymbol(int index) {
        return this.symbols.get(index);
    }

    @Override
    public String toString(){
        if (symbols.size() == 0) {
            return "()";
        }
        String str = "(";
        for (Symbol symbol : symbols) {
            str += symbol.toString() + ", ";
        }
        return str.substring(0, str.length() - 2) + ")";
    }
}
