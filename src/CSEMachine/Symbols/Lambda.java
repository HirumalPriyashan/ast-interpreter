package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.List;

import Node.Node;

public class Lambda extends Symbol{
    int index;
    List<Id> identifiers;
    Node rightChild;
    int environment;

    public Lambda(String token, Id leftChild, Node rightChild){
        super(token);
        this.identifiers = new ArrayList<Id>();
        this.identifiers.add(leftChild);
        this.rightChild = rightChild;
    }
    
    public int getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(int environment) {
        this.environment = environment;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Id getX() {
        return this.identifiers.get(0);
    }

    public void setX(Id x) {
        this.identifiers.add(0, x);;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        String str = "";
        for (Id id : identifiers) {
            str += id.getToken() + "|";
        }
        return this.token + "|" + str + this.index;
    }
}
