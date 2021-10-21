import FileReader.FileReader;
import FileReader.TextFileReader;
import Node.Node;
import Standardizer.Standardizer;

import java.util.ArrayList;

import ASTTreeGenerator.ASTGenerator;

public class Main {

    // let check x = 
    //     not Isinteger x -> 'error' |
    //     (x ls 0 -> 'negative' | 'positive')
    // in
    // print(check (-8), check 8)
    public static void main(String[] args) {
        // get ast content from file
        FileReader fileReader = new TextFileReader("inputs/input-within.txt");
        ArrayList<String> fileContent= fileReader.getData();

        // generate AST
        ASTGenerator astGenerator = new ASTGenerator();
        Node root = astGenerator.generateAST(fileContent);
        // root.printNode();

        // standardize
        Standardizer standardizer = new Standardizer();
        standardizer.standardize(root);
        root.printNode();
    }
}
