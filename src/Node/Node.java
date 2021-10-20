package Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
    private String token;
    private List<Node> children;
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

    public List<Node> getChildren() {
        return this.children;
    }

    public void addChild(Node child) {
        child.setParent(this);
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
            for (Node node : this.children) {
                node.standardizeNode();
            }
            this.standardize();
        }
        return this;
    }

    private void standardize() {
        if (this.token.equals("let")) {
            Node equal = this.children.get(0);
            Node P = this.children.get(1);
            Node X = equal.getChildren().get(0);
            Node E = equal.getChildren().get(1);
            // restructure
            this.token = "gamma";
            Node lambda = new Node("lambda", this, this.depth + 1);
            P.increaseDepthBy(1);
            lambda.setChildren(new ArrayList<Node>(Arrays.asList(X, P)));
            E.increaseDepthBy(-1);
            this.setChildren(new ArrayList<Node>(Arrays.asList(lambda, E)));
            this.setIsStandardized(true);
        }
        else if (this.token.equals("where")) {
            Node equal = this.children.get(1);
            Node P = this.children.get(0);
            Node X = equal.getChildren().get(0);
            Node E = equal.getChildren().get(1);
            // restructure
            this.token = "gamma";
            Node lambda = new Node("lambda", this, this.depth + 1);
            P.increaseDepthBy(1);
            lambda.setChildren(new ArrayList<Node>(Arrays.asList(X, P)));
            E.increaseDepthBy(-1);
            this.setChildren(new ArrayList<Node>(Arrays.asList(lambda, E)));
            this.setIsStandardized(true);
        }
        else if (this.token.equals("function_form")) {
            Node P = this.children.get(0);
            List<Node> Vs = this.children.subList(1, this.children.size() - 1);
            Node E = this.children.get(this.children.size() - 1);
            // restructure
            this.token = "=";
            Node rootLambda = new Node("lambda", this, this.depth + 1);
            Node currentLambda = rootLambda;
            for (Node v : Vs) {
                currentLambda.addChild(v);
                v.setDepth(currentLambda.getDepth() + 1);
                if (Vs.indexOf(v) == Vs.size() - 1) {
                    E.increaseDepthBy(Vs.size());
                    currentLambda.addChild(E);
                } else {
                    Node nextLamda = new Node("lambda", currentLambda, currentLambda.getDepth() + 1);
                    currentLambda.addChild(nextLamda);
                    currentLambda = nextLamda;
                }
            }
            this.setChildren(new ArrayList<Node>(Arrays.asList(P,rootLambda)));
            this.setIsStandardized(true);
        }
        this.setIsStandardized(true);
    }
}
