package ASTInterpreter;
import FileReader.FileReader;
import FileReader.TextFileReader;
import Node.Node;
import Standardizer.Standardizer;
import CSEMachine.CSEMachine;
import CSEMachine.CSEStandardizer;

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
        Standardizer standardizer = new CSEStandardizer();
        standardizer.standardize(root);
        root.printNode();

        CSEMachine cseMachine =  new CSEMachine(root);
        cseMachine.printDeltas();
        cseMachine.run();
    }
}
