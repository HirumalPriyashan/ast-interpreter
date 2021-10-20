package Node;

import java.util.ArrayList;

public class Node {
    private String token;
    private ArrayList<Node> children;
    private Node parent;
    private int depth;
    private boolean isStandardized;

    public Node(String token,  Node parent, int depth) {
        this.token = token;
        this.children = new ArrayList<Node>();
        this.parent = parent;
        this.depth = depth;
        this.isStandardized = false;
    }

    public ArrayList<Node> getChildren() {
        return this.children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public String gettoken() {
        return this.token;
    }

    public void settoken(String token) {
        this.token = token;
    }
    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void increaseDepthBy(int margin) {
        this.depth += margin;
        for (Node node : children) {
            node.increaseDepthBy(margin);
        }
    }

    public boolean getIsStandardized() {
        if (this.children.size() == 0){
            this.isStandardized = true;
        }
        return this.isStandardized;
    }

    public void setIsStandardized(boolean isStandardized) {
        this.isStandardized = isStandardized;
    }

    public Node getNextToAppend(int depth){
        if (depth == this.depth + 1) {
            return this;
        }
        return this.children.get(this.children.size() - 1).getNextToAppend(depth);
    }

    @Override
    public String toString() {
        return ".".repeat(this.depth * 1) + this.token ;
    }

    public void printNode() {
        System.out.println(this);
        for (Node node : this.children) {
            node.printNode();
        }
    }

    public Node standardizeNode() {
        if (!getIsStandardized()) {
            this.standardize();
        }
        return this;
    }

    private void standardize() {
        if (this.token.equals("let")) {
            Node equal = this.children.get(0);
            Node P = this.children.get(1).standardizeNode();
            Node X = equal.getChildren().get(0).standardizeNode();
            Node E = equal.getChildren().get(1).standardizeNode();
            // restructure
            this.token = "gamma";
            Node lamda = new Node("lamda", this, this.depth + 1);
            lamda.addChild(X);
            P.increaseDepthBy(1);
            lamda.addChild(P);
            E.increaseDepthBy(-1);
            this.setChildren(new ArrayList<Node>());
            this.addChild(lamda);
            this.addChild(E);
        }
        else if (this.token.equals("where")) {
            Node equal = this.children.get(1);
            Node P = this.children.get(0).standardizeNode();
            Node X = equal.getChildren().get(0).standardizeNode();
            Node E = equal.getChildren().get(1).standardizeNode();
            // restructure
            this.token = "gamma";
            Node lamda = new Node("lamda", this, this.depth + 1);
            lamda.addChild(X);
            P.increaseDepthBy(1);
            lamda.addChild(P);
            E.increaseDepthBy(-1);
            this.setChildren(new ArrayList<Node>());
            this.addChild(lamda);
            this.addChild(E);
        }
        this.setIsStandardized(true);
    }
}
