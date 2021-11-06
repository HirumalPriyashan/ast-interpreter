package CSEMachine.Symbols;

/**
 * Class representation for Eta symbols in control stack
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class Eta extends Rand{
    private int index;
    private int environment;
    private Id identifier;
    private Lambda lambda;
    
    /**
     * Class constructor
     */
    public Eta() {
        super("eta");
    }    
    
    /**
     * Setter for index
     * 
     * @param i index of the Eta symbol
     */
    public void setIndex(int i) {
        this.index = i;
    }
    
    /**
     * Getter for index
     * 
     * @return the index of the Eta symbol
     */
    public int getIndex() {
        return this.index;
    }
    
    /**
     * Setter for environment
     * 
     * @param e index of the environment symbol
     */
    public void setEnvironment(int e) {
        this.environment = e;
    }
    
    /**
     * Getter for environment
     * 
     * @return the environment of the Eta symbol
     */
    public int getEnvironment() {    
        return this.environment;
    }
    
    /**
     * Setter for identifier
     * 
     * @param id id of the Eta symbol
     */
    public void setIdentifier(Id id) {
        this.identifier = id;
    }
    
    /**
     * Getter for identifier
     * 
     * @return the identifier of the Eta symbol
     */
    public Id getIdentifier() {
        return this.identifier;
    }

    /**
     * Setter for lambda
     * 
     * @param lambda lambda of the Eta symbol
     */
    public void setLambda(Lambda lambda) {
        this.lambda = lambda;
    }
    
    /**
     * Getter for lambda
     * 
     * @return the lambda of the Eta symbol
     */
    public Lambda getLambda() {
        return this.lambda;
    }
    
    @Override
    public String toString() {
        return this.environment + "|eta|"+ this.identifier + "|"  + this.index;
    }
}
