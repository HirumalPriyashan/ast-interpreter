package CSEMachine.Symbols;

import java.util.HashMap;
import java.util.Map;

import Logger.Logger;

public class Environment extends Symbol {
    private int index;
    private Environment parent;
    private boolean isRemoved = false;
    public HashMap<Id,Symbol> values;
    
    public Environment(int i) {
        super("e");
        this.setIndex(i);
        this.values = new HashMap<Id,Symbol>();
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

    public void addValue(Id id, Symbol symbol) {
        this.values.put(id, symbol);
    }

    @Override
    public String toString() {
        return getToken() + this.index;
    }

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
}