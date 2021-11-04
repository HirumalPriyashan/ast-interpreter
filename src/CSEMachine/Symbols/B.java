package CSEMachine.Symbols;

import java.util.List;

import Node.Node;

public class B extends Symbol{
    private List<Symbol> symbols;
    private Node node;
    
    public B(Node node) {
        super("B");
        this.node = node;
    }
    
    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public List<Symbol> getSymbols() {
        return this.symbols;
    }

    public Node getNode(){
        return this.node;
    }
}
