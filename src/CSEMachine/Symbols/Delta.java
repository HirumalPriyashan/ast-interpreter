package CSEMachine.Symbols;

import java.util.List;

import Node.Node;

/**
 * Class representation for Delta symbols in control stack
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class Delta extends Symbol{
    private int index;
    private List<Symbol> symbols;
    private Node node;
    private static int i = 0;
    private boolean isPassed;

    /**
     * Class constructor.
     * 
     * @param node starting node for B symbol
     */
    public Delta(Node node) {
        super("delta");
        this.index = i++;
        this.node = node;
        this.isPassed = false;
    }
    
    /**
     * Getter for index
     * 
     * @return the index of the Delta symbol
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Getter for symbols
     * 
     * @return symbol list in the Delta symbol
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
     * Getter for starting node for Delta symbol
     * 
     * @return starting node
     */
    public Node getNode(){
        return this.node;
    }

    /**
     * Getter for isPassed for B symbol
     * isPassed will have value of <b>true</b> if Delta symbol have 
     * been created for control by using the node in Delta symbol
     * 
     * @return value of isPassed
     */
    public boolean isPassed() {
        return this.isPassed;
    }

    /**
     * Getter for isPassed for B symbol
     * isPassed will have value of <b>true</b> if Delta symbol have 
     * been created for control by using the node in Delta symbol
     * 
     * @return value of isPassed
     */
    public boolean getIsPassed() {
        return this.isPassed;
    }

    /**
     * Setter for isPassed
     * 
     * @param isPassed value to set for isPassed
     */
    public void setIsPassed(boolean isPassed) {
        this.isPassed = isPassed;
    }

    /**
     * Remove a symbol from symbol list
     * 
     * @param symbol symbol to be removed
     */
    public void removeSymbol(Symbol symbol){
        this.symbols.remove(symbol);
    }

    /**
     * Add a symbol to the symbols list for the specified position
     * 
     * @param index position to add the symbol
     * @param symbol symbol to be add
     */
    public void addSymbol(int index, Symbol symbol){
        this.symbols.add(index, symbol);
    }

    @Override
    public String toString() {
        return getToken() + this.index;
    }
    
}
