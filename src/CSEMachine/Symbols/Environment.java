package CSEMachine.Symbols;

import java.util.HashMap;
import java.util.Map;

import Logger.Logger;

/**
 * Class representation for Environment symbols in CSE machine
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class Environment extends Symbol {
    private int index;
    private Environment parent;
    public HashMap<Id,Symbol> values;
    
    /**
     * Class constructor.
     * 
     * @param i index for the environment
     */
    public Environment(int i) {
        super("e");
        this.setIndex(i);
        this.values = new HashMap<Id,Symbol>();
    }
    
    /**
     * Setter for index
     * 
     * @param i index of the Environment symbol
     */
    public void setIndex(int i) {
        this.index = i;
    }
    
    /**
     * Getter for index
     * 
     * @return the index of the Environment symbol
     */
    public int getIndex() {
        return this.index;
    }
    
    /**
     * Setter for parent
     * 
     * @param environment Environment symbol to be set as parent
     */
    public void setParent(Environment environment) {
        this.parent = environment;
    }
    
    /**
     * Getter for parent
     * 
     * @return parent environment of the Environment symbol
     */
    public Environment getParent() {
        return this.parent;
    }
    
    /**
     * Look up for the value for given id in the environment
     * 
     * @param id Id to lookup
     * @return value mapped to the Id
     */
    public Symbol lookup(Id id){
        for (Id key: this.values.keySet()) {
            if (key.getToken().equals(id.getToken())){
                return this.values.get(key);
            }
        }
        if (this.parent != null) {
            return this.parent.lookup(id);
        } else {
            return new Symbol(id.getToken());
        }
    }

    /**
     * Add new value to the values in the environment
     * 
     * @param id id which will use as key for hashmap
     * @param symbol symbol to map for Id
     */
    public void addValue(Id id, Symbol symbol) {
        this.values.put(id, symbol);
    }

    /**
     * Prints environment with its values
     */
    public void printEnvironment() {
        String str = toString()  + " = ";
        for (Map.Entry<Id, Symbol> entry : values.entrySet()) {
            Id key = entry.getKey();
            Symbol value = entry.getValue();
            str += "[ '" + value.toString() + "' / '" + key.toString() + "' ] ";
        }
        str += this.getParent().toString();
        Logger.log(str);
    }

    @Override
    public String toString() {
        return getToken() + this.index;
    }
}