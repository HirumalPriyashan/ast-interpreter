package ASTTreeGenerator;

import java.util.ArrayList;

public class Node {
    private String value;
    private ArrayList<Node> children;
    private Node parent;
    private int depth;

    public Node(String value,  Node parent, int depth) {
        this.value = value;
        this.children = new ArrayList<Node>();
        this.parent = parent;
        this.depth = depth;
    }

    public ArrayList<Node> getChildren() {
        return this.children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public Node getNextToAppend(int depth){
        if (depth == this.depth + 1) {
            return this;
        }
        return this.children.get(this.children.size() - 1).getNextToAppend(depth);
    }

    @Override
    public String toString() {
        return " ".repeat(this.depth * 8) + this.value ;
    }

    public void printNode() {
        System.out.println(this);
        for (Node node : this.children) {
            node.printNode();
        }
    }
}
