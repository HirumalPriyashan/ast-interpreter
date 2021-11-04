package CSEMachine.Symbols;

import java.util.List;

import Node.Node;

public class Delta extends Symbol{
    private int index;
    private List<Symbol> symbols;
    private Node node;
    private static int i = 0;
    private boolean isPassed;

    public Delta(Node node) {
        super("delta");
        this.index = i++;
        this.node = node;
        this.isPassed = false;
    }
    
    public int getIndex() {
        return this.index;
    }

    public List<Symbol> getSymbols() {
        return this.symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public Node getNode(){
        return this.node;
    }

    public boolean isPassed() {
        return this.isPassed;
    }

    public boolean getIsPassed() {
        return this.isPassed;
    }

    public void setIsPassed(boolean isPassed) {
        this.isPassed = isPassed;
    }
    
}
