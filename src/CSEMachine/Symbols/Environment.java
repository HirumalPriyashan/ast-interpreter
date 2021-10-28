package CSEMachine.Symbols;

import java.util.HashMap;

public class Environment extends Symbol {
    private int index;
    private Environment parent;
    private boolean isRemoved = false;
    public HashMap<String,Symbol> values;

    public Environment(int i) {
        super("e");
        this.setIndex(i);
        this.values = new HashMap<String,Symbol>();
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    public void setParent(Environment e) {
        this.parent = e;
    }

    public Environment getParent() {
        return this.parent;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public boolean getIsRemoved() {
        return this.isRemoved;
    }

    public void addValue(String id, Symbol symbol) {
        this.values.put(id, symbol);
    }

    public Symbol lookup(String token){
        for (String key: this.values.keySet()) {
            if (key.equals(token)){
                return this.values.get(key);
            }
        }
        if (this.parent != null) {
            return this.parent.lookup(token);
        } else {
            return new Symbol(token);
        }
    }

     @Override
     public String toString() {
         return this.token + this.index ;
     }
}