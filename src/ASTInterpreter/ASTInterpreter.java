package ASTInterpreter;
import FileReader.FileReader;
import FileReader.TextFileReader;
import Node.Node;
import Standardizer.Standardizer;

import java.util.ArrayList;

import ASTTreeGenerator.ASTGenerator;

public class ASTInterpreter {
    public static void interpret(String filename) {
        // get ast content from file
        FileReader fileReader = new TextFileReader(filename);
        ArrayList<String> fileContent= fileReader.getData();

        // generate AST
        ASTGenerator astGenerator = new ASTGenerator();
        Node root = astGenerator.generateAST(fileContent);

        // standardize
        Standardizer standardizer = new ASTStandardizer();
        standardizer.standardize(root);
        root.printNode();
    }
}