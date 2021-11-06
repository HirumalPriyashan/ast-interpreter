package ASTInterpreter;
import FileReader.FileReader;
import FileReader.TextFileReader;
import Node.Node;
import Standardizer.Standardizer;

import java.util.List;

import ASTTreeGenerator.ASTGenerator;

/**
 * ASTInterpreter is the class for standardizing Abstract Syntax Tree
 * It provides the functionality to read content from the specified file and
 * standardize the AST.
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public class ASTInterpreter {
    /**
    * Reads the content from the file in the specified path, build AST from the 
    * content and standardize the AST to produce desuarized tree using 
    * <b>rpal subtree transformation gamma</b>
    *
    * @param  filename  relative or absolute path for input file
    */
    public static void interpret(String filename) {
        // get ast content from file
        FileReader fileReader = new TextFileReader(filename);
        List<String> fileContent= fileReader.getData();

        // generate AST
        ASTGenerator astGenerator = new ASTGenerator();
        Node root = astGenerator.generateAST(fileContent);

        // standardize
        Standardizer standardizer = new ASTStandardizer();
        standardizer.standardize(root);
        // print standardized tree
        root.printNode();
    }
}
