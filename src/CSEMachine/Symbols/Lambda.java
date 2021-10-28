package CSEMachine.Symbols;

import Node.Node;

public class Lambda extends Symbol{
    int index;
    Node x;
    Node rightChild;
    public Lambda(String token, int index, Node leftChild, Node rightChild){
        super(token);
        this.index = index;
        this.x = leftChild;
        this.rightChild = rightChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
}
