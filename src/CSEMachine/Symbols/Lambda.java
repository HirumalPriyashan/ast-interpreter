package CSEMachine.Symbols;

import Node.Node;

public class Lambda extends Symbol{
    int index;
    Id x;
    Node rightChild;
    int environment;

    public Lambda(String token, int index, Id leftChild, Node rightChild){
        super(token);
        this.index = index;
        this.x = leftChild;
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
        return this.x;
    }

    public void setX(Id x) {
        this.x = x;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return this.token + "|" + this.x.getToken()+ "|" + this.index;
    }
}
