package CSEMachine.Symbols;

import java.util.ArrayList;
import java.util.List;

import Node.Node;

public class Lambda extends Symbol{
    private static int i = 1;
    private int index;
    private int environment;
    private List<Id> identifiers;
    private Node rightChild;
    private Node lambdaNode;
    private Delta delta;
    
    public Lambda(Node node) {
        super("lambda");
        this.index = i++;
        this.identifiers = new ArrayList<Id>();
        this.rightChild = node.getChildren().get(1);
        this.lambdaNode = node;
        this.environment = -1;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setEnvironment(int n) {
        this.environment = n;
    }
    
    public int getEnvironment() {
        return this.environment;
    }
    
    public void setDelta(Delta delta) {
        this.delta = delta;
    }
    
    public Delta getDelta() {
        return this.delta;
    }

    public void addIdentifier(Id id){
        this.identifiers.add(id);
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getLambdaNode() {
        return lambdaNode;
    }
    
    @Override
    public String toString() {
        String str = "lambda|";
        // if (this.environment > 0) {
        //     str = this.environment + "|" + str;
        // }
        for (Id id : identifiers) {
            str += id.getToken() + "|";
        }
        return str + this.index;
    }

    public Id getX() {
        return this.identifiers.get(0);
    }

    public List<Id> getIdentifiers() {
        return this.identifiers;
    }

}
