package CSEMachine;

import FileReader.FileReader;
import FileReader.TextFileReader;
import Node.Node;

import java.util.ArrayList;

import ASTTreeGenerator.ASTGenerator;

public class CSEMachineWrapper {
    public static void flatten(String filename) {
        // get ast content from file
        FileReader fileReader = new TextFileReader(filename);
        ArrayList<String> fileContent= fileReader.getData();

        // generate AST
        ASTGenerator astGenerator = new ASTGenerator();
        Node root = astGenerator.generateAST(fileContent);

        CSEMachine cseMachine =  new CSEMachine(root);
        cseMachine.printControl();
    }
}