package CSEMachine.Symbols;

public class Eta extends Rand{
    private int index;
    private int environment;
    private Id identifier;
    private Lambda lambda;
    
    public Eta() {
        super("eta");
    }    
    
    public void setIndex(int i) {
        this.index = i;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setEnvironment(int e) {
        this.environment = e;
    }
    
    public int getEnvironment() {    
        return this.environment;
    }
    
    public void setIdentifier(Id id) {
        this.identifier = id;
    }
    
    public Id getIdentifier() {
        return this.identifier;
    }

    public void setLambda(Lambda lambda) {
        this.lambda = lambda;
    }
    
    public Lambda getLambda() {
        return this.lambda;
    }
    
    @Override
    public String toString() {
        return this.environment + "|eta|"+ this.identifier + "|"  + this.index;
    }
}
