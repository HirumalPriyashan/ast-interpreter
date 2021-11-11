package CSEMachine.Symbols;

import java.util.List;

import Node.Node;

/**
 * Class representation for B symbols in control stack
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class B extends Symbol{
    private List<Symbol> symbols;
    private Node node;
    
    /**
     * Class constructor.
     * 
     * @param node starting node for B symbol
     */
    public B(Node node) {
        super("B");
        this.node = node;
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
     * Getter for symbols
     * 
     * @return symbol list in the B symbol
     */
    public List<Symbol> getSymbols() {
        return this.symbols;
    }

    /**
     * Getter for starting node for B symbol
     * 
     * @return starting node
     */
    public Node getNode(){
        return this.node;
    }
}
