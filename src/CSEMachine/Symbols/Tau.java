package CSEMachine.Symbols;

/**
 * Class representation for Tau symbols in control stack
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class Tau extends Symbol{
    private int n; // number of children

    /**
     * Class constructor.
     * 
     * @param n number of children
     */
    public Tau(int n) {
        super("tau");
        this.n = n;
    }
    
    /**
     * Getter for n - number of children
     * 
     * @return n
     */
    public int getN () {
        return this.n;
    }
    
    @Override
    public String toString() {
        return "tau|" + this.n;
    }
}
