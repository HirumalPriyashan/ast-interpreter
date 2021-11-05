package CSEMachine.Symbols;

public class Tau extends Symbol{
    private int n;

    public Tau(int n) {
        super("tau");
        this.n = n;
    }
    
    public int getN () {
        return this.n;
    }
    
    @Override
    public String toString() {
        return "tau|" + this.n;
    }
}
