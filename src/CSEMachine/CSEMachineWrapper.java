package CSEMachine;

import FileReader.FileReader;
import FileReader.TextFileReader;
import Node.Node;

import java.util.List;

import ASTTreeGenerator.ASTGenerator;
import Standardizer.Standardizer;

/**
 * CSEMachineWrapper is the wrapper class for CSEMachine.
 * It provides the functionality to read content from the specified file and
 * standardize the AST with gamma applied for CSE machine and get the result
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class CSEMachineWrapper {
    /**
     * Execute CSE machine and returns the final result
     * 
     * @param filename input file name
     * @return answer
     */
    public static String execute(String filename) {
        // get ast content from file
        FileReader fileReader = new TextFileReader(filename);
        List<String> fileContent= fileReader.getData();

        // generate AST
        ASTGenerator astGenerator = new ASTGenerator();
        Node root = astGenerator.generateAST(fileContent);
        // standardize
        Standardizer standardizer = new CSEStandardizer();
        standardizer.standardize(root);
        // root.printNode();

        // execute
        CSEMachine cseMachine =  new CSEMachine(root);
        cseMachine.printDeltas();
        cseMachine.run();
        // get result
        return cseMachine.getAnswer();
    }
}